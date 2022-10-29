package cu.edu.mes.sigenu.training.core.dto;

import lombok.Data;

@Data
public class StudentAnswerDto {
    private Integer id;
    private String studentSigenuId;
    private QuestionAnswerDto questionAnswerId;

}
