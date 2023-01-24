package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.model.Questionnaire;
import cu.edu.mes.sigenu.training.core.repository.QuestionnaireRepository;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

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
        return questionnaireRepository.save(questionnaire);
    }

    @Override
    public Questionnaire update(Questionnaire questionnaire) {
        Questionnaire aux = findById(questionnaire.getId());
        aux.setNameQuestionnaire(questionnaire.getNameQuestionnaire());
        return questionnaireRepository.save(aux);
    }

    @Override
    public void delete(Integer id) {
    	questionnaireRepository.delete(new Questionnaire(id));
    }
}
