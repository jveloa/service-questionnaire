package cu.edu.mes.sigenu.training.core.service;

import java.util.List;

import cu.edu.mes.sigenu.training.core.model.Question;

public interface QuestionService {
	List<Question> listAll();

	List<Question> listAllWithoutCareer();

	List<Question> listAllWithCareer();

	List<Question> getQuestionByQuestionnaire(Integer questionnaireId);

	Question findByName(String name);
	
	Question findById(Integer id);
	
	Question save(Question faq);
	
	Question update(Question faq);
	
	void delete(Integer id);

	
}
