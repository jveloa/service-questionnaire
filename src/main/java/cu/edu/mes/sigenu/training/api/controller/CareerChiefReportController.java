package cu.edu.mes.sigenu.training.api.controller;




import cu.edu.mes.sigenu.training.core.dto.question.StudentNotComputerDto;
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

@RestController
@Api(tags = "Career Chief Report enpoint controller")
@RequestMapping(value = "/reportCareerChief",produces = MediaType.APPLICATION_JSON_VALUE)
public class CareerChiefReportController {

    @Autowired
    private ReportTwoService reportTwoService;
    
    @Autowired
    private ReportThreeService ReportThreeService;

    @GetMapping("/studentNotComputerList/{year}")
    @ApiOperation(value = "Return all students who do not have a computer")
    public List<StudentNotComputerDto> studentNotComputerList(@PathVariable Integer year) {
        return reportTwoService.studentNotComputerList(year);

    }

    @GetMapping("/studentCorrectInterpretation/{year}")
    @ApiOperation(value = "Return all students who have all interpretation questions with correct answers")
    public float studentCorrectInterpretation(@PathVariable Integer year) {
        return ReportThreeService.studentCorrectInterpretation(year);
    }
}
