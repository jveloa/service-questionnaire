package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.Answer;
import cu.edu.mes.sigenu.training.core.model.Question;
import cu.edu.mes.sigenu.training.core.model.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Integer>, JpaSpecificationExecutor<QuestionAnswer> {

     @Query(value = " select count(qa.id) from student_answer sa inner join question_answer qa on sa.question_answer_id = qa.id  "
            + "inner join question q on qa.question_id = q.id inner join answer a on qa.answer_id = a.id "
            + "inner join questionnarie_student qs on qs.student_sigenu_id = sa.student_sigenu_id "
            + "where date_part('year',qs.done_date) = ?1 and q.id = ?2 and a.id = ?3 and q.group_question_id = 11  group by qa.id", nativeQuery=true)
    int totalAnswerByQuestion(int year,int questionId, int answerId);

    @Query("select distinct qa from QuestionAnswer qa inner join  Question q on qa.questionId = q.id"
            + " inner join StudentAnswer sa on qa.id = sa.questionAnswerId where q.id = ?1 ")
    List<QuestionAnswer> findQuestionAnswerByQuestion(int questionId);

    @Query("select distinct qa from QuestionAnswer qa inner join  Question q on qa.questionId = q.id"
            + " inner join StudentAnswer sa on qa.id = sa.questionAnswerId where q.id = 56 ")
    List<QuestionAnswer> findQuestionAnswerByQuestionByStudyHours();

    @Query(value = " select count(qa.id) from student_answer sa inner join question_answer qa on sa.question_answer_id = qa.id  "
            + "inner join question q on qa.question_id = q.id inner join answer a on qa.answer_id = a.id "
            + "inner join questionnarie_student qs on qs.student_sigenu_id = sa.student_sigenu_id "
            + "where date_part('year',qs.done_date) = ?1 and q.id = 56 and a.id = ?2 and q.group_question_id = 4  group by qa.id", nativeQuery=true)
    int totalAnswerByQuestionByStudyHours(int year,int answerId);

    QuestionAnswer getByQuestionIdAndAnswerId(Question questionId, Answer answerId);








}
