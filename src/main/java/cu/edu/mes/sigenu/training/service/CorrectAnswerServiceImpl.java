package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.model.CorrectAnswer;
import cu.edu.mes.sigenu.training.core.repository.CorrectAnswerRepository;
import cu.edu.mes.sigenu.training.core.service.CorrectAnswerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorrectAnswerServiceImpl implements CorrectAnswerService {

    @Autowired
    private CorrectAnswerRepository correctAnswerRepository;

	@Override
	public List<CorrectAnswer> listAll() {
		return correctAnswerRepository.findAll();
	}

	@Override
	public CorrectAnswer findById(Integer id) {
		if (correctAnswerRepository.existsById(id))
            return correctAnswerRepository.findById(id).get();
        else
        	return new CorrectAnswer();
	}

	@Override
	public CorrectAnswer save(CorrectAnswer correctAnswer) {
		return correctAnswerRepository.save(correctAnswer);
	}

	@Override
	public void delete(Integer id) {
		correctAnswerRepository.delete(new CorrectAnswer(id));
	}
}
