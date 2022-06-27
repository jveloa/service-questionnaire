package cu.edu.mes.sigenu.training.service;


import cu.edu.mes.sigenu.training.core.dto.question.StudentNotComputerDto;
import cu.edu.mes.sigenu.training.core.repository.CustomRepository;
import cu.edu.mes.sigenu.training.core.service.ReportTwoService;
import cu.edu.mes.sigenu.training.core.utils.Client;
import cu.edu.mes.subsystem.student.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportTwoServiceImpl implements ReportTwoService {

    @Autowired
    private CustomRepository customRepository;

    @Override
    public List<StudentNotComputerDto> studentNotComputerList(Integer year) {
        List<String> list = customRepository.studentNotComputer(year);
        List<StudentNotComputerDto> listReport = new ArrayList<>();

        for (int i = 0; i < list.size(); i ++) {
            StudentVO studentSigenu = getInfoStudent(list.get(i));
            StudentNotComputerDto item = StudentNotComputerDto.builder()
                                                                .name((studentSigenu.getName() +" "
                                                                        + studentSigenu.getLastName())
                                                                        .replace("  "," "))
                                                                .studentSigenuId(list.get(i).toString())
                                                                .build();
            listReport.add(item);
        }

        return listReport;
    }


    public StudentVO getInfoStudent(String studentId) {
        try {
            return Client.getStudentSubsystem().getStudent(studentId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
