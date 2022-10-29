package cu.edu.mes.sigenu.training.core.dto;

import cu.edu.mes.sigenu.training.core.model.Question;
import lombok.Data;

@Data
public class QuestionCarrerDto {
    private Integer id;
    private Integer questionId;
    private String careerSigenuId;

}
