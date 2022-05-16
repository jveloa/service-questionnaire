package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.model.QuestionCarrer;
import cu.edu.mes.sigenu.training.core.repository.QuestionCarrerRepository;
import cu.edu.mes.sigenu.training.core.service.QuestionCarrerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionCarrerServiceImlp implements QuestionCarrerService {

    @Autowired
    private QuestionCarrerRepository questionCarrerRepository;

    @Override
    public List<QuestionCarrer> listAll() {
        return questionCarrerRepository.findAll();
    }

    @Override
    public QuestionCarrer findById(Integer id) {
        if(questionCarrerRepository.existsById(id)){
            return questionCarrerRepository.findById(id).get();
        }else
            return new QuestionCarrer();

    }

    @Override
    public QuestionCarrer save(QuestionCarrer questionCarrer) {
        return questionCarrerRepository.save(questionCarrer);
    }

    @Override
    public QuestionCarrer update(QuestionCarrer questionCarrer) {
        QuestionCarrer itemDB = findById(questionCarrer.getId());
        itemDB.setCareerSigenuId(questionCarrer.getCareerSigenuId());
        itemDB.setQuestionId(questionCarrer.getQuestionId());
        return questionCarrerRepository.save(itemDB);
    }

    @Override
    public void delete(Integer id) {
        questionCarrerRepository.deleteById(id);
    }
}
