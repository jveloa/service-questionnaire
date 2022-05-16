package cu.edu.mes.sigenu.training.core.service;

import cu.edu.mes.sigenu.training.core.model.GroupQuestion;
import cu.edu.mes.sigenu.training.core.model.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {
    List<QuestionAnswer> listAll();

    QuestionAnswer findById(Integer id);

    QuestionAnswer save(QuestionAnswer questionAnswer);

    QuestionAnswer update(QuestionAnswer questionAnswer);

    void delete(Integer id);
}
