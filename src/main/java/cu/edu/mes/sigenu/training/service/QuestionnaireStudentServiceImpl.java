package cu.edu.mes.sigenu.training.service;


import cu.edu.mes.sigenu.training.core.model.QuestionnarieStudent;
import cu.edu.mes.sigenu.training.core.repository.QuestionnaireStudentRepository;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireStudentService;
import cu.edu.mes.sigenu.training.core.service.SigenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.List;

@Service
public class QuestionnaireStudentServiceImpl implements QuestionnaireStudentService {

    @Autowired
    private QuestionnaireStudentRepository questionnaireStudentRepository;

    @Autowired
    private SigenuService sigenuService;

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

    @Override
    public QuestionnarieStudent save(QuestionnarieStudent questionnarieStudent, String identification) {
        String studentSigenu = sigenuService.getStudentIdByIdentification(identification);
        questionnarieStudent.setStudentSigenuId(studentSigenu);
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
