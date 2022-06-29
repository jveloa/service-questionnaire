package cu.edu.mes.sigenu.training.core.service;



import cu.edu.mes.sigenu.training.core.dto.question.StudentNotComputerDto;
import cu.edu.mes.sigenu.training.core.dto.question.StudentsAnswerByAnswerByQuestionDto;

import java.util.List;

public interface ReportTwoService {

    List<StudentNotComputerDto> studentNotComputerList(Integer year);

    List<StudentsAnswerByAnswerByQuestionDto> percentsStudyFomrsByAnswer(Integer year);


}
