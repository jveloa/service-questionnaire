package cu.edu.mes.sigenu.training.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerDto {
    private Integer id;
    private Integer questionId;
    private Integer answerId;
    private boolean isCanceled;
    private CorrectAnswerDto correctAnswer;
}
