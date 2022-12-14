package cu.edu.mes.sigenu.training.core.dto;

import cu.edu.mes.sigenu.training.core.model.CorrectAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerDto {
    private Integer id;
    private Integer questionId;
    private Integer answerId;
    private CorrectAnswerDto correctAnswer;
}
