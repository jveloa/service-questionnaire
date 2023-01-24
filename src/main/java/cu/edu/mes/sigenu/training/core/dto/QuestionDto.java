package cu.edu.mes.sigenu.training.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Integer id;
    private String nameQuestion;
	private Integer groupQuestionId;
    private boolean isEvaluationQuestion;
    private boolean isSpecificQuestion;
    private boolean isCanceled;
}
