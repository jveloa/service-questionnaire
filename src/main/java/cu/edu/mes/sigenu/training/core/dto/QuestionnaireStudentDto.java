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
	private Integer id;
	private String studentSigenuId;
    private String identification;
    private Date doneDate;
    private QuestionnaireDto questionnaireId;
}
