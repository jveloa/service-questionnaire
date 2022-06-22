package cu.edu.mes.sigenu.training.core.service;



import cu.edu.mes.sigenu.training.core.dto.question.DeportArtListDto;
import cu.edu.mes.sigenu.training.core.dto.question.ResponsabilityReportDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentArtDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentSportDto;

import java.util.List;

public interface ReportService {

    List<ResponsabilityReportDto> responsabilityReport(Integer year);

    List<StudentSportDto> studentSportList(Integer year);

    List<StudentArtDto> studentArtList(Integer year);

    DeportArtListDto deportArtListByStudent(String studentSigenuId);
}
