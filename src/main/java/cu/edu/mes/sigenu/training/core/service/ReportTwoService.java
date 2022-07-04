package cu.edu.mes.sigenu.training.core.service;



import cu.edu.mes.sigenu.training.core.dto.question.StudentNotComputerDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentsAnswerByAnswerByQuestionDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentsWithNotesDto;

import java.util.List;
import java.util.Map;

public interface ReportTwoService {

    List<StudentNotComputerDto> studentNotComputerList(Integer year);

    List<StudentsAnswerByAnswerByQuestionDto> percentsStudyFomrsByAnswer(Integer year);

    Map<String, Float> percentsStudyHoursByAnswer(Integer year);

    List<String> studentsByPlaceEgress(Integer year, String placeEgress);

    List<StudentsWithNotesDto> studentsWithNotes(Integer year);

    List<StudentsWithNotesDto> entryDataByCourse(Integer year);

    List<StudentsWithNotesDto> entryDataByCourseByPlaceEgress(Integer year, String placeEgress);

    List<StudentsWithNotesDto> studentsByConfigurableNotes(Integer year, float academicIndex
                                                            , float noteSpanish, float noteMat, float noteHistory);


    }
