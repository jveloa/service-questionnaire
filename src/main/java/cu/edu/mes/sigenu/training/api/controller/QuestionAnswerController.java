package cu.edu.mes.sigenu.training.api.controller;

import cu.edu.mes.sigenu.training.core.dto.QuestionAnswerDto;
import cu.edu.mes.sigenu.training.core.model.Question;
import cu.edu.mes.sigenu.training.core.model.QuestionAnswer;
import cu.edu.mes.sigenu.training.core.service.QuestionAnswerService;
import cu.edu.mes.sigenu.training.core.utils.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Question Answer endpoint controller")
@RequestMapping(value = "/question-answer",produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionAnswerController {
    
	@Autowired
    private QuestionAnswerService questionAnswerService;

	@GetMapping("")
    @ApiOperation(value = "Get a list with all question answers")
    public List<QuestionAnswerDto> list(){
        ModelMapper modelMapper = new ModelMapper();
        
        /*modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<QuestionAnswer,QuestionAnswerDto> propertyMapper = modelMapper.createTypeMap(QuestionAnswer.class, QuestionAnswerDto.class);
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionId().getId(),QuestionAnswerDto::setQuestionId));
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getAnswerId().getId(),QuestionAnswerDto::setAnswerId));
        propertyMapper.addMappings(mapper-> mapper.map(src-> src.getCorrectAnswer().getQuestionAnswerId().getId(), (destination, value) -> destination.getCorrectAnswer().setQuestionAnswerId((Integer)value)));*/

        return questionAnswerService.listAll()
        		                    .stream()
                                    .map(item -> modelMapper.map(item,QuestionAnswerDto.class))
                                    .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get question answer by id")
    public QuestionAnswerDto get(@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();
        
        /*modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<QuestionAnswer,QuestionAnswerDto> propertyMapper = modelMapper.createTypeMap(QuestionAnswer.class,QuestionAnswerDto.class);
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionId().getId(),QuestionAnswerDto::setQuestionId));
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getAnswerId().getId(),QuestionAnswerDto::setAnswerId));
        TypeMap<CorrectAnswer,CorrectAnswerDto> propertyMapper1 = modelMapper.createTypeMap(CorrectAnswer.class, CorrectAnswerDto.class);
        propertyMapper1.addMappings(mapper-> mapper.map(src-> src.getQuestionAnswerId().getId(), (destination, value) -> destination.setQuestionAnswerId((Integer) value)));*/
        
        QuestionAnswer item = questionAnswerService.findById(id);
        return modelMapper.map(item, QuestionAnswerDto.class);
    }

    @GetMapping("/question-id/{id}")
    @ApiOperation(value = "Get question answer by question id")
    public List<QuestionAnswerDto> getByQuestionId(@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();
        
        /*modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<QuestionAnswer,QuestionAnswerDto> propertyMapper = modelMapper.createTypeMap(QuestionAnswer.class,QuestionAnswerDto.class);
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionId().getId(),QuestionAnswerDto::setQuestionId));
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getAnswerId().getId(),QuestionAnswerDto::setAnswerId));
        TypeMap<CorrectAnswer,CorrectAnswerDto> propertyMapper1 = modelMapper.createTypeMap(CorrectAnswer.class, CorrectAnswerDto.class);
        propertyMapper1.addMappings(mapper-> mapper.map(src-> src.getQuestionAnswerId().getId(), (destination, value) -> destination.setQuestionAnswerId((Integer) value)));*/
        
        Question question = new Question(id);
        return questionAnswerService.getByQuestionId(question).stream()
                                    .map(item -> modelMapper.map(item,QuestionAnswerDto.class))
                                    .collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create question answer")
    public ResponseEntity<ApiResponse> save(@RequestBody QuestionAnswerDto item){
        try{
            ModelMapper modelMapper = new ModelMapper();
            QuestionAnswer questionAnswer = modelMapper.map(item,QuestionAnswer.class);
            questionAnswerService.save(questionAnswer);
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: Question Answer hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Question Answer created successfully"));
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Update question answer registered")
    public ResponseEntity<ApiResponse> update(@ApiParam(value = "Values for updating", name = "Body") @RequestBody QuestionAnswerDto item) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            QuestionAnswer questionAnswer = modelMapper.map(item, QuestionAnswer.class);
            questionAnswerService.update(questionAnswer);
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Error: Question Answer hasn't been updated"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Question Answer updated successfully"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete question asnwer registered")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        try {
            questionAnswerService.delete(id);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException)
                return ResponseEntity.ok(new ApiResponse(false,
                		"Error: Question Answer can't be deleted because has been reference by another entity"));
            return ResponseEntity.ok(new ApiResponse(false, "Error: Question Answer can't be deleted"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Question Answer deleted successfully"));
    }
}
