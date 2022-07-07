package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.model.Question;
import cu.edu.mes.sigenu.training.core.model.Questionnaire;
import cu.edu.mes.sigenu.training.core.service.QuestionService;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireQuestionService;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
