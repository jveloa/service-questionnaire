package cu.edu.mes.sigenu.training.core.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
public class ResponsabilityReportDto {
    private String name;
    private String studentSigenuId;
    private String answerInterest;
    private String answerExp;
    private String answerOrg;
}
