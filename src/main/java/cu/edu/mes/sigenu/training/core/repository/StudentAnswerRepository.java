package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentAnswerRepository extends JpaRepository <StudentAnswer,Integer>, JpaSpecificationExecutor<StudentAnswer> {
	List<StudentAnswer> findAllByStudentSigenuId(String sigenuId);

	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "

			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 8 and q.groupQuestionId = 2 order by sa.studentSigenuId")
	List<StudentAnswer> findStudentsCorrectAnswerInterpretation(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 9 and q.groupQuestionId = 2 order by sa.studentSigenuId")
	List<StudentAnswer> findStudentsWrongAnswerInterpretation(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and qa.questionId = 91")
	List<StudentAnswer> findStudentsWhoEnterCareerBecauseTheyLikeIt(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and qa.questionId = 93")
	List<StudentAnswer> findStudentsWhoEnterCareerBecauseTheyPleaseParents(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 7 and q.groupQuestionId = 17 order by sa.studentSigenuId")
	List<StudentAnswer> findStudentsWhoNeverMadeContest(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and q.id = 115")
	List<StudentAnswer> findStudentsWhoMadeMathContest(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and q.id = 116")
	List<StudentAnswer> findStudentsWhoMadeSpanishContest(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and q.id = 117")
	List<StudentAnswer> findStudentsWhoMadeChemistryContest(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and q.id = 118")
	List<StudentAnswer> findStudentsWhoMadeBiologyContest(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and q.id = 119")
	List<StudentAnswer> findStudentsWhoMadeInformaticsContest(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and q.id = 120")
	List<StudentAnswer> findStudentsWhoMadeEnglishContest(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and q.id = 121")
	List<StudentAnswer> findStudentsWhoMadePoliticCultureContest(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and q.id = 122")
	List<StudentAnswer> findStudentsWhoMadePhysicsContest(int year);


	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and q.id = 123")
	List<StudentAnswer> findStudentsWhoMadeHistoryContest(int year);

	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "inner join Questionnaire qu on qu.id = qs.questionnarieId "
			+ "where year(qs.doneDate) = ?1 and qu.id = ?2 and a.id = 69 and q.groupQuestionId = 8")
	List<StudentAnswer> studentNotComputer(int year,int questionnarieId);

	@Query( nativeQuery = true,
			value = "SELECT *" +
					" from student_answer" +
					" join question_answer  on student_answer.question_answer_id = question_answer.id " +
					" join question on question_answer.question_id = question.id" +
					" join answer on question_answer.answer_id = answer.id" +
					" join group_question on  question.group_question_id = group_question.id" +
					" join questionnarie_student on student_answer.student_sigenu_id = questionnarie_student.student_sigenu_id" +
					" join questionnaire on questionnaire.id = questionnarie_student.questionnarie_id " +
					" where (question.question = 'Tienes interés en apoyar en la dirección de las organizaciones estudiantiles '" +
					" or question.question = 'Experiencias en dirección de FEEM o UJC'" +
					" or question.question = 'Organización política a la que perteneces' )" +
					" and date_part('year',questionnarie_student.done_date) = :year and questionnaire.id = :questionnarieId " +
					" order by student_answer.student_sigenu_id")
	public List<StudentAnswer> responsibilityReport(@Param("year") Integer year, @Param("questionnarieId") Integer questionnarieId);

	@Query(nativeQuery = true,
			value = "SELECT *" +
					" from student_answer" +
					" join question_answer on question_answer.id = student_answer.question_answer_id" +
					" join question on question.id = question_answer.question_id" +
					" join answer on answer.id = question_answer.answer_id" +
					" join questionnarie_student on student_answer.student_sigenu_id = questionnarie_student.student_sigenu_id" +
					" join group_question on question.group_question_id = group_question.id" +
					" join questionnaire on questionnaire.id = questionnarie_student.questionnarie_id " +
					" where answer.answer = 'Si' and" +
					" group_question.name_group = 'Deportes' and" +
					" date_part('year',questionnarie_student.done_date) = :year and questionnaire.id = :questionnarieId " +
					" order by student_answer.student_sigenu_id")
	public List<StudentAnswer> studentSportList(@Param("year") Integer year, @Param("questionnarieId") Integer questionnarieId);


	@Query(nativeQuery = true,
			value = " select *" +
					" from student_answer " +
					" join question_answer on question_answer.id = student_answer.question_answer_id " +
					" join question on question.id = question_answer.question_id" +
					" join answer on answer.id = question_answer.answer_id" +
					" join questionnarie_student on student_answer.student_sigenu_id = questionnarie_student.student_sigenu_id" +
					" join group_question on question.group_question_id = group_question.id" +
					" join questionnaire on questionnaire.id = questionnarie_student.questionnarie_id " +
					" where answer.answer = 'Si' and questionnaire.id = :questionnarieId and " +
					" group_question.name_group = 'Manifestaciones artísticas' and" + " " +
					" date_part('year',questionnarie_student.done_date) = :year" +
					" order by student_answer.student_sigenu_id")
	public List<StudentAnswer> studentArtList(@Param("year") Integer year,@Param("questionnarieId") Integer questionnarieId);

	@Query(nativeQuery = true,
			value = "SELECT *" +
					" from student_answer\n" +
					" join question_answer on student_answer.question_answer_id = question_answer.id" +
					" join question on question_answer.question_id = question.id" +
					" join answer on question_answer.answer_id = answer.id" +
					" join group_question on  question.group_question_id = group_question.id" +
					" join questionnarie_student on student_answer.student_sigenu_id = questionnarie_student.student_sigenu_id " +
					" join questionnaire on questionnaire.id = questionnarie_student.questionnarie_id " +
					" where questionnaire.id = :questionnarieId and date_part('year',questionnarie_student.done_date) = :year and" +
					" answer.answer = 'Si' and " +
					"(group_question.name_group = 'Manifestaciones artísticas' " +
					" or group_question.name_group = 'Deportes')")
	public List<StudentAnswer> deportArtListByStudent(@Param("year") Integer year,@Param("questionnarieId") Integer questionnarieId);

	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 73 and q.id = 48 or year(qs.doneDate) = ?1 and a.id = 73 and q.id = 49 or year(qs.doneDate) = ?1 and a.id = 73 and q.id = 50 order by sa.studentSigenuId")
	List<StudentAnswer> findStudentsWhoNotRead(int year);

	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 7 and q.id = 48 or year(qs.doneDate) = ?1 and a.id = 7 and q.id = 49 or year(qs.doneDate) = ?1 and a.id = 7 and q.id = 50 order by sa.studentSigenuId")
	List<StudentAnswer> findStudentsWhoRegularyRead(int year);

	@Query("select sa from StudentAnswer sa "
			+ "inner join QuestionAnswer qa on sa.questionAnswerId = qa.id "
			+ "inner join Question q on qa.questionId = q.id "
			+ "inner join Answer a on qa.answerId = a.id "
			+ "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
			+ "where year(qs.doneDate) = ?1 and a.id = 6 and q.id = 48 or year(qs.doneDate) = ?1 and a.id = 6 and q.id = 49 or year(qs.doneDate) = ?1 and a.id = 6 and q.id = 50  order by sa.studentSigenuId")
	List<StudentAnswer> findStudentsWhoOnlyReadSchool(int year);




}
