package cu.edu.mes.sigenu.training.service;
import cu.edu.mes.sigenu.training.core.model.Question;
import cu.edu.mes.sigenu.training.core.model.Questionnaire;
import cu.edu.mes.sigenu.training.core.repository.QuestionnaireRepository;
import cu.edu.mes.sigenu.training.core.service.QuestionService;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireQuestionService;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionnaireQuestionService questionnaireQuestionService;

    @Override
    public List<Questionnaire> listAll() {
        return questionnaireRepository.findAll();
    }

    @Override
    public Questionnaire findById(Integer id) {
        if (questionnaireRepository.existsById(id))
            return questionnaireRepository.findById(id).get();
        else
            return new Questionnaire();
    }

    @Override
    public Questionnaire save(Questionnaire questionnaire) {
        Questionnaire result =questionnaireRepository.save(questionnaire);
        List<Question> questionList = questionService.listAllWithoutCareer();
        questionList.forEach(question -> questionnaireQuestionService.addQuestionToQuestionnaire(questionnaire.getId(),question.getId()));
        return result;

    }

    @Override
    public Questionnaire update(Questionnaire questionnaire) {
        Questionnaire itemDB = findById(questionnaire.getId());
        itemDB.setName(questionnaire.getName());
        return questionnaireRepository.save(itemDB);
    }

    @Override
    public void delete(Integer id) {
        Questionnaire questionnaire = questionnaireRepository.findById(id).get();
        List<Question> questionList =  questionnaire.getQuestionList();
        questionList.forEach(question -> questionnaireQuestionService.deleteQuestionToQuestionnaire(id,question.getId()));
        questionnaireRepository.deleteById(id);

    }
}
