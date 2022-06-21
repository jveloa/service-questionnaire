package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.dto.question.ResponsabilityReportDto;
import cu.edu.mes.sigenu.training.core.repository.CustomRepository;
import cu.edu.mes.sigenu.training.core.service.ReportService;
import cu.edu.mes.sigenu.training.core.utils.Client;
import cu.edu.mes.subsystem.student.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CustomRepository customRepository;

    @Override
    public List<ResponsabilityReportDto> responsabilityReport(Integer year) {
        List<Object[]> list = customRepository.responsibilityReport(year);
        List<ResponsabilityReportDto> listReport = new ArrayList<>();

        for (int i = 0; i < list.size(); i += 3) {
            StudentVO studentSigenu = getInfoStudent(list.get(i)[0].toString());
            ResponsabilityReportDto item = ResponsabilityReportDto.builder()
                                                                  .name((studentSigenu.getName() +" "
                                                                            + studentSigenu.getLastName())
                                                                                  .replace("  "," "))
                                                                  .studentSigenuId(list.get(i)[0].toString())
                                                                  .questionInterest(list.get(i)[1].toString())
                                                                  .answerInterest(list.get(i)[2].toString())
                                                                  .questionExp(list.get(i + 1)[1].toString())
                                                                  .answerExp(list.get(i + 1)[2].toString())
                                                                  .questionOrg(list.get(i + 2)[1].toString())
                                                                  .answerOrg(list.get(i + 2)[2].toString())
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
