package cu.edu.mes.sigenu.training.service;


import cu.edu.mes.sigenu.training.core.dto.question.StudentNotComputerDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentsAnswerByAnswerByQuestionDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentsWithNotesDto;
import cu.edu.mes.sigenu.training.core.model.*;
import cu.edu.mes.sigenu.training.core.repository.*;
import cu.edu.mes.sigenu.training.core.service.ReportTwoService;
import cu.edu.mes.sigenu.training.core.utils.Client;
import cu.edu.mes.subsystem.student.vo.StudentVO;

import cu.edu.mes.vo.EntryEvaluationDataVO;
import cu.edu.mes.vo.EntryEvaluationVO;
import cu.edu.mes.vo.EntrySourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.*;

@Service
public class ReportTwoServiceImpl implements ReportTwoService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Autowired
    private QuestionnaireStudentRepository questionnaireStudentRepository;


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

    @Override
    public Map<String, Float> percentsStudyHoursByAnswer(Integer year) {

        Map<String,Float> listReport = new HashMap<String, Float>();
        float total = questionRepository.totalQuestionByStudyHours(year);
        List<QuestionAnswer> list = questionAnswerRepository.findQuestionAnswerByQuestionByStudyHours();

        for (int i = 0; i < list.size(); i++){

            float part = questionAnswerRepository.totalAnswerByQuestionByStudyHours(year,list.get(i).getAnswerId().getId());
            float percents = part / total * 100;
            listReport.put(list.get(i).getAnswerId().getAnswer().toString(),percents);
        }

        return listReport;
    }

    @Override
    public List<String> studentsByPlaceEgress(Integer year,String placeEgress) {

        List<String> listReport = new ArrayList<>();
        List<QuestionnarieStudent> list = questionnaireStudentRepository.findAllByDoneDate(year);

        for (int i = 0; i < list.size(); i++){

            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            if (studentSigenu.getScholasticOrigin().getName().equals(placeEgress))
                listReport.add((studentSigenu.getName() +" "
                        + studentSigenu.getLastName())
                        .replace("  "," "));

        }
        return listReport;
    }

   @Override
    public List<StudentsWithNotesDto> studentsWithNotes(Integer year) {

        List<StudentsWithNotesDto> listReport = new ArrayList<>();
        List<QuestionnarieStudent> list = questionnaireStudentRepository.findAllByDoneDate(year);

        for (int i = 0; i < list.size(); i++){

            Map<String,Float> listAux = new HashMap<String, Float>();
            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            listAux.put("Índice acádemico",studentSigenu.getAcademicIndex());
            List <EntryEvaluationVO> entryEvaluationVOList = (List<EntryEvaluationVO>) studentSigenu.getEntryEvaluations();

            for (int j = 0; j < entryEvaluationVOList.size(); j++)
            listAux.put(entryEvaluationVOList.get(j).getEntrySubjectName().toString()
                        ,entryEvaluationVOList.get(j).getMark());

            StudentsWithNotesDto item  = StudentsWithNotesDto.builder()
                                                                .name((studentSigenu.getName() +" "
                                                                        + studentSigenu.getLastName())
                                                                        .replace("  "," "))
                                                                .notesList(listAux)
                                                                .build();

            listReport.add(item);

        }

        return listReport;
    }


    @Override
    public List<StudentsWithNotesDto> entryDataByCourse(Integer year) {

        float aveAcademic = 0;
        float aveSpanish = 0;
        float aveHistory = 0;
        float aveMat = 0;
        int countAcademic = 0;
        int countSpanish = 0;
        int countHistory = 0;
        int countMat = 0;

        List<StudentsWithNotesDto> listReport = new ArrayList<>();
        List<QuestionnarieStudent> list = questionnaireStudentRepository.findAllByDoneDate(year);
        List <Float> noteAcademic = new ArrayList<>();
        List <Float> noteSpanish = new ArrayList<>();
        List <Float> noteMat = new ArrayList<>();
        List <Float> noteHistory = new ArrayList<>();

        for (int i = 0; i < list.size(); i++){

            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            if (studentSigenu.getAcademicIndex() > 0){

                aveAcademic += studentSigenu.getAcademicIndex();
                noteAcademic.add(studentSigenu.getAcademicIndex());
                countAcademic++;
            }

            List <EntryEvaluationVO> entryEvaluationVOList = (List<EntryEvaluationVO>) studentSigenu.getEntryEvaluations();

            for (int j = 0; j < entryEvaluationVOList.size(); j++) {

                if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Español") && entryEvaluationVOList.get(j).getMark() > 0) {
                    aveSpanish += entryEvaluationVOList.get(j).getMark();
                    noteSpanish.add(entryEvaluationVOList.get(j).getMark());
                    countSpanish++;
                } else if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Matemática") && entryEvaluationVOList.get(j).getMark() > 0) {
                    aveMat += entryEvaluationVOList.get(j).getMark();
                    noteMat.add(entryEvaluationVOList.get(j).getMark());
                    countMat++;
                } else if (entryEvaluationVOList.get(j).getMark() > 0){
                    aveHistory += entryEvaluationVOList.get(j).getMark();
                    noteHistory.add(entryEvaluationVOList.get(j).getMark());
                    countHistory++;
                }

            }

        }


        StudentsWithNotesDto spanish = addNotes(noteSpanish,aveSpanish,"Español",countSpanish);
        listReport.add(spanish);

        StudentsWithNotesDto mat = addNotes(noteMat,aveMat,"Matemática",countMat);
        listReport.add(mat);

        StudentsWithNotesDto history = addNotes(noteHistory,aveHistory,"Historia",countHistory);
        listReport.add(history);

        StudentsWithNotesDto index = addNotes(noteAcademic,aveAcademic,"Índice Academico",countAcademic);
        listReport.add(index);

        return listReport;
    }

    public StudentsWithNotesDto addNotes(List <Float> note, float average, String name, int size){

        float min = Collections.min(note);
        float max = Collections.max(note);
        float ave = average / size;
        Map<String,Float> listAux = new HashMap<String, Float>();

        listAux.put("Promedio",ave);
        listAux.put("Máximo",max);
        listAux.put("Mínimo",min);

        StudentsWithNotesDto item  = StudentsWithNotesDto.builder()
                                                            .name(name)
                                                            .notesList(listAux)
                                                            .build();

        return item;
    }

    public float noteMax(float currentNote, float noteStudent){
        float noteMax = 0;
        if(noteStudent >= currentNote)
            noteMax = currentNote;
        else
            noteMax = currentNote;

        return noteMax;
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
