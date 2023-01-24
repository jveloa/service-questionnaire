package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.GroupQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface GroupQuestionRepository extends JpaRepository<GroupQuestion, Integer>, JpaSpecificationExecutor<GroupQuestion> {

    GroupQuestion findByNameGroup(String nameGroup);
    
    @Query(nativeQuery = true, value= "SELECT MAX(organization_order) organizationOrder FROM group_question")
    int lastOrder();
}
