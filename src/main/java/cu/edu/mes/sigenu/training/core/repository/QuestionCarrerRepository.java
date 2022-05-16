package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.QuestionCarrer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionCarrerRepository extends JpaRepository<QuestionCarrer, Integer>, JpaSpecificationExecutor<QuestionCarrer> {

}
