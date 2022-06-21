package cu.edu.mes.sigenu.training.core.repository;


import cu.edu.mes.sigenu.training.core.model.QuestionnarieStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface QuestionnaireStudentRepository extends JpaRepository<QuestionnarieStudent, Integer>, JpaSpecificationExecutor<QuestionnarieStudent> {

    List<QuestionnarieStudent> findAllByStudentSigenuId(String sigenuId);

}
