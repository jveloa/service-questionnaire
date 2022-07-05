package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.dto.question.DeportArtListDto;
import cu.edu.mes.sigenu.training.core.dto.question.ResponsabilityReportDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentArtDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentSportDto;
import cu.edu.mes.sigenu.training.core.model.StudentAnswer;
import cu.edu.mes.sigenu.training.core.repository.StudentAnswerRepository;
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
    private StudentAnswerRepository studentAnswerRepository;

    @Override
    public List<ResponsabilityReportDto> responsabilityReport(Integer year) {
        List<StudentAnswer> list = studentAnswerRepository.responsibilityReport(year);
        List<ResponsabilityReportDto> listReport = new ArrayList<>();

        for (int i = 0; i < list.size(); i += 3) {
            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            ResponsabilityReportDto item = ResponsabilityReportDto.builder()
                                                                  .name((studentSigenu.getName() +" "
                                                                            + studentSigenu.getLastName())
                                                                                  .replace("  "," "))
                                                                  .studentSigenuId(list.get(i).getStudentSigenuId())
                                                                  .questionInterest(list.get(i).getQuestionAnswerId().getQuestionId().getQuestion())
                                                                  .answerInterest(list.get(i).getQuestionAnswerId().getAnswerId().getAnswer())
                                                                  .questionExp(list.get(i + 1).getQuestionAnswerId().getQuestionId().getQuestion())
                                                                  .answerExp(list.get(i + 1).getQuestionAnswerId().getAnswerId().getAnswer())
                                                                  .questionOrg(list.get(i + 2).getQuestionAnswerId().getQuestionId().getQuestion())
                                                                  .answerOrg(list.get(i + 2).getQuestionAnswerId().getAnswerId().getAnswer())
                                                                  .build();
            listReport.add(item);
        }

        return listReport;
    }

    @Override
    public List<StudentSportDto> studentSportList(Integer year) {
        List<StudentAnswer> list = studentAnswerRepository.studentSportList(year);
        List<StudentSportDto> listStudent = new ArrayList<>();
        for (StudentAnswer studentAnswer : list) {
            List<String> sports = new ArrayList<>();

            if (!listStudent.isEmpty() && listStudent.get(listStudent.size() - 1)
                                                     .getStudentSigenuId()
                                                     .equals(studentAnswer.getStudentSigenuId())) {

                listStudent.get(listStudent.size() - 1)
                           .getSports()
                           .add(studentAnswer.getQuestionAnswerId().getQuestionId().getQuestion());

            } else {
                sports.add(studentAnswer.getQuestionAnswerId().getQuestionId().getQuestion());
                StudentVO studentSigenu = getInfoStudent(studentAnswer.getStudentSigenuId());
                StudentSportDto item = StudentSportDto.builder()
                                                      .name((studentSigenu.getName() + " " +
                                                                studentSigenu.getLastName())
                                                                             .replace("  "," ")
                                                           )
                                                      .studentSigenuId(studentAnswer.getStudentSigenuId())
                                                      .sports(sports)
                                                      .build();
                listStudent.add(item);
            }
        }
        return listStudent;
    }

    @Override
    public List<StudentArtDto> studentArtList(Integer year) {
        List<StudentAnswer> list = studentAnswerRepository.studentArtList(year);
        List<StudentArtDto> listStudent = new ArrayList<>();
        for (StudentAnswer studentAnswer : list) {
            List<String> arts = new ArrayList<>();

            if (!listStudent.isEmpty() && listStudent.get(listStudent.size() - 1)
                                                     .getStudentSigenuId()
                                                     .equals(studentAnswer.getStudentSigenuId())) {

                listStudent.get(listStudent.size() - 1)
                           .getArts()
                           .add(studentAnswer.getQuestionAnswerId().getQuestionId().getQuestion());

            } else {
                arts.add(studentAnswer.getQuestionAnswerId().getQuestionId().getQuestion());
                StudentVO studentSigenu = getInfoStudent(studentAnswer.getStudentSigenuId());
                StudentArtDto item = StudentArtDto.builder()
                                                  .name((studentSigenu.getName() + " " +
                                                            studentSigenu.getLastName())
                                                                         .replace("  "," ")
                                                       )
                                                  .studentSigenuId(studentAnswer.getStudentSigenuId())
                                                  .arts(arts)
                                                  .build();
                listStudent.add(item);
            }

        }
        return listStudent;
    }

    @Override
    public DeportArtListDto deportArtListByStudent(String studentSigenuId) {
        List<StudentAnswer> list = studentAnswerRepository.deportArtListByStudent(studentSigenuId);

        List<String> sports = new ArrayList<>();
        List<String> arts = new ArrayList<>();

        for (StudentAnswer studentAnswer:list) {

            if (studentAnswer.getQuestionAnswerId().getQuestionId().getGroupQuestionId().getNameGroup().equals("Deportes")) {
                sports.add(studentAnswer.getQuestionAnswerId().getQuestionId().getQuestion());
            } else
                arts.add(studentAnswer.getQuestionAnswerId().getQuestionId().getQuestion());

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
