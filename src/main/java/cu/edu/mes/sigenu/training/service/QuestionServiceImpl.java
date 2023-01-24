package cu.edu.mes.sigenu.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.mes.sigenu.training.core.model.Question;
import cu.edu.mes.sigenu.training.core.repository.QuestionRepository;
import cu.edu.mes.sigenu.training.core.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public List<Question> listAll() {
		return questionRepository.findAll();
	}

	@Override
	public Question findById(Integer id) {
		if (questionRepository.existsById(id))
    		return questionRepository.findById(id).get();
    	else
    		return new Question();
	}

	@Override
	public Question findByNameQuestion(String nameQuestion) {
		return questionRepository.findByNameQuestion(nameQuestion);
	}

	@Override
	public Question save(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public Question update(Question question) {
		Question aux = findById(question.getId());
		aux.setNameQuestion(question.getNameQuestion());
		aux.setGroupQuestionId(question.getGroupQuestionId());
		aux.setIsEvaluationQuestion(question.getIsEvaluationQuestion());
		aux.setIsSpecificQuestion(question.getIsSpecificQuestion());
		aux.setIsCanceled(question.getIsCanceled());
		return questionRepository.save(aux);
	}

	@Override
	public void delete(Integer id) {
		questionRepository.delete(new Question(id));
	}

	@Override
	public List<Question> getQuestionByQuestionnaire(Integer questionnaireId) {
		return questionRepository.findByQuestionnaireIdOrderByGroup(questionnaireId);
	}
}
