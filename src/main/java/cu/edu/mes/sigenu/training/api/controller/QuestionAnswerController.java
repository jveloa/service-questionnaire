package cu.edu.mes.sigenu.training.api.controller;

import cu.edu.mes.sigenu.training.core.dto.question.QuestionAnswerDto;
import cu.edu.mes.sigenu.training.core.dto.question.QuestionCarrerDto;
import cu.edu.mes.sigenu.training.core.model.QuestionAnswer;
import cu.edu.mes.sigenu.training.core.model.QuestionCarrer;
import cu.edu.mes.sigenu.training.core.repository.QuestionAnswerRepository;
import cu.edu.mes.sigenu.training.core.service.QuestionAnswerService;
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
@Api(tags = "Question Answer enpoint controller")
@RequestMapping(value = "/question-answer",produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionAnswerController {
    @Autowired
    private QuestionAnswerService questionAnswerService;

    @GetMapping("")
    @ApiOperation(value = "Get a List with all question answer")
    public List<QuestionAnswerDto> list(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<QuestionAnswer,QuestionAnswerDto> propertyMapper = modelMapper.createTypeMap(QuestionAnswer.class, QuestionAnswerDto.class);
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionId().getId(),QuestionAnswerDto::setQuestionId));

        return questionAnswerService.listAll().stream()
                                    .map(item -> modelMapper.map(item,QuestionAnswerDto.class))
                                    .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get question answer by id")
    public QuestionAnswerDto get(@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<QuestionAnswer,QuestionAnswerDto> propertyMapper = modelMapper.createTypeMap(QuestionAnswer.class,QuestionAnswerDto.class);
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionId().getId(),QuestionAnswerDto::setQuestionId));
        QuestionAnswer item = questionAnswerService.findById(id);
        return modelMapper.map(item, QuestionAnswerDto.class);

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create question answer")
    public ResponseEntity<ApiResponse> save(@RequestBody QuestionAnswerDto item){
        try{
            ModelMapper modelMapper = new ModelMapper();
            QuestionAnswer questionCarrer = modelMapper.map(item,QuestionAnswer.class);
            questionAnswerService.save(questionCarrer);
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: Question answer hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Question answer created successfully"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("")
    @ApiOperation(value = "Update question answer registered")
    public ResponseEntity<ApiResponse> update(@ApiParam(value = "Values for updating", name = "Body") @RequestBody QuestionAnswerDto item) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            QuestionAnswer questionAnswer = modelMapper.map(item, QuestionAnswer.class);
            questionAnswerService.update(questionAnswer);
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Error: question answer hasn't been updated@"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "question answer updated successfully@"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete question asnwer registered")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        try {
            questionAnswerService.delete(id);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException)
                return ResponseEntity.ok(new ApiResponse(false, "question answer CAN'T be deleted because has been reference by another entity"));
            return ResponseEntity.ok(new ApiResponse(false, "question answer CAN'T be deleted"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "question answer deleted successfully@"));
    }

}
