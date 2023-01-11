package cu.edu.mes.sigenu.training.service;

import java.util.List;

import cu.edu.mes.sigenu.training.core.model.QuestionAnswer;
import cu.edu.mes.sigenu.training.core.model.QuestionCarrer;
import cu.edu.mes.sigenu.training.core.service.QuestionAnswerService;
import cu.edu.mes.sigenu.training.core.service.QuestionCarrerService;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.mes.sigenu.training.core.model.Question;
import cu.edu.mes.sigenu.training.core.repository.QuestionRepository;
import cu.edu.mes.sigenu.training.core.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
    private QuestionAnswerService questionAnswerService;

	@Autowired
    private QuestionCarrerService questionCarrerService;
	
	@Override
	public List<Question> listAll() {
		return questionRepository.findAll();
	}

    @Override
    public List<Question> listAllWithoutCareer() {
        return questionRepository.getAllByQuestionCarrerListIsNullOrderById();
    }

    @Override
    public List<Question> listAllWithCareer() {
        return questionRepository.getAllByQuestionCarrerListIsNotNullOrderById();
    }

    @Override
    public List<Question> getQuestionByQuestionnaire(Integer questionnaireId) {
        return questionRepository.findByQuestionnaireIdOrderByGroup(questionnaireId);
    }

    @Override
	public Question findById(Integer id) {
		return questionRepository.findById(id).get();
	}

	@Override
	public Question save(Question item) {
		return questionRepository.save(item);
	}

	@Override
	public Question update(Question item) {
		Question itemInDb = findById(item.getId());
		itemInDb.setQuestion(item.getQuestion());
		itemInDb.setGroupQuestionId(item.getGroupQuestionId());
		return questionRepository.save(itemInDb);
	}

	@Override
	public void delete(Integer id) throws Exception {
        Question question = findById(id);
        if(question.getQuestionnaireList().size() != 0){
            throw new Exception();
        }
        for(QuestionAnswer questionAnswer:question.getQuestionAnswerList()){
            if(questionAnswer.getStudentAnswerList().size()!=0){
                throw new Exception();
            }
        }
        for(QuestionAnswer questionAnswer:question.getQuestionAnswerList()){
            questionAnswerService.delete(questionAnswer.getId());
        }
        if(!question.getQuestionCarrerList().isEmpty()) {
            questionCarrerService.delete(question.getQuestionCarrerList().get(0).getId());
        }
        questionRepository.deleteById(id);
	}


	@Override
	public Question findByName(String name) {
		return questionRepository.findByQuestion(name);
	}


}
