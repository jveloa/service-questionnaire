package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.Answer;
import cu.edu.mes.sigenu.training.core.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer>, JpaSpecificationExecutor<Answer> {



    @Query("select a from Answer a where a.id = ?1 ")
    Answer findIAnswerName(int answerId);



}
