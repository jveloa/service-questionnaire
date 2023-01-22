package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.dto.*;
import cu.edu.mes.sigenu.training.core.model.Question;
import cu.edu.mes.sigenu.training.core.model.Questionnaire;
import cu.edu.mes.sigenu.training.core.service.QuestionService;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireQuestionService;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionnaireQuestionServiceImpl implements QuestionnaireQuestionService {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Override
    public List<QuestionDto> getQuestionsByQuestionnaireId(Integer questionnaireId){
        ModelMapper modelMapper = new ModelMapper();
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        return questionnaire.getQuestionList()
                            .stream()
                            .map(question -> modelMapper.map(question,QuestionDto.class))
                            .collect(Collectors.toList());
    }
    
    @Override
    public void addQuestionToQuestionnaire(Integer questionnaireId, Integer questionId) {
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        Question question = questionService.findById(questionId);
        question.getQuestionnaireList()
                .add(questionnaire);
        questionService.save(question);

    }

    @Override
    public void deleteQuestionToQuestionnaire(Integer questionnaireId, Integer questionId) {
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        Question question = questionService.findById(questionId);
        question.getQuestionnaireList()
                .remove(questionnaire);
        questionService.update(question);
    }

    @Override
    public List<QuestionnaireQuestionByGroupDto> getQuestionsByQuestionnaire(Integer questionnaireId) {
        List<QuestionnaireQuestionByGroupDto> questionnaireQuestionByGroupDto = 
        		new ArrayList<QuestionnaireQuestionByGroupDto>();
        List<Question> questionList = questionService.getQuestionByQuestionnaire(questionnaireId);
        ModelMapper modelMapper = new ModelMapper();
        
        for (Question question : questionList) {
            List<QuestionnaireQuestionDto> questionnaireQuestionDto = null;
            
            for(QuestionnaireQuestionByGroupDto item : questionnaireQuestionByGroupDto ){
                if(question.getGroupQuestionId().getId().equals(item.getGroupQuestionDto().getId())) {
                    questionnaireQuestionDto = item.getQuestionnaireQuestionDto();
                    questionnaireQuestionDto.add(QuestionnaireQuestionDto.builder()
                    		.question(modelMapper.map(question, QuestionDto.class))
                    		.answerList(question.getQuestionAnswerList().stream()
                    				.map(var -> modelMapper.map(var.getAnswerId(), AnswerDto.class))
                    				.collect(Collectors.toList()))
                    		.build());
                }
            }

            if(questionnaireQuestionDto == null) {
                questionnaireQuestionDto = new ArrayList<>();
                questionnaireQuestionDto.add(QuestionnaireQuestionDto
                		.builder()
                		.question(modelMapper.map(question, QuestionDto.class))
                		.answerList(question.getQuestionAnswerList().stream()
                				.map(var -> modelMapper.map(var.getAnswerId(), AnswerDto.class))
                                .collect(Collectors.toList()))
                		.build());
                questionnaireQuestionByGroupDto.add(QuestionnaireQuestionByGroupDto
                		.builder()
                		.groupQuestionDto(modelMapper.map(question.getGroupQuestionId(), GroupQuestionDto.class))
                        .questionnaireQuestionDto(questionnaireQuestionDto)
                        .build());
            }
        }
        return questionnaireQuestionByGroupDto;
    }
}
