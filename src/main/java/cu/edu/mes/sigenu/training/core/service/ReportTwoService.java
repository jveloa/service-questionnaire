package cu.edu.mes.sigenu.training.core.service;



import cu.edu.mes.sigenu.training.core.dto.question.StudentNotComputerDto;

import java.util.List;

public interface ReportTwoService {

    List<StudentNotComputerDto> studentNotComputerList(Integer year);


}
