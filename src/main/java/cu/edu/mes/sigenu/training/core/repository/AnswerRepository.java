package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer,Integer>, JpaSpecificationExecutor<Answer> {

    @Query("select a from Answer a "
            + "inner join QuestionAnswer qa on a.id = qa.answerId "
            + "inner join StudentAnswer sa on qa.id = sa.questionAnswerId "
            + "inner join QuestionnaireStudent qs on qs.studentSigenuId = sa.studentSigenuId "
            + "inner join Questionnaire q on q.id = qs.questionnaireId "
            + "where a.id = ?1 and q.id = ?2")
    Answer findAnswer(int answerId, int questionnarieId);
}
