package cu.edu.mes.sigenu.training.core.service;

import cu.edu.mes.sigenu.training.core.model.QuestionnaireStudent;

import java.util.List;

public interface QuestionnaireStudentService {

    List<String> listAllByQuestionnaire(Integer questionnaireId);

    QuestionnaireStudent findById(Integer id);

    QuestionnaireStudent save(QuestionnaireStudent questionnaireStudent, String identification);
}
