package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.model.Answer;
import cu.edu.mes.sigenu.training.core.model.Question;
import cu.edu.mes.sigenu.training.core.model.QuestionAnswer;
import cu.edu.mes.sigenu.training.core.repository.QuestionAnswerRepository;
import cu.edu.mes.sigenu.training.core.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;


    @Override
    public List<QuestionAnswer> listAll() {
        return questionAnswerRepository.findAll();
    }

    @Override
    public QuestionAnswer findById(Integer id) {
        if (questionAnswerRepository.existsById(id))
            return questionAnswerRepository.findById(id).get();
        else
            return new QuestionAnswer();
    }

    @Override
    public QuestionAnswer save(QuestionAnswer questionAnswer) {
        return questionAnswerRepository.save(questionAnswer);
    }

    @Override
    public QuestionAnswer update(QuestionAnswer questionAnswer) {
        QuestionAnswer aux = findById(questionAnswer.getId());
        aux.setQuestionId(questionAnswer.getQuestionId());
        aux.setAnswerId(questionAnswer.getAnswerId());
        aux.setIsCanceled(questionAnswer.getIsCanceled());
        return questionAnswerRepository.save(aux);
    }

    @Override
    public void delete(Integer id) {
    	questionAnswerRepository.delete(new QuestionAnswer(id));
    }

    @Override
    public QuestionAnswer findByQuestionIdAnswerId(Question questionId, Answer answerId){
        return questionAnswerRepository.getByQuestionIdAndAnswerId(questionId, answerId);
    }

    @Override
    public List<QuestionAnswer> getByQuestionId(Question questionId) {
        return questionAnswerRepository.getByQuestionId(questionId);
    }
}
