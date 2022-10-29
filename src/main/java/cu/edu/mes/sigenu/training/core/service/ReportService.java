package cu.edu.mes.sigenu.training.core.service;



import cu.edu.mes.sigenu.training.core.dto.report.DeportArtListDto;
import cu.edu.mes.sigenu.training.core.dto.report.ResponsabilityReportDto;
import cu.edu.mes.sigenu.training.core.dto.report.StudentArtDto;
import cu.edu.mes.sigenu.training.core.dto.report.StudentSportDto;

import java.util.List;

public interface ReportService {

    List<ResponsabilityReportDto> responsabilityReport(Integer year);

    List<StudentSportDto> studentSportList(Integer year);

    List<StudentArtDto> studentArtList(Integer year);

    DeportArtListDto deportArtListByStudent(String studentSigenuId);
}
