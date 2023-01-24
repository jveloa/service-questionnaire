package cu.edu.mes.sigenu.training.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cu.edu.mes.sigenu.training.core.model.QuestionnaireCourse;

public interface QuestionnaireCourseRepository extends JpaRepository<QuestionnaireCourse,Integer>, JpaSpecificationExecutor<QuestionnaireCourse> {

}
