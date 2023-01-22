package cu.edu.mes.sigenu.training.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentAnswerDto {
    private Integer id;
    private String studentSigenuId;
    private String identification;
    private QuestionAnswerDto questionAnswerId;
}
