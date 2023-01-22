package cu.edu.mes.sigenu.training.core.service;

import java.util.List;

import cu.edu.mes.sigenu.training.core.model.QuestionnaireCourse;

public interface QuestionnaireCourseService {

	List<QuestionnaireCourse> listAll();

    QuestionnaireCourse findById(Integer id);

    QuestionnaireCourse save(QuestionnaireCourse questionnaireCourse);

    void delete(Integer id);
}
