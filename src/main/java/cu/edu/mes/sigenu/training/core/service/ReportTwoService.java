package cu.edu.mes.sigenu.training.core.service;



import cu.edu.mes.sigenu.training.core.dto.report.*;

import java.util.List;
import java.util.Map;

public interface ReportTwoService {

    List<StudentNotComputerDto> studentNotComputerList(Integer year, Integer questionnarieId);

    List<StudentsAnswerByAnswerByQuestionDto> percentsStudyFomrsByAnswer(Integer year,Integer questionnarieId);

    List<PercentsStudyHoursByAnswerDto> percentsStudyHoursByAnswer(Integer year, Integer questionnarieId);

    List<String> studentsByPlaceEgress(Integer year, String idPlaceEgress, Integer questionnarieId);

    List<StudentsNotesDto> studentsWithNotes(Integer year, Integer questionnarieId);

    List<StudentsWithNotesDto> entryDataByCourse(Integer year, Integer questionnarieId);

    List<StudentsWithNotesDto> entryDataByCourseByPlaceEgress(Integer year, String idPlaceEgress, Integer questionnarieId);

    List<StudentsNotesDto> studentsByConfigurableNotes(Integer year, float academicIndex
                                                            , float noteSpanish, float noteMat, float noteHistory
                                                           ,Integer questionnarieId);


    List<String> studentsByEntrySource(Integer year, String idEntrySource, Integer questionnarieId);

    List<EntrySourceAuxDto> getAllEntrySource();

    List<EntrySourceAuxDto> getAllPlaceEgress();

    List<String> getAllYears(Integer questionnarieId);

}
