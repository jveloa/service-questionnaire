package cu.edu.mes.sigenu.training.api.controller;

import cu.edu.mes.sigenu.training.core.dto.CorrectAnswerDto;
import cu.edu.mes.sigenu.training.core.dto.CorrectAnswerDto;
import cu.edu.mes.sigenu.training.core.model.CorrectAnswer;
import cu.edu.mes.sigenu.training.core.model.CorrectAnswer;
import cu.edu.mes.sigenu.training.core.service.CorrectAnswerService;
import cu.edu.mes.sigenu.training.core.utils.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Correct Answer controller")
@RequestMapping(value = "/correct-asnwer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CorrectAnswerController {

    @Autowired
    private CorrectAnswerService correctAnswerService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get correct answer by id")
    public CorrectAnswerDto get(@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();
        CorrectAnswer item = correctAnswerService.findById(id);
        return modelMapper.map(item, CorrectAnswerDto.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create correct answer")
    public ResponseEntity<ApiResponse> save(@RequestBody CorrectAnswerDto item){
        try{
            ModelMapper modelMapper = new ModelMapper();
            CorrectAnswer correctAnswer = modelMapper.map(item,CorrectAnswer.class);
            correctAnswerService.save(correctAnswer);
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: Correct Answer hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Correct Answer created successfully"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{Id}")
    @ApiOperation(value = "Delete correct answer registered")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer Id) {
        try {
            correctAnswerService.deleteById(Id);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException)
                return ResponseEntity.ok(new ApiResponse(false, "Correct Answer CAN'T be deleted because has been reference by another entity"));
            return ResponseEntity.ok(new ApiResponse(false, "Correct Answer CAN'T be deleted"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Correct Answer deleted successfully@"));
    }
}
