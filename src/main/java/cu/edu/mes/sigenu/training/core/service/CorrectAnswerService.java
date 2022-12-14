package cu.edu.mes.sigenu.training.core.service;

import cu.edu.mes.sigenu.training.core.model.Answer;
import cu.edu.mes.sigenu.training.core.model.CorrectAnswer;

public interface CorrectAnswerService {
    CorrectAnswer findById(Integer id);
    CorrectAnswer save(CorrectAnswer correctAnswer);
    void deleteById(Integer Id);
}
