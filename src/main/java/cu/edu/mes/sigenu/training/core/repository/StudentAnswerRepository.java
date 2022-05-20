package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentAnswerRepository extends JpaRepository <StudentAnswer,Integer>, JpaSpecificationExecutor<StudentAnswer> {
    List<StudentAnswer> findAllByStudentSigenuId(String sigenuId);
}
