package cu.edu.mes.sigenu.training.api.controller;


import cu.edu.mes.sigenu.training.core.dto.QuestionnaireStudentDto;
import cu.edu.mes.sigenu.training.core.model.QuestionnarieStudent;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireStudentService;
import cu.edu.mes.sigenu.training.core.utils.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Questionnaire student enpoint controller")
@RequestMapping(value = "/questionnaire-student",produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionnaireStudentController {

    @Autowired
    private QuestionnaireStudentService questionnaireStudentService;

    @GetMapping("/student/{sigenuId}")
    @ApiOperation(value = "Get a List with all student questionnaire by a student")
    public List<QuestionnaireStudentDto> list(@PathVariable String sigenuId){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<QuestionnarieStudent, QuestionnaireStudentDto> propertyMapper = modelMapper.createTypeMap(QuestionnarieStudent.class, QuestionnaireStudentDto.class);
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionnarieId().getName(),
                (destination, value) -> destination.getQuestionnarieId().setName((String)value)));
       propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionnarieId().getDescription(),
                (destination, value) -> destination.getQuestionnarieId().setDescription((String) value)));
        return questionnaireStudentService.listAllByStudent(sigenuId).stream()
                .map(item -> modelMapper.map(item,QuestionnaireStudentDto.class))
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get questionnaire student by id")
    public QuestionnaireStudentDto get(@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();
        QuestionnarieStudent item = questionnaireStudentService.findById(id);
        return modelMapper.map(item, QuestionnaireStudentDto.class);

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create questionnaire student")
    public ResponseEntity<ApiResponse> save(@RequestBody QuestionnaireStudentDto item){
        try{
            ModelMapper modelMapper = new ModelMapper();
            QuestionnarieStudent questionnarieStudent = modelMapper.map(item,QuestionnarieStudent.class);
            questionnaireStudentService.save(questionnarieStudent,item.getIdentification());
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: questionnaire student hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Questionnaire student created successfully"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("")
    @ApiOperation(value = "Update questionnaire student registered")
    public ResponseEntity<ApiResponse> update(@ApiParam(value = "Values for updating", name = "Body") @RequestBody QuestionnaireStudentDto item) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            QuestionnarieStudent questionnarieStudent = modelMapper.map(item, QuestionnarieStudent.class);
            questionnaireStudentService.update(questionnarieStudent);
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Error: Questionnaire student hasn't been updated@"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Questionnaire student updated successfully@"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete questionnaire student registered")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        try {
            questionnaireStudentService.delete(id);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException)
                return ResponseEntity.ok(new ApiResponse(false, "Questionnaire student CAN'T be deleted because has been reference by another entity"));
            return ResponseEntity.ok(new ApiResponse(false, "Questionnaire student CAN'T be deleted"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Questionnaire student deleted successfully@"));
    }
}
