package cu.edu.mes.sigenu.training.core.service;


import cu.edu.mes.sigenu.training.core.model.QuestionnarieStudent;

import java.util.List;


public interface QuestionnaireStudentService {

    List<QuestionnarieStudent> listAllByStudent(String sigenuId);

    QuestionnarieStudent findById(Integer id);

    QuestionnarieStudent save(QuestionnarieStudent questionnarieStudent,String identification);

    QuestionnarieStudent update(QuestionnarieStudent questionnarieStudent);

    void delete(Integer id);


}
