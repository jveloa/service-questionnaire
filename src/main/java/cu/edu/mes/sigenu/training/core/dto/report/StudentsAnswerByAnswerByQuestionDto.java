package cu.edu.mes.sigenu.training.core.dto.report;


import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class StudentsAnswerByAnswerByQuestionDto {

    private String nameQuestion;
    private Float percentMuch;
    private Float percentLittle;
    private Float percentNot;
    private Float percentNever;


}
