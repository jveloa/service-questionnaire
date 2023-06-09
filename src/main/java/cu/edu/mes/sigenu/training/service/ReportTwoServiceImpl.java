package cu.edu.mes.sigenu.training.service;


import cu.edu.mes.sigenu.training.core.dto.report.*;
import cu.edu.mes.sigenu.training.core.model.*;
import cu.edu.mes.sigenu.training.core.repository.*;
import cu.edu.mes.sigenu.training.core.service.ReportTwoService;
import cu.edu.mes.sigenu.training.core.utils.Client;
import cu.edu.mes.subsystem.student.vo.BasicStudentVO;
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
    public List<StudentNotComputerDto> studentNotComputerList(Integer year, Integer questionnarieId) {
        List<StudentAnswer> list = studentAnswerRepository.studentNotComputer(year,questionnarieId);
        List<StudentNotComputerDto> listReport = new ArrayList<>();

        for (int i = 0; i < list.size(); i ++) {
            BasicStudentVO studentSigenu = getInfoBasicStudent(list.get(i).getStudentSigenuId());
            StudentNotComputerDto item = StudentNotComputerDto.builder()
                    .name((studentSigenu.getName() +" "
                            +studentSigenu.getMiddleName()+" "
                            + studentSigenu.getLastName())
                            .replace("  "," "))
                    .studentSigenuId(studentSigenu.getIdentification())
                    .build();
            listReport.add(item);
        }

        return listReport;
    }

    @Override
    public List<StudentsAnswerByAnswerByQuestionDto> percentsStudyFomrsByAnswer(Integer year, Integer questionnarieId) {

        float total = 0;
        float part = 0;
        float percentMuch = 0;
        float percentLittle = 0;
        float percentNot = 0;
        float percentNever = 0;
        List<StudentsAnswerByAnswerByQuestionDto> listReport = new ArrayList<>();
        List<Question> questions = questionRepository.findQuestionsByGroupQuestion(year,questionnarieId);


        for (int i = 0; i < questions.size(); i++ ){
            total = questionRepository.totalQuestionByStudyForms(year,questions.get(i).getId(),questionnarieId);
            List<QuestionAnswer> list = questionAnswerRepository.findQuestionAnswerByQuestion(questions.get(i).getId(),questionnarieId);


            for (int j = 0; j < list.size(); j++){
                part = questionAnswerRepository.totalAnswerByQuestion(year,questions.get(i).getId()
                        ,list.get(j).getAnswerId().getId(),questionnarieId);
                float percents = (part / total) * 100;
                percents = (float) (Math.round(percents * 100d) / 100d);
                Answer answer = answerRepository.findAnswer(list.get(j).getAnswerId().getId(),questionnarieId);

                if (answer.getNameAnswer().equals("Mucho")) {
                    percentMuch = percents;

                } else if (answer.getNameAnswer().equals("Nunca")) {
                    percentNever = percents;

                } else if (answer.getNameAnswer().equals("No sé")){
                    percentNot = percents;

                }else if (answer.getNameAnswer().equals("Un poco")){
                    percentLittle = percents;

                }


            }
            StudentsAnswerByAnswerByQuestionDto item = StudentsAnswerByAnswerByQuestionDto.builder()
                    .nameQuestion(questions.get(i).getNameQuestion().toString())
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
    public List<PercentsStudyHoursByAnswerDto> percentsStudyHoursByAnswer(Integer year, Integer questionnarieId) {
        float total = questionRepository.totalQuestionByStudyHours(year, questionnarieId) ;
        List<PercentsStudyHoursByAnswerDto> listReport = new ArrayList<>();
        if(year == 0 || questionnarieId == 0 || total == 0){
            return listReport;
        }
        else {
            List<QuestionAnswer> list = questionAnswerRepository.findQuestionAnswerByQuestionByStudyHours();

            for (int i = 0; i < list.size(); i++) {

                float part = questionAnswerRepository.totalAnswerByQuestionByStudyHours(year, list.get(i).getAnswerId().getId(), questionnarieId);
                float percents = part / total * 100;
                percents = (float) (Math.round(percents * 100d) / 100d);
                PercentsStudyHoursByAnswerDto item = PercentsStudyHoursByAnswerDto.builder()
                        .question(list.get(i).getAnswerId().getNameAnswer().toString())
                        .value(percents)
                        .build();
                listReport.add(item);
            }
        }

        return listReport;
    }

    @Override
    public List<String> studentsByPlaceEgress(Integer year,String idPlaceEgress,Integer questionnarieId) {

        List<String> listReport = new ArrayList<>();

        if(year == 0 || questionnarieId == 0)
            return listReport;

        List<QuestionnaireStudent> list = questionnaireStudentRepository.findAllByDate(year,questionnarieId);

        for (int i = 0; i < list.size(); i++){

            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            if (studentSigenu.getScholasticOrigin().getIdScholasticOrigin().equals(idPlaceEgress))
                listReport.add((studentSigenu.getName() +" "
                        +studentSigenu.getMiddleName()+" "
                        + studentSigenu.getLastName())
                        .replace("  "," "));

        }
        return listReport;
    }

    @Override
    public List<StudentsNotesDto> studentsWithNotes(Integer year, Integer questionnarieId) {

        float noteAve = 0;
        float noteMat = 0;
        float noteHistory = 0;
        float noteSpanish = 0;

        List<StudentsNotesDto> listReport = new ArrayList<>();
        List<QuestionnaireStudent> list = questionnaireStudentRepository.findAllByDate(year,questionnarieId);

        for (int i = 0; i < list.size(); i++){


            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            noteAve = studentSigenu.getAcademicIndex();
            List <EntryEvaluationVO> entryEvaluationVOList = (List<EntryEvaluationVO>) studentSigenu.getEntryEvaluations();

            for (int j = 0; j < entryEvaluationVOList.size(); j++){

                if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Español")) {
                    noteSpanish = entryEvaluationVOList.get(j).getMark();

                } else if (entryEvaluationVOList.get(j).getEntrySubjectName().equals("Matemática")) {
                    noteMat = entryEvaluationVOList.get(j).getMark();

                } else
                    noteHistory = entryEvaluationVOList.get(j).getMark();



            }

            StudentsNotesDto item  = StudentsNotesDto.builder()
                    .name((studentSigenu.getName() +" "
                            +studentSigenu.getMiddleName()+" "
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
    public List<StudentsWithNotesDto> entryDataByCourse(Integer year, Integer questionnarieId) {

        float aveAcademic = 0;
        float aveSpanish = 0;
        float aveHistory = 0;
        float aveMat = 0;
        int countAcademic = 0;
        int countSpanish = 0;
        int countHistory = 0;
        int countMat = 0;

        List<StudentsWithNotesDto> listReport = new ArrayList<>();
        List<QuestionnaireStudent> list = questionnaireStudentRepository.findAllByDate(year, questionnarieId);
        if(year == 0 || questionnarieId == 0 || list.isEmpty())
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
    public List<StudentsWithNotesDto> entryDataByCourseByPlaceEgress(Integer year, String idPlaceEgress,Integer questionnarieId) {

        float aveAcademic = 0;
        float aveSpanish = 0;
        float aveHistory = 0;
        float aveMat = 0;
        int countAcademic = 0;
        int countSpanish = 0;
        int countHistory = 0;
        int countMat = 0;

        StudentVO studentSigenu = new StudentVO();

        List<StudentsWithNotesDto> listReport = new ArrayList<>();
        List<QuestionnaireStudent> list = questionnaireStudentRepository.findAllByDate(year,questionnarieId);
        List <Float> noteAcademic = new ArrayList<>();
        List <Float> noteSpanish = new ArrayList<>();
        List <Float> noteMat = new ArrayList<>();
        List <Float> noteHistory = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
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

        /*if (noteAcademic.size() > 0 ) {
            StudentsWithNotesDto spanish = addNotes(noteSpanish, aveSpanish, "Español", countSpanish);
            listReport.add(spanish);

            StudentsWithNotesDto mat = addNotes(noteMat, aveMat, "Matemática", countMat);
            listReport.add(mat);

            StudentsWithNotesDto history = addNotes(noteHistory, aveHistory, "Historia", countHistory);
            listReport.add(history);

            StudentsWithNotesDto index = addNotes(noteAcademic, aveAcademic, "Índice Académico", countAcademic);
            listReport.add(index);
        }*/
        if (!(noteAcademic.isEmpty()) || !(noteHistory.isEmpty()) || !(noteMat.isEmpty()) || !(noteSpanish.isEmpty()) ) {
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
            , float noteSpanish, float noteMat, float noteHistory,Integer questionnarieId) {
        boolean count = true;
        //int aux = 0;

        float noteAve = 0;
        float mat = 0;
        float history = 0;
        float spanish = 0;

        List<StudentsNotesDto> listReport = new ArrayList<>();
        List<QuestionnaireStudent> list = questionnaireStudentRepository.findAllByDate(year,questionnarieId);

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
                                    +studentSigenu.getMiddleName()+" "
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
    public List<String> studentsByEntrySource(Integer year, String idEntrySource,Integer questionnarieId) {

        List<String> listReport = new ArrayList<>();

        if(year == 0 || questionnarieId == 0)
            return listReport;

        List<QuestionnaireStudent> list = questionnaireStudentRepository.findAllByDate(year,questionnarieId);

        for (int i = 0; i < list.size(); i++){

            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            if (studentSigenu.getEntrySource().getIdEntrySource().equals(idEntrySource))
                listReport.add((studentSigenu.getName() +" "
                        +studentSigenu.getMiddleName()+" "
                        + studentSigenu.getLastName())
                        .replace("  "," "));

        }
        return listReport;
    }

    public StudentsWithNotesDto addNotes(List <Float> note, float average, String name, int size){

        float min = 0;
        float max = 0;
        float ave = 0;

        if (note.size() > 0){
            min = Collections.min(note);
            max = Collections.max(note);
            ave = average / size;
            ave = (float) (Math.round(ave * 100d) / 100d);
        }



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

    public BasicStudentVO getInfoBasicStudent(String studentId) {
        try {
            return Client.getStudentSubsystem().getSingleStudent(studentId);
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

    @Override
    public List<String> getAllYears(Integer questionnarieId) {

        return questionRepository.getAllYear(questionnarieId);
    }

    @Override
    public CareerOptionsDto studentCareerOptions(Integer year, Integer questionnarieId) {
        Integer quantityOptionOne = 0;
        Integer quantityOptionTwo = 0;
        Integer quantityOptionThree = 0;
        Integer quantityOptionPlusThree = 0;


        List<QuestionnaireStudent> list = questionnaireStudentRepository.findAllByDate(year,questionnarieId);

        for (int i = 0; i < list.size(); i++){


            StudentVO studentSigenu = getInfoStudent(list.get(i).getStudentSigenuId());
            if (studentSigenu.getOption() == 1)
                quantityOptionOne++;
            else if (studentSigenu.getOption() == 2)
                quantityOptionTwo++;
            else if (studentSigenu.getOption() == 3)
                quantityOptionThree++;
            else if (studentSigenu.getOption() > 3)
                quantityOptionPlusThree++;
        }

        CareerOptionsDto item  = CareerOptionsDto.builder()
                .quantityOptionOne(quantityOptionOne)
                .quantityOptionTwo(quantityOptionTwo)
                .quantityOptionThree(quantityOptionThree)
                .quantityOptionPlusThree(quantityOptionPlusThree)
                .build();



        return item;
    }

    @Override
    public Double percentsStudentsUjcByYear(Integer year, Integer questionnarieId) {

        float total = questionRepository.totalQuestionByStudentsUjc(year,questionnarieId);
        float part = questionAnswerRepository.totalAnswerByQuestionByStudentsUjc(year,questionnarieId);
        double percents = part * 100 / total;
        percents = Math.round(percents * 100d) / 100d;

        return percents;
    }
}

