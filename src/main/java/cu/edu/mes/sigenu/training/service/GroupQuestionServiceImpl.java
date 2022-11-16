package cu.edu.mes.sigenu.training.service;


import cu.edu.mes.sigenu.training.core.model.GroupQuestion;
import cu.edu.mes.sigenu.training.core.repository.GroupQuestionRepository;
import cu.edu.mes.sigenu.training.core.service.GroupQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupQuestionServiceImpl implements GroupQuestionService {

    @Autowired
    private GroupQuestionRepository groupQuestionRepository;

    @Override
    public List<GroupQuestion> listAll() {
        return groupQuestionRepository.findAll();
    }

    @Override
    public GroupQuestion findByName(String name) {
        return groupQuestionRepository.findByNameGroup(name);
    }

    @Override
    public GroupQuestion findById(Integer id) {
        if (groupQuestionRepository.existsById(id))
          return groupQuestionRepository.findById(id).get();
        else
            return new GroupQuestion();
    }

    @Override
    public GroupQuestion save(GroupQuestion groupQuestion) {
        return groupQuestionRepository.save(groupQuestion);
    }

    @Override
    public GroupQuestion update(GroupQuestion groupQuestion) {
        GroupQuestion itemDB = findById(groupQuestion.getId());
        itemDB.setDescription(groupQuestion.getDescription());
        itemDB.setNameGroup(groupQuestion.getNameGroup());
        return groupQuestionRepository.save(itemDB);
    }

    @Override
    public void delete(Integer id) {
        groupQuestionRepository.deleteById(id);
    }
}
