package cu.edu.mes.sigenu.training.core.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import cu.edu.mes.sigenu.training.core.model.Question;
import org.springframework.data.repository.query.Param;

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
			+ "inner join Questionnaire qu on qu.id = qs.questionnarieId "
			+ "where date_part('year',qs.doneDate) = ?1 and q.groupQuestionId = 11 and qu.id = ?2")
	List<Question> findIQuestionsByGroupQuestion(int year,int questionnarieId);

	@Query("select distinct qs.doneDate from QuestionnarieStudent qs "
			+ "inner join Questionnaire q on q.id = qs.questionnarieId.id "
			+ "where q.id = ?1 order by qs.doneDate")
	List<String> getAllYear(int questionnarieId);

	@Query(value = " select count(q.id) from student_answer sa inner join question_answer qa on sa.question_answer_id = qa.id  "
			+ "inner join question q on qa.question_id = q.id inner join answer a on qa.answer_id = a.id "
			+ "inner join questionnarie_student qs on qs.student_sigenu_id = sa.student_sigenu_id "
			+ "inner join questionnaire qu on qu.id = qs.questionnarie_id "
			+ "where date_part('year',qs.done_date) = ?1 and q.id = ?2 and q.group_question_id = 11 and qu.id = ?3", nativeQuery=true)
	int totalQuestionByStudyForms(int year,int questionId,int questionnarieId);

	@Query(value = " select count(q.id) from student_answer sa inner join question_answer qa on sa.question_answer_id = qa.id  "
			+ "inner join question q on qa.question_id = q.id inner join answer a on qa.answer_id = a.id "
			+ "inner join questionnarie_student qs on qs.student_sigenu_id = sa.student_sigenu_id "
			+ "inner join questionnaire qu on qu.id = qs.questionnarie_id "
			+ "where date_part('year',qs.done_date) = ?1 and qu.id = ?2 and q.id = 56 and q.group_question_id = 4", nativeQuery=true)
	int totalQuestionByStudyHours(int year,int questionnarieId);

	List<Question> getAllByQuestionCarrerListIsNullOrderById();

	List<Question> getAllByQuestionCarrerListIsNotNullOrderById();

    @Query( nativeQuery = true,
            value = "select * " +
                "from question " +
                "join questionnaire_question on questionnaire_question.question_id = question.id " +
                "join questionnaire on questionnaire.id = questionnaire_question.questionnaire_id " +
                "join group_question on group_question.id = question.group_question_id " +
                "where questionnaire.id = :questionnarieId " +
                "order by group_question.organization_order "
    )
    List<Question> findByQuestionnaireIdOrderByGroup(@Param("questionnarieId") Integer Id);
}


