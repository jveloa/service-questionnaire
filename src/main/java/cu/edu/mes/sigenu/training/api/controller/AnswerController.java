package cu.edu.mes.sigenu.training.api.controller;


import cu.edu.mes.sigenu.training.core.dto.AnswerDto;
import cu.edu.mes.sigenu.training.core.model.Answer;
import cu.edu.mes.sigenu.training.core.service.AnswerService;
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
@Api(tags = "Answer enpoint controller")
@RequestMapping(value = "/answer", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("")
    @ApiOperation(value = "Get a List with all answer")
    public List<AnswerDto> list() {
        ModelMapper modelMapper = new ModelMapper();
        return answerService.listAll()
                            .stream()
                            .map(item -> modelMapper.map(item, AnswerDto.class))
                            .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get answer by id")
    public AnswerDto get(@PathVariable Integer id) {
        ModelMapper modelMapper = new ModelMapper();
        Answer item = answerService.findById(id);
        return modelMapper.map(item, AnswerDto.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create answer")
    public ResponseEntity<ApiResponse> save(@RequestBody AnswerDto item) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Answer answer = modelMapper.map(item, Answer.class);
            answerService.save(answer);
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Error: Answer hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Answer created successfully"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("")
    @ApiOperation(value = "Update answer registered")
    public ResponseEntity<ApiResponse> update(@ApiParam(value = "Values for updating", name = "Body") @RequestBody AnswerDto item) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Answer answer = modelMapper.map(item, Answer.class);
            answerService.update(answer);
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Error: Answer hasn't been updated@"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Answer updated successfully@"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete answer registered")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        try {
            answerService.delete(id);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException)
                return ResponseEntity.ok(
                    new ApiResponse(false, "Answer CAN'T be deleted because has been reference by another entity"));
            return ResponseEntity.ok(new ApiResponse(false, "Answer CAN'T be deleted"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Answer deleted successfully@"));
    }
}
