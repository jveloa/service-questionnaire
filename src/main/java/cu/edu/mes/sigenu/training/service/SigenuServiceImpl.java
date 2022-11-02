package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.service.SigenuService;
import cu.edu.mes.sigenu.training.core.utils.Client;
import cu.edu.mes.subsystem.student.vo.StudentVO;
import cu.edu.mes.vo.CareerVO;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SigenuServiceImpl implements SigenuService {

    @Override
    public String getStudentIdByIdentification(String identification) {
        try {
            return (String) ((ArrayList) Client.getStudentReportCatalog()
                                               .getIdStudentByIdentificacion(identification)).get(0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public StudentVO getStudentBySigenuId(String studentId) {
        try {
            return Client.getStudentSubsystem().getStudent(studentId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<CareerVO> getCareersSigenu() {
        try {
          List<CareerVO> careerVOList = (ArrayList<CareerVO>) Client.getCareerCatalog().getAllActive();
          return (ArrayList<CareerVO>) careerVOList;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

}
