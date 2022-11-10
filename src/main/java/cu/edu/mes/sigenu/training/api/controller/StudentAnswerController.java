package cu.edu.mes.sigenu.training.api.controller;

import cu.edu.mes.sigenu.training.core.dto.StudentAnswerDto;
import cu.edu.mes.sigenu.training.core.model.QuestionAnswer;
import cu.edu.mes.sigenu.training.core.model.Questionnaire;
import cu.edu.mes.sigenu.training.core.model.QuestionnarieStudent;
import cu.edu.mes.sigenu.training.core.model.StudentAnswer;
import cu.edu.mes.sigenu.training.core.service.*;
import cu.edu.mes.sigenu.training.core.utils.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Student Answer enpoint controller")
@RequestMapping(value = "/student-answer",produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentAnswerController {

    @Autowired
    private StudentAnswerService studentAnswerService;

    @Autowired
    private QuestionAnswerService questionAnswerService;

    @Autowired
    private SigenuService sigenuService;

    @Autowired
    private QuestionnaireStudentService questionnaireStudentService;

    @GetMapping("/student/{sigenuId}")
    @ApiOperation(value = "Get a List with all student answer by a student")
    public List<StudentAnswerDto> list(@PathVariable String sigenuId){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<StudentAnswer, StudentAnswerDto> propertyMapper = modelMapper.createTypeMap(StudentAnswer.class, StudentAnswerDto.class);
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionAnswerId().getQuestionId().getId(),
                                                         (destination, value) -> destination.getQuestionAnswerId().setQuestionId((Integer)value)));
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionAnswerId().getAnswerId().getId(),
                                                         (destination, value) -> destination.getQuestionAnswerId().setAnswerId((Integer) value)));
        return studentAnswerService.listAllByStudent(sigenuId).stream()
                                    .map(item -> modelMapper.map(item,StudentAnswerDto.class))
                                    .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get student answer by id")
    public StudentAnswerDto get(@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<StudentAnswer, StudentAnswerDto> propertyMapper = modelMapper.createTypeMap(StudentAnswer.class, StudentAnswerDto.class);
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionAnswerId().getQuestionId().getId(),
                                                         (destination, value) -> destination.getQuestionAnswerId().setQuestionId((Integer)value)));
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionAnswerId().getAnswerId().getId(),
                                                         (destination, value) -> destination.getQuestionAnswerId().setAnswerId((Integer) value)));
        StudentAnswer item = studentAnswerService.findById(id);
        return modelMapper.map(item, StudentAnswerDto.class);

    }

    @PostMapping("/questionnaire/{questionnaireId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create student answer")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> save(@RequestBody List<StudentAnswerDto> items,@PathVariable Integer questionnaireId){
        try{

            ModelMapper modelMapper = new ModelMapper();
            List<StudentAnswer> studentAnswerList = new ArrayList<>();
            String idSigenu = sigenuService.getStudentIdByIdentification(items.get(0).getIdentification());

            for (StudentAnswerDto studentAnswerDtos:items) {
                StudentAnswer studentAnswer = new StudentAnswer();
                studentAnswer.setStudentSigenuId(idSigenu);
                QuestionAnswer questionAnswer = modelMapper.map(studentAnswerDtos.getQuestionAnswerId(),QuestionAnswer.class);


                int id = questionAnswerService.findByQuestionIdAnswerId(questionAnswer.getQuestionId(),questionAnswer.getAnswerId()).getId();
                questionAnswer.setId(id);
                studentAnswer.setQuestionAnswerId(questionAnswer);
                studentAnswerList.add(studentAnswer);

            }
            studentAnswerService.save(studentAnswerList);
            QuestionnarieStudent questionnarieStudent = new QuestionnarieStudent();
            questionnarieStudent.setStudentSigenuId(idSigenu);
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setId(questionnaireId);
            questionnarieStudent.setQuestionnarieId(questionnaire);
            questionnarieStudent.setDoneDate(Date.from(Instant.now()));
            questionnaireStudentService.save(questionnarieStudent,items.get(0).getIdentification());

        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: Student answers hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Student answers created successfully"));
    }



}
