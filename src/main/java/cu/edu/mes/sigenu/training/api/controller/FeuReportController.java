package cu.edu.mes.sigenu.training.api.controller;

import cu.edu.mes.sigenu.training.core.dto.report.DeportArtListDto;
import cu.edu.mes.sigenu.training.core.dto.report.ResponsabilityReportDto;
import cu.edu.mes.sigenu.training.core.dto.report.StudentArtDto;
import cu.edu.mes.sigenu.training.core.dto.report.StudentSportDto;
import cu.edu.mes.sigenu.training.core.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@Api(tags = "Feu Report enpoint controller")
@RequestMapping(value = "/reportFeu",produces = MediaType.APPLICATION_JSON_VALUE)
public class FeuReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/responsabilityReport/{year}")
    @ApiOperation(value = "List of students who are interested in occupying responsibilities")
    public List<ResponsabilityReportDto> responsabilityReport(@PathVariable Integer year) {
        return reportService.responsabilityReport(year);

    }

    @GetMapping("/studentSportList/{year}")
    @ApiOperation(value = "Get all the students who play sports and which ones")
    public List<StudentSportDto> studentSportList(@PathVariable Integer year) {
        return reportService.studentSportList(year);

    }

    @GetMapping("/studentArtList/{year}")
    @ApiOperation(value = "Get all the students who practice arts and which ones")
    public List<StudentArtDto> studentArtList(@PathVariable Integer year) {
        return reportService.studentArtList(year);

    }

    @GetMapping("/deportArtListByStudent/{studentSigenuId}")
    @ApiOperation(value = "get all the sports and arts that a student practices")
    public DeportArtListDto deportArtListByStudent(@PathVariable String studentSigenuId) {
        return reportService.deportArtListByStudent(studentSigenuId);

    }




}
