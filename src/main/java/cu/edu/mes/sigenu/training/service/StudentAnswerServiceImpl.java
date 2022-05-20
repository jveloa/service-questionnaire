package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.model.StudentAnswer;
import cu.edu.mes.sigenu.training.core.repository.StudentAnswerRepository;
import cu.edu.mes.sigenu.training.core.service.StudentAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentAnswerServiceImpl implements StudentAnswerService {

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Override
    public List<StudentAnswer> listAllByStudent(String sigenuId) {
        return studentAnswerRepository.findAllByStudentSigenuId(sigenuId);
    }

    @Override
    public StudentAnswer findById(Integer id) {
        if (studentAnswerRepository.existsById(id))
            return studentAnswerRepository.findById(id).get();
        else
            return new StudentAnswer();
    }

    @Override
    public List<StudentAnswer> save(List<StudentAnswer> studentAnswer) {
        return studentAnswerRepository.saveAll(studentAnswer);
    }

    @Override
    public StudentAnswer update(StudentAnswer studentAnswer) {
        StudentAnswer itemDb = findById(studentAnswer.getId());
        itemDb.setStudentSigenuId(studentAnswer.getStudentSigenuId());
        itemDb.setQuestionAnswerId(studentAnswer.getQuestionAnswerId());
        return studentAnswerRepository.save(itemDb);
    }

    @Override
    public void delete(Integer id) {
        studentAnswerRepository.deleteById(id);
    }
}
