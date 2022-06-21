package cu.edu.mes.sigenu.training.api.controller;

import cu.edu.mes.sigenu.training.core.dto.question.ResponsabilityReportDto;
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
    @ApiOperation(value = "Get resposability report")
    public List<ResponsabilityReportDto> list(@PathVariable Integer year) {
        return reportService.responsabilityReport(year);

    }
}
