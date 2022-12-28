package cu.edu.mes.sigenu.training.api.controller;




import cu.edu.mes.sigenu.training.core.dto.report.*;
import cu.edu.mes.sigenu.training.core.service.ReportThreeService;
import cu.edu.mes.sigenu.training.core.service.ReportTwoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Career Chief Report endpoint controller")
@RequestMapping(value = "/reportCareerChief",produces = MediaType.APPLICATION_JSON_VALUE)
public class CareerChiefReportController {

    @Autowired
    private ReportTwoService reportTwoService;
    
    @Autowired
    private ReportThreeService ReportThreeService;

    @GetMapping("/studentNotComputerList/{year}/{questionnarieId}")
    @ApiOperation(value = "Return all students who do not have a computer")
    public List<StudentNotComputerDto> studentNotComputerList(@PathVariable Integer year, @PathVariable Integer questionnarieId) {
        return reportTwoService.studentNotComputerList(year,questionnarieId);

    }

    @GetMapping("/percentsStudyFomrsByAnswer/{year}/{questionnarieId}")
    @ApiOperation(value = "Percent of the frequency with which students use each form of study by academic year")
    public List<StudentsAnswerByAnswerByQuestionDto> percentsStudyFomrsByAnswer(@PathVariable Integer year, @PathVariable Integer questionnarieId) {
        return reportTwoService.percentsStudyFomrsByAnswer(year,questionnarieId);

    }

    @GetMapping("/entryDataByCourse/{year}/{questionnarieId}")
    @ApiOperation(value = "Returns the data entry by course")
    public List<StudentsWithNotesDto> entryDataByCourse(@PathVariable Integer year, @PathVariable Integer questionnarieId) {
        return reportTwoService.entryDataByCourse(year,questionnarieId);

    }

    @GetMapping("/studentsByConfigurableNotes/{year}/{academicIndex}/{noteSpanish}/{noteMat}/{noteHistory}/{questionnarieId}")
    @ApiOperation(value = "List of students by index and entrance grades configurable")
    public List<StudentsNotesDto> studentsByConfigurableNotes(@PathVariable Integer year, @PathVariable Float academicIndex
                                                                 , @PathVariable Float noteSpanish, @PathVariable Float noteMat
                                                                 , @PathVariable Float noteHistory, @PathVariable Integer questionnarieId) {
        return reportTwoService.studentsByConfigurableNotes(year,academicIndex,noteSpanish,noteMat,noteHistory,questionnarieId);

    }

    @GetMapping("/studentsWithNotes/{year}/{questionnarieId}")
    @ApiOperation(value = "List of students by index and entrance grades")
    public List<StudentsNotesDto> studentsWithNotes(@PathVariable Integer year, @PathVariable Integer questionnarieId) {
        return reportTwoService.studentsWithNotes(year,questionnarieId);

    }

    @GetMapping("/studentsByPlaceEgress/{year}/{idPlaceEgress}/{questionnarieId}")
    @ApiOperation(value = "Return all students by place of egress")
    public List<String> studentsByPlaceEgress(@PathVariable Integer year,@PathVariable String idPlaceEgress,@PathVariable Integer questionnarieId) {
        return reportTwoService.studentsByPlaceEgress(year,idPlaceEgress,questionnarieId);
    }

    @GetMapping("/entryDataByCourseByPlaceEgress/{year}/{idPlaceEgress}/{questionnarieId}")
    @ApiOperation(value = "Returns entry data by place of egress.")
    public List<StudentsWithNotesDto> entryDataByCourseByPlaceEgress(@PathVariable Integer year,@PathVariable String idPlaceEgress, @PathVariable Integer questionnarieId) {
        return reportTwoService.entryDataByCourseByPlaceEgress(year,idPlaceEgress,questionnarieId);
    }

    @GetMapping("/percentsStudyHoursByAnswer/{year}/{questionnarieId}")
    @ApiOperation(value = "Percent of the frequency of students who dedicate different ranges of hours per week to study.")
    public List<PercentsStudyHoursByAnswerDto> percentsStudyHoursByAnswer(@PathVariable Integer year, @PathVariable Integer questionnarieId) {
        return reportTwoService.percentsStudyHoursByAnswer(year,questionnarieId);

    }

    @GetMapping("/studentCorrectInterpretationPercent/{year}")
    @ApiOperation(value = "Return the percent of students who have all interpretation questions with correct answers")
    public float studentCorrectInterpretation(@PathVariable Integer year) {
        return ReportThreeService.studentCorrectInterpretation(year);
    }
    
    @GetMapping("/studentWrongInterpretationPercent/{year}")
    @ApiOperation(value = "Return the percent of students who could not answer correctly any interpretation questions")
    public float studentWrongInterpretation(@PathVariable Integer year) {
        return ReportThreeService.studentWrongInterpretation(year);
    }
    
    @GetMapping("/studentWhoEnterCareerBecauseTheyLikeItPercent/{year}")
    @ApiOperation(value = "Return the percent of students who declare they entered to the career because they like it")
    public float studentWhoEnterCareerBecauseTheyLikeIt(@PathVariable Integer year) {
        return ReportThreeService.studentWhoEnterCareerBecauseTheyLikeIt(year);
    }    
    
    @GetMapping("/studentWhoEnterCareerBecauseTheyPleaseParentsPercent/{year}")
    @ApiOperation(value = "Return the percent of students who declare they entered to the career because their parents's influence")
    public float studentWhoEnterCareerBecauseTheyPleaseParents(@PathVariable Integer year) {
        return ReportThreeService.studentWhoEnterCareerBecauseTheyPleaseParents(year);
    }
    
    @GetMapping("/studentWhoNeverMadeContestPercent/{year}")
    @ApiOperation(value = "Return the percent of students who never made a contest of any type")
    public float studentWhoNeverMadeContest(@PathVariable Integer year) {
        return ReportThreeService.studentWhoNeverMadeContests(year);
    }
    
    @GetMapping("/studentWhoMadeContestByYearBySubjectPercent/{year}/{subject}")
    @ApiOperation(value = "Return the percent of students who made a determined contest")
    public float studentWhoMadeContestByYearBySubject(@PathVariable Integer year,@PathVariable String subject) {
        return ReportThreeService.studentWhoMadeContestByYearBySubject(year,subject);
    }
    
    @GetMapping("/studentsCorrectInterpretationList/{year}")
    @ApiOperation(value = "Return the list of all students who have all interpretation questions with correct answers")
    public List<String> studentsCorrectInterpretation(@PathVariable Integer year) {
        return ReportThreeService.studentsCorrectInterpretation(year);
    }
    
    @GetMapping("/studentWhoNotReadPercent/{year}")
    @ApiOperation(value = "Return the percent of students who not read")
    public float studentWhoNotRead(@PathVariable Integer year) {
        return ReportThreeService.studentWhoNotRead(year);
    }
    
    @GetMapping("/studentsWhoNotReadList/{year}")
    @ApiOperation(value = "Return the list of all students who not read")
    public List<String> studentsWhoNotRead(@PathVariable Integer year) {
        return ReportThreeService.studentsWhoNotRead(year);
    }
    
    @GetMapping("/studentsWrongInterpretationList/{year}")
    @ApiOperation(value = "Return the list of all students who could not answer correctly any interpretation questions")
    public List<String> studentsWrongInterpretation(@PathVariable Integer year) {
        return ReportThreeService.studentsWrongInterpretation(year);
    }
    
    @GetMapping("/studentWhoRegularyReadPercent/{year}")
    @ApiOperation(value = "Return the percent of students who read not schoolar books regulary")
    public float studentWhoRegularyRead(@PathVariable Integer year) {
        return ReportThreeService.studentWhoRegularyRead(year);
    }
    
    @GetMapping("/studentWhoOnlyReadSchoolPercent/{year}")
    @ApiOperation(value = "Return the percent of students who only read schoolar books")
    public float studentWhoOnlyReadSchool(@PathVariable Integer year) {
        return ReportThreeService.studentWhoOnlyReadSchool(year);
    }

    @GetMapping("/studentsByEntrySource/{year}/{idEntrySource}/{questionnarieId}")
    @ApiOperation(value = "Return all students by entry source")
    public List<String> studentsByEntrySource(@PathVariable Integer year,@PathVariable String idEntrySource, @PathVariable Integer questionnarieId) {
        return reportTwoService.studentsByEntrySource(year,idEntrySource,questionnarieId);
    }

    @GetMapping("/allEntrySource/")
    @ApiOperation(value = "Return all  entry source")
    public List<EntrySourceAuxDto> getAllEntrySource() {
        return reportTwoService.getAllEntrySource();
    }

    @GetMapping("/allPlaceEgress/")
    @ApiOperation(value = "Return all  place egress")
    public List<EntrySourceAuxDto> getAllPlaceEgress() {
        return reportTwoService.getAllPlaceEgress();
    }

    @GetMapping("/allYear/{questionnarieId}")
    @ApiOperation(value = "Return all years")
    public List<String> getAllYears(@PathVariable Integer questionnarieId) {
        return reportTwoService.getAllYears(questionnarieId);
    }

}