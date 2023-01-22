package cu.edu.mes.sigenu.training.api.controller;

import cu.edu.mes.sigenu.training.core.dto.QuestionnaireStudentDto;
import cu.edu.mes.sigenu.training.core.model.QuestionnaireStudent;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireStudentService;
import cu.edu.mes.sigenu.training.core.service.SigenuService;
import cu.edu.mes.sigenu.training.core.utils.ApiResponse;
import cu.edu.mes.subsystem.student.vo.StudentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Questionnaire Student endpoint controller")
@RequestMapping(value = "/questionnaire-student",produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionnaireStudentController {

    @Autowired
    private QuestionnaireStudentService questionnaireStudentService;

    @Autowired
    private SigenuService sigenuService;

    @GetMapping("/questionnaire/{questionnaireId}")
    @ApiOperation(value = "Get a list with all students by a questionnaire")
    public List<String> list(@PathVariable Integer questionnaireId) {
		return questionnaireStudentService.listAllByQuestionnaire(questionnaireId);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get questionnaire student by id")
    public QuestionnaireStudent get(@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();
        QuestionnaireStudent item = questionnaireStudentService.findById(id);
        return modelMapper.map(item, QuestionnaireStudent.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create questionnaire student")
    public ResponseEntity<ApiResponse> save(@RequestBody QuestionnaireStudentDto item){
        try{
            ModelMapper modelMapper = new ModelMapper();
            QuestionnaireStudent questionnarieStudent = modelMapper.map(item,QuestionnaireStudent.class);
            questionnaireStudentService.save(questionnarieStudent, item.getIdentification());
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: Questionnaire Student hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Questionnaire Student created successfully"));
    }
    
    @GetMapping("/student-sigenu/{identification}")
    @ApiOperation(value = "Check a student exist in SIGENU")
    public StudentVO getExistStudent(@PathVariable String identification) {
        String sigenuId;
        StudentVO studentVO = null;
        
        try {
             sigenuId = sigenuService.getStudentIdByIdentification(identification);
             studentVO = sigenuService.getStudentBySigenuId(sigenuId);
        } catch (Exception e) {
            return new StudentVO();
        }
        return studentVO;
    }
}
