package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.dto.question.DeportArtListDto;
import cu.edu.mes.sigenu.training.core.dto.question.ResponsabilityReportDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentArtDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentSportDto;
import cu.edu.mes.sigenu.training.core.repository.CustomRepository;
import cu.edu.mes.sigenu.training.core.service.ReportService;
import cu.edu.mes.sigenu.training.core.utils.Client;
import cu.edu.mes.subsystem.student.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.ArrayList;
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

    @Override
    public List<StudentSportDto> studentSportList(Integer year) {
        List<Object[]> list = customRepository.studentSportList(year);
        List<StudentSportDto> listStudent = new ArrayList<>();
        for (Object[] objects : list) {
            List<String> sports = new ArrayList<>();

            if (!listStudent.isEmpty() && listStudent.get(listStudent.size() - 1)
                                                     .getStudentSigenuId()
                                                     .equals(objects[0].toString())) {

                listStudent.get(listStudent.size() - 1)
                           .getSports()
                           .add(objects[1].toString());

            } else {
                sports.add(objects[1].toString());
                StudentSportDto item = StudentSportDto.builder()
                                                      .studentSigenuId(objects[0].toString())
                                                      .sports(sports)
                                                      .build();
                listStudent.add(item);
            }
        }
        return listStudent;
    }

    @Override
    public List<StudentArtDto> studentArtList(Integer year) {
        List<Object[]> list = customRepository.studentArtList(year);
        List<StudentArtDto> listStudent = new ArrayList<>();
        for (Object[] objects : list) {
            List<String> arts = new ArrayList<>();

            if (!listStudent.isEmpty() && listStudent.get(listStudent.size() - 1)
                                                     .getStudentSigenuId()
                                                     .equals(objects[0].toString())) {

                listStudent.get(listStudent.size() - 1)
                           .getArts()
                           .add(objects[1].toString());

            } else {
                arts.add(objects[1].toString());
                StudentArtDto item = StudentArtDto.builder()
                                                  .studentSigenuId(objects[0].toString())
                                                  .arts(arts)
                                                  .build();
                listStudent.add(item);
            }

        }
        return listStudent;
    }

    @Override
    public DeportArtListDto deportArtListByStudent(String studentSigenuId) {
        List<Object[]> list = customRepository.deportArtListByStudent(studentSigenuId);

        List<String> sports = new ArrayList<>();
        List<String> arts = new ArrayList<>();

        for (Object[] e:list) {

            if (e[0].equals("Deportes")) {
                sports.add(e[1].toString());
            } else
                arts.add(e[1].toString());

        }

        return DeportArtListDto.builder()
                               .arts(arts)
                               .sports(sports)
                               .build();

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
