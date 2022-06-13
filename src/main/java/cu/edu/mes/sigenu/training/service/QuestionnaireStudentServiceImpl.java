package cu.edu.mes.sigenu.training.service;


import cu.edu.mes.sigenu.training.core.model.QuestionnarieStudent;
import cu.edu.mes.sigenu.training.core.repository.QuestionnaireStudentRepository;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireStudentServiceImpl implements QuestionnaireStudentService {

    @Autowired
    private QuestionnaireStudentRepository questionnaireStudentRepository;

    @Override
    public List<QuestionnarieStudent> listAllByStudent(String sigenuId) {
        return questionnaireStudentRepository.findAllByStudentSigenuId(sigenuId);
    }

    @Override
    public QuestionnarieStudent findById(Integer id) {
        if (questionnaireStudentRepository.existsById(id))
            return questionnaireStudentRepository.findById(id).get();
        else
            return new QuestionnarieStudent();
    }

    /*@Override
    public QuestionnarieStudent findByStudentSigenuId(String sigenuId) {
        return questionnaireStudentRepository.findByStudentSigenuId(sigenuId);
    }*/

    @Override
    public QuestionnarieStudent save(QuestionnarieStudent questionnarieStudent) {
        return questionnaireStudentRepository.save(questionnarieStudent);
    }

    @Override
    public QuestionnarieStudent update(QuestionnarieStudent questionnarieStudent) {
        QuestionnarieStudent itemDB = findById(questionnarieStudent.getId());
        itemDB.setStudentSigenuId(questionnarieStudent.getStudentSigenuId());
        itemDB.setQuestionnarieId(questionnarieStudent.getQuestionnarieId());
        itemDB.setDoneDate(questionnarieStudent.getDoneDate());
        return questionnaireStudentRepository.save(itemDB);
    }

    @Override
    public void delete(Integer id) {
        questionnaireStudentRepository.deleteById(id);

    }
}
