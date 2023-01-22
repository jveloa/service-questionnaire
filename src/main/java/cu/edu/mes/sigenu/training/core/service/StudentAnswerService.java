package cu.edu.mes.sigenu.training.core.service;

import cu.edu.mes.sigenu.training.core.model.StudentAnswer;

import java.util.List;

public interface StudentAnswerService {
	
    List<StudentAnswer> listAllByStudentSigenuId(String studentSigenuId);

    StudentAnswer findById(Integer id);

    List<StudentAnswer> save(List<StudentAnswer> studentAnswerList);
}

