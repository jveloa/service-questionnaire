package cu.edu.mes.sigenu.training.api.controller;

import cu.edu.mes.sigenu.training.core.dto.StudentAnswerDto;
import cu.edu.mes.sigenu.training.core.model.StudentAnswer;
import cu.edu.mes.sigenu.training.core.service.StudentAnswerService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Student Answer enpoint controller")
@RequestMapping(value = "/student-answer",produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentAnswerController {
    @Autowired
    private StudentAnswerService studentAnswerService;

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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create student answer")
    public ResponseEntity<ApiResponse> save(@RequestBody List<StudentAnswerDto> items){
        try{
            ModelMapper modelMapper = new ModelMapper();
            List<StudentAnswer> studentAnswerList = items.stream()
                                                         .map(item -> modelMapper.map(item,StudentAnswer.class))
                                                         .collect(Collectors.toList());
            studentAnswerService.save(studentAnswerList);
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: Student answers hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Student answers created successfully"));
    }



}
