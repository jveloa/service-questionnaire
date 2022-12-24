package cu.edu.mes.sigenu.training.api.controller;



import cu.edu.mes.sigenu.training.core.dto.QuestionnaireDto;

import cu.edu.mes.sigenu.training.core.model.Questionnaire;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireService;
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
@Api(tags = "Questionnaire endpoint controller")
@RequestMapping(value = "/questionnaire",produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @GetMapping("")
    @ApiOperation(value = "Get a List with all questionnaire")
    public List<QuestionnaireDto> list(){
        ModelMapper modelMapper = new ModelMapper();
        return questionnaireService.listAll().stream()
                .map(item -> modelMapper.map(item,QuestionnaireDto.class))
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get questionnaire by id")
    public QuestionnaireDto get(@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();
        Questionnaire item = questionnaireService.findById(id);
        return modelMapper.map(item, QuestionnaireDto.class);

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create questionnaire")
    public ResponseEntity<ApiResponse> save(@RequestBody QuestionnaireDto item){
        try{
            ModelMapper modelMapper = new ModelMapper();
            Questionnaire questionnaire = modelMapper.map(item,Questionnaire.class);
            questionnaireService.save(questionnaire);
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: questionnaire hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Questionnaire created successfully"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("")
    @ApiOperation(value = "Update questionnaire registered")
    public ResponseEntity<ApiResponse> update(@ApiParam(value = "Values for updating", name = "Body") @RequestBody QuestionnaireDto item) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Questionnaire questionnaire = modelMapper.map(item, Questionnaire.class);
            questionnaireService.update(questionnaire);
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Error: Questionnaire hasn't been updated@"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Questionnaire updated successfully@"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete questionnaire registered")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        try {
            Questionnaire questionnaire = questionnaireService.findById(id);
            if (!questionnaire.getQuestionnarieStudentList().isEmpty()){
                return ResponseEntity.ok(new ApiResponse(false, "Questionnaire has already been taken"));
            }
            questionnaireService.delete(id);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException)
                return ResponseEntity.ok(new ApiResponse(false, "Questionnaire CAN'T be deleted because has been reference by another entity"));
            return ResponseEntity.ok(new ApiResponse(false, "Questionnaire CAN'T be deleted"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Questionnaire deleted successfully@"));
    }
}
