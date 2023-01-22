package cu.edu.mes.sigenu.training.core.service;

import java.util.List;

import cu.edu.mes.sigenu.training.core.model.Question;

public interface QuestionService {
	
	List<Question> listAll();
	
	Question findById(Integer id);
	
	Question findByNameQuestion(String nameQuestion);
	
	Question save(Question question);
	
	Question update(Question question);
	
	void delete(Integer id);

	List<Question> getQuestionByQuestionnaire(Integer questionnaireId);
}
