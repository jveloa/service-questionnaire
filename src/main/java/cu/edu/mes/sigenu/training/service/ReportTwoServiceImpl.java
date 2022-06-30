package cu.edu.mes.sigenu.training.service;


import cu.edu.mes.sigenu.training.core.dto.question.StudentNotComputerDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentsAnswerByAnswerByQuestionDto;
import cu.edu.mes.sigenu.training.core.model.Answer;
import cu.edu.mes.sigenu.training.core.model.Question;
import cu.edu.mes.sigenu.training.core.model.QuestionAnswer;
import cu.edu.mes.sigenu.training.core.model.StudentAnswer;
import cu.edu.mes.sigenu.training.core.repository.*;
import cu.edu.mes.sigenu.training.core.service.ReportTwoService;
import cu.edu.mes.sigenu.training.core.utils.Client;
import cu.edu.mes.subsystem.student.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportTwoServiceImpl implements ReportTwoService {

    @Autowired
    private CustomRepository customRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;


    @Override
    public List<StudentNotComputerDto> studentNotComputerList(Integer year) {
        List<StudentAnswer> list = studentAnswerRepository.studentNotComputer(year);
        List<StudentNotComputerDto> listReport = new ArrayList<>();

        for (int i = 0; i < list.size(); i ++) {
            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            StudentNotComputerDto item = StudentNotComputerDto.builder()
                                                                .name((studentSigenu.getName() +" "
                                                                        + studentSigenu.getLastName())
                                                                        .replace("  "," "))
                                                                .studentSigenuId(list.get(i).getStudentSigenuId().toString())
                                                                .build();
            listReport.add(item);
        }

        return listReport;
    }

    @Override
    public List<StudentsAnswerByAnswerByQuestionDto> percentsStudyFomrsByAnswer(Integer year) {

        float total = 0;
        float part = 0;
        List<StudentsAnswerByAnswerByQuestionDto> listReport = new ArrayList<>();
        List<Question> questions = questionRepository.findIQuestionsByGroupQuestion(year);


        for (int i = 0; i < questions.size(); i++ ){
            total = questionRepository.totalQuestionByStudyForms(year,questions.get(i).getId());
            List<QuestionAnswer> list = questionAnswerRepository.findQuestionAnswerByQuestion(questions.get(i).getId());
            Map<String,Float> listAux = new HashMap<String, Float>();

            for (int j = 0; j < list.size(); j++){
                part = questionAnswerRepository.totalAnswerByQuestion(year,questions.get(i).getId()
                                                                      ,list.get(j).getAnswerId().getId());
                float percents = (part / total) * 100;
                Answer answer = answerRepository.findIAnswerName(list.get(j).getAnswerId().getId());
                listAux.put(answer.getAnswer().toString(),percents);

            }
            StudentsAnswerByAnswerByQuestionDto item = StudentsAnswerByAnswerByQuestionDto.builder()
                                                                .nameQuestion(questions.get(i).getQuestion().toString())
                                                                .answerList(listAux)
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
