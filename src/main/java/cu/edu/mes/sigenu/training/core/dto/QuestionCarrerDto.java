package cu.edu.mes.sigenu.training.core.dto;

import cu.edu.mes.sigenu.training.core.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCarrerDto {
    private Integer id;
    private Integer questionId;
    private String careerSigenuId;

}
