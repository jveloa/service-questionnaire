package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

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
}
