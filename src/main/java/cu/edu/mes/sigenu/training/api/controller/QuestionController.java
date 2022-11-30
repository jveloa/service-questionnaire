package cu.edu.mes.sigenu.training.api.controller;

import cu.edu.mes.sigenu.training.core.dto.QuestionDto;
import cu.edu.mes.sigenu.training.core.dto.QuestionWithCareerDto;
import cu.edu.mes.sigenu.training.core.model.Question;
import cu.edu.mes.sigenu.training.core.service.QuestionService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Question endpoint controller")
@RequestMapping(value = "/question", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create question")
	public ResponseEntity<ApiResponse> save(@RequestBody QuestionDto item) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			Question question = modelMapper.map(item, Question.class);
			questionService.save(question);
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(false, "Error: Question hasn't been created@"));
		}
		return ResponseEntity.ok(new ApiResponse(true, "Question created successfully@"));
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Get question by id")
	public QuestionDto get(@PathVariable Integer id){
		ModelMapper modelMapper = new ModelMapper();
		Question item = questionService.findById(id);
		return modelMapper.map(item, QuestionDto.class);
	}
	
	@GetMapping("")
	@ApiOperation(value = "List questions")
	public List<QuestionDto> list(){
		ModelMapper modelMapper = new ModelMapper();
		return questionService.listAll()
				  .stream()
				  .map(item -> modelMapper.map(item, QuestionDto.class))
				  .collect(Collectors.toList());
	}

    @GetMapping("/questionWithoutCareer")
    @ApiOperation(value = "List questions without career")
    public List<QuestionDto> listQuestionWithoutCareer() {
        ModelMapper modelMapper = new ModelMapper();
        return questionService.listAllWithoutCareer()
                              .stream()
                              .map(item -> modelMapper.map(item, QuestionDto.class))
                              .collect(Collectors.toList());
    }

    @GetMapping("/questionWithCareer")
    @ApiOperation(value = "List questions with career")
    public List<QuestionWithCareerDto> listQuestionWithCareer() {
        List<QuestionWithCareerDto> questionWithCareerDtos = new ArrayList<>();
        List<Question> questions = questionService.listAllWithCareer();
        for (Question question : questions) {
            questionWithCareerDtos.add(QuestionWithCareerDto.builder()
                                                            .id(question.getId())
                                                            .question(question.getQuestion())
                                                            .groupQuestionId(question.getGroupQuestionId().getId())
                                                            .questionCarrerId(question.getQuestionCarrerList().get(0).getCareerSigenuId())
                                                            .build());
        }
        return questionWithCareerDtos;
    }
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping("")
	@ApiOperation(value = "Update question registered")
	public ResponseEntity<ApiResponse> update(@ApiParam(value = "Values for updating", name = "Body") @RequestBody QuestionDto item) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			Question question = modelMapper.map(item, Question.class);
			questionService.update(question);
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(false, "Error: Question hasn't been updated@"));
		}
		return ResponseEntity.ok(new ApiResponse(true, "Question updated successfully@"));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete question registered")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
		try {
			questionService.delete(id);
		} catch (Exception e) {
			if(e instanceof DataIntegrityViolationException)
				return ResponseEntity.ok(new ApiResponse(false, "Question CAN'T be deleted because has been reference by another entity"));
			return ResponseEntity.ok(new ApiResponse(false, "Question CAN'T be deleted"));
		}
		return ResponseEntity.ok(new ApiResponse(true, "Question deleted successfully@"));
	}
}
