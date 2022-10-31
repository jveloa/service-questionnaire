package cu.edu.mes.sigenu.training.api.controller;


import cu.edu.mes.sigenu.training.core.dto.QuestionnaireQuestionByGroupDto;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireQuestionService;
import cu.edu.mes.sigenu.training.core.utils.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = "Questionnaire Question endpoint controller")
@RequestMapping(value = "/questionnaire-question",produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionnaireQuestionController {

    @Autowired
    private QuestionnaireQuestionService questionnaireQuestionService;

    @GetMapping("/{questionnaireId}/")
    @ApiOperation(value = "Get all question by a questionnaire")
    public List<QuestionnaireQuestionByGroupDto> get(@PathVariable Integer questionnaireId){
        return questionnaireQuestionService.getQuestionsByQuestionnaire(questionnaireId);
    }

    @PostMapping("/{questionnaire_id}/{question_id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add question to questionnaire")
    public ResponseEntity<ApiResponse> save(@PathVariable Integer questionnaire_id, @PathVariable Integer question_id){
        try{
            questionnaireQuestionService.addQuestionToQuestionnaire(questionnaire_id,question_id);
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: Questionnaire question hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Questionnaire question created successfully"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{questionnaire_id}/{question_id}")
    @ApiOperation(value = "Delete question to questionnaire ")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer questionnaire_id, @PathVariable Integer question_id) {
        try {
            questionnaireQuestionService.deleteQuestionToQuestionnaire(questionnaire_id,question_id);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException)
                return ResponseEntity.ok(new ApiResponse(false, "Questionnaire Question CAN'T be deleted because has been reference by another entity"));
            return ResponseEntity.ok(new ApiResponse(false, "Questionnaire Question CAN'T be deleted"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Questionnaire Question deleted successfully@"));
    }

}
