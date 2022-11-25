package cu.edu.mes.sigenu.training.core.service;



import cu.edu.mes.sigenu.training.core.dto.report.PercentsStudyHoursByAnswerDto;
import cu.edu.mes.sigenu.training.core.dto.report.StudentNotComputerDto;
import cu.edu.mes.sigenu.training.core.dto.report.StudentsAnswerByAnswerByQuestionDto;
import cu.edu.mes.sigenu.training.core.dto.report.StudentsWithNotesDto;

import java.util.List;
import java.util.Map;

public interface ReportTwoService {

    List<StudentNotComputerDto> studentNotComputerList(Integer year, Integer id);

    List<StudentsAnswerByAnswerByQuestionDto> percentsStudyFomrsByAnswer(Integer year);

    List<PercentsStudyHoursByAnswerDto> percentsStudyHoursByAnswer(Integer year, Integer id);

    List<String> studentsByPlaceEgress(Integer year, String placeEgress);

    List<StudentsWithNotesDto> studentsWithNotes(Integer year);

    List<StudentsWithNotesDto> entryDataByCourse(Integer year);

    List<StudentsWithNotesDto> entryDataByCourseByPlaceEgress(Integer year, String placeEgress);

    List<StudentsWithNotesDto> studentsByConfigurableNotes(Integer year, float academicIndex
                                                            , float noteSpanish, float noteMat, float noteHistory);


    List<String> studentsByEntrySource(Integer year, String entrySource);
}
