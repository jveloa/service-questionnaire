package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.Answer;
import cu.edu.mes.sigenu.training.core.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer>, JpaSpecificationExecutor<Answer> {



    @Query("select a from Answer a "
            + "inner join QuestionAnswer qa on a.id = qa.id "
            + "inner join StudentAnswer sa on qa.id = sa.questionAnswerId "
            + "inner join QuestionnarieStudent qs on qs.studentSigenuId = sa.studentSigenuId "
            + "inner join Questionnaire q on q.id = qs.questionnarieId where a.id = ?1 and q.id = ?2")
    Answer findIAnswerName(int answerId,int questionnarieId);



}
