package cu.edu.mes.sigenu.training.core.dto;

import cu.edu.mes.sigenu.training.core.model.Question;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionnaireQuestionDto {
    private QuestionDto question;
    private List<AnswerDto> answerList;
}
