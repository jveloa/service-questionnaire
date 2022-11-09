package cu.edu.mes.sigenu.training.core.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireQuestionDto {
    private QuestionDto question;
    private List<AnswerDto> answerList;
}
