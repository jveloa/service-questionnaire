package cu.edu.mes.sigenu.training.core.repository;


import cu.edu.mes.sigenu.training.core.model.QuestionnarieStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionnaireStudentRepository extends JpaRepository<QuestionnarieStudent, Integer>, JpaSpecificationExecutor<QuestionnarieStudent> {

    List<QuestionnarieStudent> findAllByStudentSigenuId(String sigenuId);
    
    @Query("select qs from QuestionnarieStudent qs "
            + "inner join Questionnaire q on q.id = qs.questionnarieId "
            + "where year(qs.doneDate) = ?1 and q.id = ?2")
    List<QuestionnarieStudent> findAllByDate(int year, int id);

    @Query("select qs from QuestionnarieStudent qs where year(qs.doneDate) = ?1")
    List<QuestionnarieStudent> findAllByDoneDate(int year);
}
