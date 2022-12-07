package cu.edu.mes.sigenu.training.core.service;



import cu.edu.mes.sigenu.training.core.dto.report.*;

import java.util.List;
import java.util.Map;

public interface ReportTwoService {

    List<StudentNotComputerDto> studentNotComputerList(Integer year, Integer id);

    List<StudentsAnswerByAnswerByQuestionDto> percentsStudyFomrsByAnswer(Integer year,Integer id);

    List<PercentsStudyHoursByAnswerDto> percentsStudyHoursByAnswer(Integer year, Integer id);

    List<String> studentsByPlaceEgress(Integer year, String placeEgress, Integer id);

    List<StudentsNotesDto> studentsWithNotes(Integer year, Integer id);

    List<StudentsWithNotesDto> entryDataByCourse(Integer year, Integer id);

    List<StudentsWithNotesDto> entryDataByCourseByPlaceEgress(Integer year, String placeEgress, Integer id);

    List<StudentsNotesDto> studentsByConfigurableNotes(Integer year, float academicIndex
                                                            , float noteSpanish, float noteMat, float noteHistory
                                                           ,Integer id);


    List<String> studentsByEntrySource(Integer year, String idEntrySource, Integer id);

    List<EntrySourceAuxDto> getAllEntrySource();
}
