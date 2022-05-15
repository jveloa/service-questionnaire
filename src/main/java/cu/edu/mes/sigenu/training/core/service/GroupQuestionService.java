package cu.edu.mes.sigenu.training.core.service;

import cu.edu.mes.sigenu.training.core.model.GroupQuestion;
import cu.edu.mes.sigenu.training.core.model.Question;

import java.util.List;

public interface GroupQuestionService {
    List<GroupQuestion> listAll();

    GroupQuestion findByName(String name);

    GroupQuestion findById(Integer id);

    GroupQuestion save(GroupQuestion groupQuestion);

    GroupQuestion update(GroupQuestion groupQuestion);

    void delete(Integer id);


}
