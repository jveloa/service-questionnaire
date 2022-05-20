package cu.edu.mes.sigenu.training.core.service;


import cu.edu.mes.sigenu.training.core.model.StudentAnswer;

import java.util.List;

public interface StudentAnswerService {
    List<StudentAnswer> listAllByStudent(String sigenuId);

    StudentAnswer findById(Integer id);

    List<StudentAnswer> save(List<StudentAnswer> studentAnswer);

    StudentAnswer update(StudentAnswer studentAnswer);

    void delete(Integer id);
}

