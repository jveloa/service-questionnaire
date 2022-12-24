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
@Api(tags = "Feu Report endpoint controller")
@RequestMapping(value = "/reportFeu",produces = MediaType.APPLICATION_JSON_VALUE)
public class FeuReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/responsabilityReport/{year}/{questionnarieId}")
    @ApiOperation(value = "List of students who are interested in occupying responsibilities")
    public List<ResponsabilityReportDto> responsabilityReport(@PathVariable Integer year, @PathVariable Integer questionnarieId) {
        return reportService.responsabilityReport(year,questionnarieId);

    }

    @GetMapping("/studentSportList/{year}/{questionnarieId}")
    @ApiOperation(value = "Get all the students who play sports and which ones")
    public List<StudentSportDto> studentSportList(@PathVariable Integer year, @PathVariable Integer questionnarieId) {
        return reportService.studentSportList(year,questionnarieId);

    }

    @GetMapping("/studentArtList/{year}/{questionnarieId}")
    @ApiOperation(value = "Get all the students who practice arts and which ones")
    public List<StudentArtDto> studentArtList(@PathVariable Integer year,@PathVariable Integer questionnarieId) {
        return reportService.studentArtList(year,questionnarieId);

    }

    @GetMapping("/deportArtListByStudent/{year}/{questionnarieId}")
    @ApiOperation(value = "get all the sports and arts that a student practices")
    public List<DeportArtListDto> deportArtListByStudent(@PathVariable Integer year,@PathVariable Integer questionnarieId) {
        return reportService.deportArtListByStudent(year,questionnarieId);

    }




}
