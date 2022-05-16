package cu.edu.mes.sigenu.training.core.service;

import cu.edu.mes.sigenu.training.core.model.GroupQuestion;
import cu.edu.mes.sigenu.training.core.model.QuestionCarrer;

import java.util.List;

public interface QuestionCarrerService {
    List<QuestionCarrer> listAll();

    QuestionCarrer findById(Integer id);

    QuestionCarrer save(QuestionCarrer questionCarrer);

    QuestionCarrer update(QuestionCarrer questionCarrer);

    void delete(Integer id);
}
