package cu.edu.mes.sigenu.training.core.service;

import cu.edu.mes.subsystem.student.vo.StudentVO;

import java.util.Collection;

public interface SigenuService {

    String getStudentIdByIdentification(String identification);
    StudentVO getStudentBySigenuId(String sigenuId);
}
