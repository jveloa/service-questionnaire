package cu.edu.mes.sigenu.training.core.service;

import java.util.List;

import cu.edu.mes.sigenu.training.core.model.Question;

public interface QuestionService {
	List<Question> listAll();
	
	Question findByName(String name);
	
	Question findById(Long id);
	
	Question save(Question faq);
	
	Question update(Question faq);
	
	void delete(Long id);

	
}
