package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.GroupQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupQuestionRepository extends JpaRepository<GroupQuestion, Integer>, JpaSpecificationExecutor<GroupQuestion> {

    GroupQuestion findByNameGroup(String name);

}
