package cu.edu.mes.sigenu.training.core.service;

import cu.edu.mes.subsystem.student.vo.StudentVO;
import cu.edu.mes.vo.CareerVO;

import java.util.Collection;
import java.util.List;

public interface SigenuService {

    String getStudentIdByIdentification(String identification);
    StudentVO getStudentBySigenuId(String sigenuId);
    List<CareerVO> getCareersSigenu();
}
