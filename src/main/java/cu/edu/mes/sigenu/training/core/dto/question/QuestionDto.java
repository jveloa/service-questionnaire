package cu.edu.mes.sigenu.training.core.dto.question;

import lombok.Data;

@Data
public class QuestionDto {

    private Integer id;
	private Integer idGroupQuestion;
	private String question;
	private String description;
}
