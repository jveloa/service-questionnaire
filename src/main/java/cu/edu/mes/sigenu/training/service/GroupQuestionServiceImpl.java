package cu.edu.mes.sigenu.training.service;


import cu.edu.mes.sigenu.training.core.model.GroupQuestion;
import cu.edu.mes.sigenu.training.core.repository.GroupQuestionRepository;
import cu.edu.mes.sigenu.training.core.service.GroupQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupQuestionServiceImpl implements GroupQuestionService {

    @Autowired
    private GroupQuestionRepository groupQuestionRepository;

    @Override
    public List<GroupQuestion> listAll() {
        return groupQuestionRepository.findAll(Sort.by(Sort.Direction.ASC,"organizationOrder"));
    }

    @Override
    public GroupQuestion findById(Integer id) {
        if (groupQuestionRepository.existsById(id))
          return groupQuestionRepository.findById(id).get();
        else
            return new GroupQuestion();
    }
    
    @Override
    public GroupQuestion findByNameGroup(String name) {
        return groupQuestionRepository.findByNameGroup(name);
    }

    @Override
    public GroupQuestion save(GroupQuestion groupQuestion) {
        return groupQuestionRepository.save(groupQuestion);
    }

    @Override
    public GroupQuestion update(GroupQuestion groupQuestion) {
        GroupQuestion aux = findById(groupQuestion.getId());
        aux.setNameGroup(groupQuestion.getNameGroup());
        aux.setDescription(groupQuestion.getDescription());
        aux.setOrganizationOrder(groupQuestion.getOrganizationOrder());
        return groupQuestionRepository.save(aux);
    }

    @Override
    public void delete(Integer id) {
    	groupQuestionRepository.delete(new GroupQuestion(id));
    }

	@Override
	public int lastOrder() {
		return groupQuestionRepository.lastOrder();
	}
}
