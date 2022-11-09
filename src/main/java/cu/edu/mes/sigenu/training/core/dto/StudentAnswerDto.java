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
    private String identification;
    private String studentSigenuId;
    private QuestionAnswerDto questionAnswerId;

}
