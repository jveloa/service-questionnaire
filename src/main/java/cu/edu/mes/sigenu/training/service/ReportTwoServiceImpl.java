package cu.edu.mes.sigenu.training.service;


import cu.edu.mes.sigenu.training.core.dto.report.*;
import cu.edu.mes.sigenu.training.core.model.*;
import cu.edu.mes.sigenu.training.core.repository.*;
import cu.edu.mes.sigenu.training.core.service.ReportTwoService;
import cu.edu.mes.sigenu.training.core.utils.Client;
import cu.edu.mes.subsystem.student.vo.StudentVO;

import cu.edu.mes.vo.EntryEvaluationVO;
import cu.edu.mes.vo.EntrySourceVO;
import cu.edu.mes.vo.ScholasticOriginVO;
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
    public List<StudentNotComputerDto> studentNotComputerList(Integer year, Integer id) {
        List<StudentAnswer> list = studentAnswerRepository.studentNotComputer(year,id);
        List<StudentNotComputerDto> listReport = new ArrayList<>();

        for (int i = 0; i < list.size(); i ++) {
            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            StudentNotComputerDto item = StudentNotComputerDto.builder()
                    .name((studentSigenu.getName() +" "
                            + studentSigenu.getLastName())
                            .replace("  "," "))
                    .studentSigenuId(studentSigenu.getIdentification())
                    .build();
            listReport.add(item);
        }

        return listReport;
    }

    @Override
    public List<StudentsAnswerByAnswerByQuestionDto> percentsStudyFomrsByAnswer(Integer year, Integer id) {

        float total = 0;
        float part = 0;
        float percentMuch = 0;
        float percentLittle = 0;
        float percentNot = 0;
        float percentNever = 0;
        List<StudentsAnswerByAnswerByQuestionDto> listReport = new ArrayList<>();
        List<Question> questions = questionRepository.findIQuestionsByGroupQuestion(year,id);


        for (int i = 0; i < questions.size(); i++ ){
            total = questionRepository.totalQuestionByStudyForms(year,questions.get(i).getId(),id);
            List<QuestionAnswer> list = questionAnswerRepository.findQuestionAnswerByQuestion(questions.get(i).getId(),id);


            for (int j = 0; j < list.size(); j++){
                part = questionAnswerRepository.totalAnswerByQuestion(year,questions.get(i).getId()
                        ,list.get(j).getAnswerId().getId(),id);
                float percents = (part / total) * 100;
                Answer answer = answerRepository.findIAnswerName(list.get(j).getAnswerId().getId(),id);

                if (answer.getAnswer().equals("Mucho")) {
                    percentMuch = percents;

                } else if (answer.getAnswer().equals("Nunca")) {
                    percentNever = percents;

                } else if (answer.getAnswer().equals("No sé")){
                    percentNot = percents;

                }else if (answer.getAnswer().equals("Un poco")){
                    percentLittle = percents;

                }


            }
            StudentsAnswerByAnswerByQuestionDto item = StudentsAnswerByAnswerByQuestionDto.builder()
                    .nameQuestion(questions.get(i).getQuestion().toString())
                    .percentMuch(percentMuch)
                    .percentLittle(percentLittle)
                    .percentNot(percentNot)
                    .percentNever(percentNever)
                    .build();
            listReport.add(item);



        }


        return listReport;
    }

    @Override
    public List<PercentsStudyHoursByAnswerDto> percentsStudyHoursByAnswer(Integer year, Integer id) {
        float total = questionRepository.totalQuestionByStudyHours(year, id) ;
        List<PercentsStudyHoursByAnswerDto> listReport = new ArrayList<>();
        if(year == 0 || id == 0 || total == 0){
            return listReport;
        }
        else {
            List<QuestionAnswer> list = questionAnswerRepository.findQuestionAnswerByQuestionByStudyHours();

            for (int i = 0; i < list.size(); i++) {

                float part = questionAnswerRepository.totalAnswerByQuestionByStudyHours(year, list.get(i).getAnswerId().getId(), id);
                float percents = part / total * 100;
                PercentsStudyHoursByAnswerDto item = PercentsStudyHoursByAnswerDto.builder()
                        .question(list.get(i).getAnswerId().getAnswer().toString())
                        .value(percents)
                        .build();
                listReport.add(item);
            }
        }

        return listReport;
    }

    @Override
    public List<String> studentsByPlaceEgress(Integer year,String idPlaceEgress,Integer id) {

        List<String> listReport = new ArrayList<>();

        if(year == 0 || id == 0)
            return listReport;

        List<QuestionnarieStudent> list = questionnaireStudentRepository.findAllByDate(year,id);

        for (int i = 0; i < list.size(); i++){

            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            if (studentSigenu.getScholasticOrigin().getIdScholasticOrigin().equals(idPlaceEgress))
                listReport.add((studentSigenu.getName() +" "
                        + studentSigenu.getLastName())
                        .replace("  "," "));

        }
        return listReport;
    }

    @Override
    public List<StudentsNotesDto> studentsWithNotes(Integer year, Integer id) {

        float noteAve = 0;
        float noteMat = 0;
        float noteHistory = 0;
        float noteSpanish = 0;

        List<StudentsNotesDto> listReport = new ArrayList<>();
        List<QuestionnarieStudent> list = questionnaireStudentRepository.findAllByDate(year,id);

        for (int i = 0; i < list.size(); i++){


            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            noteAve = studentSigenu.getAcademicIndex();
            List <EntryEvaluationVO> entryEvaluationVOList = (List<EntryEvaluationVO>) studentSigenu.getEntryEvaluations();

            for (int j = 0; j < entryEvaluationVOList.size(); j++){

                if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Español") && entryEvaluationVOList.get(j).getMark() > 0) {
                    noteSpanish = entryEvaluationVOList.get(j).getMark();

                } else if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Matemática") && entryEvaluationVOList.get(j).getMark() > 0) {
                    noteMat = entryEvaluationVOList.get(j).getMark();

                } else if (entryEvaluationVOList.get(j).getMark() > 0){
                    noteHistory = entryEvaluationVOList.get(j).getMark();

                }

            }

            StudentsNotesDto item  = StudentsNotesDto.builder()
                    .name((studentSigenu.getName() +" "
                            + studentSigenu.getLastName())
                            .replace("  "," "))
                    .noteAve(noteAve)
                    .noteMat(noteMat)
                    .noteHistory(noteHistory)
                    .noteSpanish(noteSpanish)
                    .build();

            listReport.add(item);

        }

        return listReport;
    }


    @Override
    public List<StudentsWithNotesDto> entryDataByCourse(Integer year, Integer id) {

        float aveAcademic = 0;
        float aveSpanish = 0;
        float aveHistory = 0;
        float aveMat = 0;
        int countAcademic = 0;
        int countSpanish = 0;
        int countHistory = 0;
        int countMat = 0;

        List<StudentsWithNotesDto> listReport = new ArrayList<>();
        List<QuestionnarieStudent> list = questionnaireStudentRepository.findAllByDate(year, id);
        if(year == 0 || id == 0 || list.isEmpty())
            return listReport;


        List<Float> noteAcademic = new ArrayList<>();
        List<Float> noteSpanish = new ArrayList<>();
        List<Float> noteMat = new ArrayList<>();
        List<Float> noteHistory = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            if (studentSigenu.getAcademicIndex() > 0) {

                aveAcademic += studentSigenu.getAcademicIndex();
                noteAcademic.add(studentSigenu.getAcademicIndex());
                countAcademic++;
            }

            List<EntryEvaluationVO> entryEvaluationVOList = (List<EntryEvaluationVO>) studentSigenu.getEntryEvaluations();

            for (int j = 0; j < entryEvaluationVOList.size(); j++) {

                if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Español") && entryEvaluationVOList.get(j).getMark() > 0) {
                    aveSpanish += entryEvaluationVOList.get(j).getMark();
                    noteSpanish.add(entryEvaluationVOList.get(j).getMark());
                    countSpanish++;
                } else if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Matemática") && entryEvaluationVOList.get(j).getMark() > 0) {
                    aveMat += entryEvaluationVOList.get(j).getMark();
                    noteMat.add(entryEvaluationVOList.get(j).getMark());
                    countMat++;
                } else if (entryEvaluationVOList.get(j).getMark() > 0) {
                    aveHistory += entryEvaluationVOList.get(j).getMark();
                    noteHistory.add(entryEvaluationVOList.get(j).getMark());
                    countHistory++;
                }

            }

        }

        StudentsWithNotesDto index = addNotes(noteAcademic, aveAcademic, "Índice Académico", countAcademic);
        listReport.add(index);

        StudentsWithNotesDto spanish = addNotes(noteSpanish, aveSpanish, "Español", countSpanish);
        listReport.add(spanish);

        StudentsWithNotesDto mat = addNotes(noteMat, aveMat, "Matemática", countMat);
        listReport.add(mat);

        StudentsWithNotesDto history = addNotes(noteHistory, aveHistory, "Historia", countHistory);
        listReport.add(history);



        return listReport;
    }

    @Override
    public List<StudentsWithNotesDto> entryDataByCourseByPlaceEgress(Integer year, String idPlaceEgress,Integer id) {

        float aveAcademic = 0;
        float aveSpanish = 0;
        float aveHistory = 0;
        float aveMat = 0;
        int countAcademic = 0;
        int countSpanish = 0;
        int countHistory = 0;
        int countMat = 0;

        List<StudentsWithNotesDto> listReport = new ArrayList<>();
        List<QuestionnarieStudent> list = questionnaireStudentRepository.findAllByDate(year,id);
        List <Float> noteAcademic = new ArrayList<>();
        List <Float> noteSpanish = new ArrayList<>();
        List <Float> noteMat = new ArrayList<>();
        List <Float> noteHistory = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            if (studentSigenu.getScholasticOrigin().getIdScholasticOrigin().equals(idPlaceEgress)) {
                if (studentSigenu.getAcademicIndex() > 0) {

                    aveAcademic += studentSigenu.getAcademicIndex();
                    noteAcademic.add(studentSigenu.getAcademicIndex());
                    countAcademic++;
                }

                List<EntryEvaluationVO> entryEvaluationVOList = (List<EntryEvaluationVO>) studentSigenu.getEntryEvaluations();

                for (int j = 0; j < entryEvaluationVOList.size(); j++) {

                    if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Español")
                            && entryEvaluationVOList.get(j).getMark() > 0) {

                        aveSpanish += entryEvaluationVOList.get(j).getMark();
                        noteSpanish.add(entryEvaluationVOList.get(j).getMark());
                        countSpanish++;

                    } else if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Matemática")
                            && entryEvaluationVOList.get(j).getMark() > 0) {

                        aveMat += entryEvaluationVOList.get(j).getMark();
                        noteMat.add(entryEvaluationVOList.get(j).getMark());
                        countMat++;

                    } else if (entryEvaluationVOList.get(j).getMark() > 0) {

                        aveHistory += entryEvaluationVOList.get(j).getMark();
                        noteHistory.add(entryEvaluationVOList.get(j).getMark());
                        countHistory++;
                    }

                }

            }
        }

        if (noteAcademic.size() > 0 ) {
            StudentsWithNotesDto spanish = addNotes(noteSpanish, aveSpanish, "Español", countSpanish);
            listReport.add(spanish);

            StudentsWithNotesDto mat = addNotes(noteMat, aveMat, "Matemática", countMat);
            listReport.add(mat);

            StudentsWithNotesDto history = addNotes(noteHistory, aveHistory, "Historia", countHistory);
            listReport.add(history);

            StudentsWithNotesDto index = addNotes(noteAcademic, aveAcademic, "Índice Académico", countAcademic);
            listReport.add(index);
        }
        return listReport;
    }

    @Override
    public List<StudentsNotesDto> studentsByConfigurableNotes(Integer year, float academicIndex
            , float noteSpanish, float noteMat, float noteHistory,Integer id) {
        boolean count = true;
        //int aux = 0;

        float noteAve = 0;
        float mat = 0;
        float history = 0;
        float spanish = 0;

        List<StudentsNotesDto> listReport = new ArrayList<>();
        List<QuestionnarieStudent> list = questionnaireStudentRepository.findAllByDate(year,id);

        for (int i = 0; i < list.size(); i++) {


            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            List<EntryEvaluationVO> entryEvaluationVOList = (List<EntryEvaluationVO>) studentSigenu.getEntryEvaluations();

            if (studentSigenu.getAcademicIndex() >= academicIndex && studentSigenu.getAcademicIndex() != 0) {


                for (int j = 0; j < entryEvaluationVOList.size(); j++) {
                    count = true;

                    if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Español")
                            && entryEvaluationVOList.get(j).getMark() >= noteSpanish
                            && entryEvaluationVOList.get(j).getMark() != 0) {

                        count = false;


                    } else if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Matemática")
                            && entryEvaluationVOList.get(j).getMark() >= noteMat
                            && entryEvaluationVOList.get(j).getMark() != 0) {

                        count = false;

                    } else if (entryEvaluationVOList.get(j).getMark() >= noteHistory
                            && entryEvaluationVOList.get(j).getMark() != 0
                            && entryEvaluationVOList.get(j).getEntrySubjectName().equals("Historia")) {

                        count = false;
                    }

                    if (count)
                        j = entryEvaluationVOList.size();

                }
                if (!count){
                    noteAve = studentSigenu.getAcademicIndex();

                    for (int j = 0; j < entryEvaluationVOList.size(); j++){

                        if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Español") && entryEvaluationVOList.get(j).getMark() > 0) {
                            spanish = entryEvaluationVOList.get(j).getMark();

                        } else if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Matemática") && entryEvaluationVOList.get(j).getMark() > 0) {
                            mat = entryEvaluationVOList.get(j).getMark();

                        } else if (entryEvaluationVOList.get(j).getMark() > 0){
                            history = entryEvaluationVOList.get(j).getMark();

                        }

                    }

                    StudentsNotesDto item = StudentsNotesDto.builder()
                            .name((studentSigenu.getName() + " "
                                    + studentSigenu.getLastName())
                                    .replace("  ", " "))
                            .noteAve(noteAve)
                            .noteMat(mat)
                            .noteHistory(history)
                            .noteSpanish(spanish)
                            .build();

                    listReport.add(item);
                }

            }
        }

        return listReport;
    }

    @Override
    public List<String> studentsByEntrySource(Integer year, String idEntrySource,Integer id) {

        List<String> listReport = new ArrayList<>();

        if(year == 0 || id == 0)
            return listReport;

        List<QuestionnarieStudent> list = questionnaireStudentRepository.findAllByDate(year,id);

        for (int i = 0; i < list.size(); i++){

            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            if (studentSigenu.getEntrySource().getIdEntrySource().equals(idEntrySource))
                listReport.add((studentSigenu.getName() +" "
                        + studentSigenu.getLastName())
                        .replace("  "," "));

        }
        return listReport;
    }

    public StudentsWithNotesDto addNotes(List <Float> note, float average, String name, int size){

        float min = Collections.min(note);
        float max = Collections.max(note);
        float ave = average / size;

        StudentsWithNotesDto item  = StudentsWithNotesDto.builder()
                .name(name)
                .numberAve(ave)
                .numberMax(max)
                .numberMin(min)
                .build();

        return item;
    }

    public StudentVO getInfoStudent(String studentId) {
        try {
            return Client.getStudentSubsystem().getStudent(studentId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EntrySourceAuxDto> getAllEntrySource() {
        List<EntrySourceAuxDto> list = new ArrayList<>();
        try {
            Collection<EntrySourceVO> entrySource = (Collection<EntrySourceVO>) Client.getEntrySourceCatalog().getAllActive();
            Iterator<EntrySourceVO> iterator = entrySource.iterator();
           while (iterator.hasNext()){
               EntrySourceVO e = iterator.next();
                EntrySourceAuxDto item  = EntrySourceAuxDto.builder()
                        .name(e.getName())
                        .idEntrysource(e.getIdEntrySource())
                        .build();
                list.add(item);
            }
            return list;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EntrySourceAuxDto> getAllPlaceEgress() {
        List<EntrySourceAuxDto> list = new ArrayList<>();
        try {
            Collection<ScholasticOriginVO> scholasticOrigin = (Collection<ScholasticOriginVO>) Client.getScholasticOriginCatalog().getAllActive();
            Iterator<ScholasticOriginVO> iterator = scholasticOrigin.iterator();
            while (iterator.hasNext()){
                ScholasticOriginVO e = iterator.next();
                EntrySourceAuxDto item  = EntrySourceAuxDto.builder()
                        .name(e.getName())
                        .idEntrysource(e.getIdScholasticOrigin())
                        .build();
                list.add(item);
            }
            return list;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
