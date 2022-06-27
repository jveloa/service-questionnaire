package cu.edu.mes.sigenu.training.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cu.edu.mes.sigenu.training.core.model.Question;
import cu.edu.mes.sigenu.training.core.model.StudentAnswer;

public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question> {
	
	Question findByQuestion(String question);
	
	 @Query("select distinct q from Question q inner join QuestionAnswer qa on q.id = qa.questionId "
	    		+ "inner join StudentAnswer sa on qa.id = sa.questionAnswerId "
	    		+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
	    		+ "where year(qs.doneDate) = ?1 and q.groupQuestionId = 2")
	    List<Question> findInterpretativeQuestions(int year);
}

