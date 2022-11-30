package cu.edu.mes.sigenu.training.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWithCareerDto {

    private Integer id;
    private Integer groupQuestionId;
    private String question;
    private String questionCarrerId;
}

