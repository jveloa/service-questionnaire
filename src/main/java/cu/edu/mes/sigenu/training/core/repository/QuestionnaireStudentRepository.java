package cu.edu.mes.sigenu.training.core.repository;

import cu.edu.mes.sigenu.training.core.model.QuestionnaireStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionnaireStudentRepository extends JpaRepository<QuestionnaireStudent, Integer>, JpaSpecificationExecutor<QuestionnaireStudent> {

	@Query("select qs.studentSigenuId from QuestionnaireStudent qs "
			+ "inner join Questionnaire q on qs.questionnaireId = q.id "
			+ "where q.id = ?1")
	List<String> findAllByQuestionnaire(Integer questionnaireId);
    
    @Query("select qs from QuestionnaireStudent qs "
            + "inner join Questionnaire q on q.id = qs.questionnaireId "
            + "where year(qs.doneDate) = ?1 and q.id = ?2")
    List<QuestionnaireStudent> findAllByDate(int year, int questionnaireId);

    @Query("select qs from QuestionnaireStudent qs "
    		+ "where year(qs.doneDate) = ?1")
    List<QuestionnaireStudent> findAllByDoneDate(int year);
}
