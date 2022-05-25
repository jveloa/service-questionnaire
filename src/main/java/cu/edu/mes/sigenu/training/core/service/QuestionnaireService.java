package cu.edu.mes.sigenu.training.core.service;

import cu.edu.mes.sigenu.training.core.model.Questionnaire;

import java.util.List;

public interface QuestionnaireService {
    List<Questionnaire> listAll();

    Questionnaire findById(Integer id);

    Questionnaire save(Questionnaire questionnaire);

    Questionnaire update(Questionnaire questionnaire);

    void delete(Integer id);


}
