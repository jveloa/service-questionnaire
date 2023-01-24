package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.model.Answer;
import cu.edu.mes.sigenu.training.core.repository.AnswerRepository;
import cu.edu.mes.sigenu.training.core.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> listAll() {
        return answerRepository.findAll();
    }

    @Override
    public Answer findById(Integer id) {
        if (answerRepository.existsById(id))
            return answerRepository.findById(id).get();
        else
            return new Answer();
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer update(Answer answer) {
        Answer aux = findById(answer.getId());
        aux.setNameAnswer(answer.getNameAnswer());
        return answerRepository.save(aux);
    }

    @Override
    public void delete(Integer id) {
        answerRepository.delete(new Answer(id));
    }
}
