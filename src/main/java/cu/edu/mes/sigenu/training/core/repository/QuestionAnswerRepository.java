package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Integer>, JpaSpecificationExecutor<QuestionAnswer> {

}
