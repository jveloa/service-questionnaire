package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.dto.AnswerDto;
import cu.edu.mes.sigenu.training.core.dto.QuestionDto;
import cu.edu.mes.sigenu.training.core.dto.QuestionnaireQuestionDto;
import cu.edu.mes.sigenu.training.core.model.Answer;
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
    public void addQuestionToQuestionnaire(Integer questionnaire_id, Integer question_id) {

        Questionnaire questionnaire = questionnaireService.findById(questionnaire_id);
        Question question = questionService.findById(question_id);
        question.getQuestionnaireList().add(questionnaire);
        questionService.save(question);

    }

    @Override
    public void deleteQuestionToQuestionnaire(Integer questionnaire_id, Integer question_id) {

        Questionnaire questionnaire = questionnaireService.findById(questionnaire_id);
        Question question = questionService.findById(question_id);
        question.getQuestionnaireList().remove(questionnaire);
        questionService.update(question);

    }

    @Override
    public List<QuestionnaireQuestionDto> getQuestionsByQuestionnaire(Integer questionnaireId) {
        List<QuestionnaireQuestionDto> questionnaireQuestionList = new ArrayList<>();
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        ModelMapper modelMapper = new ModelMapper();
        for (Question question:questionnaire.getQuestionList()){
            questionnaireQuestionList
                .add(QuestionnaireQuestionDto.builder()
                                             .question(modelMapper.map(question,QuestionDto.class))
                                             .answerList(question.getQuestionAnswerList().stream()
                                                                 .map(item -> modelMapper.map(item.getAnswerId(),AnswerDto.class))
                                                                 .collect(Collectors.toList()))
                                             .build());
        }
        return questionnaireQuestionList;
    }
}
