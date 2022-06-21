package cu.edu.mes.sigenu.training.core.dto.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
public class ResponsabilityReportDto {
    private String name;
    private String studentSigenuId;
    private String questionInterest;
    private String answerInterest;
    private String questionExp;
    private String answerExp;
    private String questionOrg;
    private String answerOrg;
}
