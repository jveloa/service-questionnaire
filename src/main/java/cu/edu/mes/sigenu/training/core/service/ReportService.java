package cu.edu.mes.sigenu.training.core.service;



import cu.edu.mes.sigenu.training.core.dto.report.DeportArtListDto;
import cu.edu.mes.sigenu.training.core.dto.report.ResponsabilityReportDto;
import cu.edu.mes.sigenu.training.core.dto.report.StudentArtDto;
import cu.edu.mes.sigenu.training.core.dto.report.StudentSportDto;

import java.util.List;

public interface ReportService {

    List<ResponsabilityReportDto> responsabilityReport(Integer year, Integer questionnarieId);

    List<StudentSportDto> studentSportList(Integer year, Integer questionnarieId);

    List<StudentArtDto> studentArtList(Integer year, Integer questionnarieId);

    List<DeportArtListDto> deportArtListByStudent(Integer year, Integer questionnarieId);
}
