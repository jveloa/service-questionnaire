package cu.edu.mes.sigenu.training.core.dto.question;


import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class StudentsAnswerByAnswerByQuestionDto {

    private String nameQuestion;
    private Map<String,Float> answerList;


}
