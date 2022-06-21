package cu.edu.mes.sigenu.training.core.service;



import cu.edu.mes.sigenu.training.core.dto.question.ResponsabilityReportDto;

import java.util.List;

public interface ReportService {

    List<ResponsabilityReportDto> responsabilityReport(Integer year);
}
