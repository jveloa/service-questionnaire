package cu.edu.mes.sigenu.training.core.service;

import cu.edu.mes.sigenu.training.core.model.GroupQuestion;

import java.util.List;

public interface GroupQuestionService {
	
    List<GroupQuestion> listAll();
    
    GroupQuestion findById(Integer id);

    GroupQuestion findByNameGroup(String nameGroup);

    GroupQuestion save(GroupQuestion groupQuestion);

    GroupQuestion update(GroupQuestion groupQuestion);

    void delete(Integer id);
}
