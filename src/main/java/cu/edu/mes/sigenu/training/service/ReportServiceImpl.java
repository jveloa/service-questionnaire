package cu.edu.mes.sigenu.training.service;

import cu.edu.mes.sigenu.training.core.dto.report.DeportArtListDto;
import cu.edu.mes.sigenu.training.core.dto.report.ResponsabilityReportDto;
import cu.edu.mes.sigenu.training.core.dto.report.StudentArtDto;
import cu.edu.mes.sigenu.training.core.dto.report.StudentSportDto;
import cu.edu.mes.sigenu.training.core.model.StudentAnswer;
import cu.edu.mes.sigenu.training.core.repository.StudentAnswerRepository;
import cu.edu.mes.sigenu.training.core.service.ReportService;
import cu.edu.mes.sigenu.training.core.utils.Client;
import cu.edu.mes.subsystem.student.vo.BasicStudentVO;
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
    public List<ResponsabilityReportDto> responsabilityReport(Integer year, Integer questionnarieId) {
        List<StudentAnswer> list = studentAnswerRepository.responsibilityReport(year,questionnarieId);
        List<ResponsabilityReportDto> listReport = new ArrayList<>();

         String answerInterest = "";
         String answerExp = "";
         String answerOrg = "";
         int count = 0;

        for (int i = 0; i < list.size(); i += 3) {
            count = i+3;
            BasicStudentVO studentSigenu = getInfoBasicStudent(list.get(i).getStudentSigenuId());
            for (int j = i; j < count; j++){
                if (list.get(j).getQuestionAnswerId().getQuestionId().getNameQuestion().contains("Tienes interés")){
                    answerInterest = list.get(j).getQuestionAnswerId().getAnswerId().getNameAnswer();
                }
                else if (list.get(j).getQuestionAnswerId().getQuestionId().getNameQuestion().contains("Experiencias")){
                     answerExp = list.get(j).getQuestionAnswerId().getAnswerId().getNameAnswer();
                }
                else if (list.get(j).getQuestionAnswerId().getQuestionId().getNameQuestion().contains("Organización")){
                    answerOrg = list.get(j).getQuestionAnswerId().getAnswerId().getNameAnswer();
                }


            }
            ResponsabilityReportDto item = ResponsabilityReportDto.builder()
                    .name((studentSigenu.getName() +" "
                            +studentSigenu.getMiddleName()+" "
                            + studentSigenu.getLastName())
                            .replace("  "," "))
                    .studentSigenuId(studentSigenu.getIdentification())
                    .answerInterest(answerInterest)
                    .answerExp(answerExp)
                    .answerOrg(answerOrg)
                    .build();
            listReport.add(item);


        }

        return listReport;
    }

    /*@Override
    public List<ResponsabilityReportDto> responsabilityReport(Integer year, Integer questionnarieId) {
        List<StudentAnswer> list = studentAnswerRepository.responsibilityReport(year,questionnarieId);
        List<ResponsabilityReportDto> listReport = new ArrayList<>();

        for (int i = 0; i < list.size(); i += 3) {
            BasicStudentVO studentSigenu = getInfoBasicStudent(list.get(i).getStudentSigenuId());
            ResponsabilityReportDto item = ResponsabilityReportDto.builder()
                                                                  .name((studentSigenu.getName() +" "
                                                                            + studentSigenu.getLastName())
                                                                                  .replace("  "," "))
                                                                  .studentSigenuId(studentSigenu.getIdentification())
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
    }*/

    @Override
    public List<StudentSportDto> studentSportList(Integer year,Integer questionnarieId) {

        List<StudentAnswer> list = studentAnswerRepository.studentSportList(year,questionnarieId);
        BasicStudentVO studentSigenu = null;
        List<StudentSportDto> listStudent = new ArrayList<>();
        for (StudentAnswer studentAnswer : list) {
            List<String> sports = new ArrayList<>();

            if (!listStudent.isEmpty() && listStudent.get(listStudent.size() - 1)
                                                     .getStudentSigenuId()
                                                     .equals(studentSigenu.getIdentification())
                                                    && studentSigenu.getIdStudent()
                                                    .equals(studentAnswer.getStudentSigenuId())) {

                listStudent.get(listStudent.size() - 1)
                           .getSports()
                           .add(studentAnswer.getQuestionAnswerId().getQuestionId().getNameQuestion());

            } else {
                sports.add(studentAnswer.getQuestionAnswerId().getQuestionId().getNameQuestion());
                     studentSigenu = getInfoBasicStudent(studentAnswer.getStudentSigenuId());
                StudentSportDto item = StudentSportDto.builder()
                                                      .name((studentSigenu.getName() + " "
                                                              +studentSigenu.getMiddleName()+" "
                                                               + studentSigenu.getLastName())
                                                                             .replace("  "," ")
                                                           )
                                                      .studentSigenuId(studentSigenu.getIdentification())
                                                      .sports(sports)
                                                      .build();
                listStudent.add(item);
            }
        }
        return listStudent;
    }



    @Override
    public List<StudentArtDto> studentArtList(Integer year, Integer questionnarieId) {
        List<StudentAnswer> list = studentAnswerRepository.studentArtList(year,questionnarieId);
        BasicStudentVO studentSigenu = null;
        List<StudentArtDto> listStudent = new ArrayList<>();
        for (StudentAnswer studentAnswer : list) {
            List<String> arts = new ArrayList<>();

            if (!listStudent.isEmpty() && listStudent.get(listStudent.size() - 1)
                                                     .getStudentSigenuId()
                                                        .equals(studentSigenu.getIdentification())
                                                                && studentSigenu.getIdStudent()
                                                                .equals(studentAnswer.getStudentSigenuId())) {

                listStudent.get(listStudent.size() - 1)
                           .getArts()
                           .add(studentAnswer.getQuestionAnswerId().getQuestionId().getNameQuestion());

            } else {
                arts.add(studentAnswer.getQuestionAnswerId().getQuestionId().getNameQuestion());
                     studentSigenu = getInfoBasicStudent(studentAnswer.getStudentSigenuId());
                StudentArtDto item = StudentArtDto.builder()
                                                  .name((studentSigenu.getName() + " "
                                                          +studentSigenu.getMiddleName()+" "+
                                                            studentSigenu.getLastName())
                                                                         .replace("  "," ")
                                                       )
                                                  .studentSigenuId(studentSigenu.getIdentification())
                                                  .arts(arts)
                                                  .build();
                listStudent.add(item);
            }

        }
        return listStudent;
    }

    @Override
    public List<DeportArtListDto> deportArtListByStudent(Integer year, Integer questionnarieId) {
        List<StudentAnswer> list = studentAnswerRepository.deportArtListByStudent(year,questionnarieId);
        List<DeportArtListDto> listStudent = new ArrayList<>();
        List<String> sports = new ArrayList<>();
        List<String> arts = new ArrayList<>();
        BasicStudentVO studentSigenu = null;
        int aux = 0;



        for (StudentAnswer studentAnswer:list) {

             studentSigenu = getInfoBasicStudent(studentAnswer.getStudentSigenuId());

            if (studentAnswer.getQuestionAnswerId().getQuestionId().getGroupQuestionId().getNameGroup().equals("Deportes")) {
                sports.add(studentAnswer.getQuestionAnswerId().getQuestionId().getNameQuestion());
            } else
                arts.add(studentAnswer.getQuestionAnswerId().getQuestionId().getNameQuestion());

            if(aux == list.size()- 1){
                listStudent.add(addDeportArtList(studentSigenu,sports,arts));
            }

            else if (!(studentSigenu.getIdStudent().equals(list.get(aux+1).getStudentSigenuId())))
            {

                listStudent.add(addDeportArtList(studentSigenu,sports,arts));
                sports = new ArrayList<>();
                arts = new ArrayList<>();
            }
            aux ++;
        }
        return listStudent;
    }




    public StudentVO getInfoStudent(String studentId) {
        try {
            return Client.getStudentSubsystem().getStudent(studentId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BasicStudentVO getInfoBasicStudent(String studentId) {
        try {
            return Client.getStudentSubsystem().getSingleStudent(studentId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DeportArtListDto addDeportArtList(BasicStudentVO studentSigenu,List<String> sports,List<String> arts) {
        DeportArtListDto item = DeportArtListDto.builder()
                .name((studentSigenu.getName() + " "
                        +studentSigenu.getMiddleName()+" "+
                        studentSigenu.getLastName())
                        .replace("  ", " "))
                .studentSigenuId(studentSigenu.getIdentification())
                .arts(arts)
                .sports(sports)
                .build();
        return item;
    }

}
