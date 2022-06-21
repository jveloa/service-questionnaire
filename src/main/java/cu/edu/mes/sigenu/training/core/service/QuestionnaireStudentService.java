package cu.edu.mes.sigenu.training.core.service;


import cu.edu.mes.sigenu.training.core.model.QuestionnarieStudent;
import cu.edu.mes.sigenu.training.core.model.StudentAnswer;

import java.util.List;


public interface QuestionnaireStudentService {

    List<QuestionnarieStudent> listAllByStudent(String sigenuId);

    QuestionnarieStudent findById(Integer id);

    QuestionnarieStudent save(QuestionnarieStudent questionnarieStudent);

    QuestionnarieStudent update(QuestionnarieStudent questionnarieStudent);

    void delete(Integer id);


}
