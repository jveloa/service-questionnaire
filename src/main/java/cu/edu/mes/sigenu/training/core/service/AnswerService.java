package cu.edu.mes.sigenu.training.core.service;

import cu.edu.mes.sigenu.training.core.model.Answer;
import cu.edu.mes.sigenu.training.core.model.GroupQuestion;

import java.util.List;

public interface AnswerService {
    List<Answer> listAll();

    Answer findById(Integer id);

    Answer save(Answer answer);

    Answer update(Answer answer);

    void delete(Integer id);
}
