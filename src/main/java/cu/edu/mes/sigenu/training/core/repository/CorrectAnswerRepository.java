package cu.edu.mes.sigenu.training.core.repository;

import com.google.inject.internal.cglib.proxy.$InvocationHandler;
import cu.edu.mes.sigenu.training.core.model.CorrectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer,Integer>, JpaSpecificationExecutor<CorrectAnswer> {

}
