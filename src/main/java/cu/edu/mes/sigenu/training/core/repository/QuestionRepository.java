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

	@Query("select distinct q from Question q inner join QuestionAnswer qa on q.id = qa.questionId "
			+ "inner join StudentAnswer sa on qa.id = sa.questionAnswerId "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and q.groupQuestionId = 17")
	List<Question> findContestQuestions(int year);

	@Query("select distinct q from Question q inner join QuestionAnswer qa on q.id = qa.questionId "
			+ "inner join StudentAnswer sa on qa.id = sa.questionAnswerId "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where date_part('year',qs.doneDate) = ?1 and q.groupQuestionId = 11")
	List<Question> findIQuestionsByGroupQuestion(int year);

	@Query(value = " select count(q.id) from student_answer sa inner join question_answer qa on sa.question_answer_id = qa.id  "
			+ "inner join question q on qa.question_id = q.id inner join answer a on qa.answer_id = a.id "
			+ "inner join questionnarie_student qs on qs.student_sigenu_id = sa.student_sigenu_id "
			+ "where date_part('year',qs.done_date) = ?1 and q.id = ?2 and q.group_question_id = 11 group by q.id", nativeQuery=true)
	int totalQuestionByStudyForms(int year,int questionId);
}


