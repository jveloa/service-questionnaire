package cu.edu.mes.sigenu.training.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireStudentDto {

    private String studentSigenuId;
    private String identification;
    private Date doneDate;
    private Integer id;
    private QuestionnaireDto questionnarieId;
}
