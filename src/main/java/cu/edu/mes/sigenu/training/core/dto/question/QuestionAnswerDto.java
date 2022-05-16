package cu.edu.mes.sigenu.training.core.dto.question;

import lombok.Data;

@Data
public class QuestionAnswerDto {
    private Integer id;
    private Integer questionId;
    private String answer;
}
