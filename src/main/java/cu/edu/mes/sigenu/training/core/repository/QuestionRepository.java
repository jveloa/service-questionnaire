package cu.edu.mes.sigenu.training.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cu.edu.mes.sigenu.training.core.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question> {
	
	Question findByQuestion(String question);
}

