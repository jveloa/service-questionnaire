package cu.edu.mes.sigenu.training.core.service;

import java.util.List;

import cu.edu.mes.sigenu.training.core.model.CorrectAnswer;

public interface CorrectAnswerService {
	
	List<CorrectAnswer> listAll();
	
    CorrectAnswer findById(Integer id);
    
    CorrectAnswer save(CorrectAnswer correctAnswer);
    
    void delete(Integer Id);
}
