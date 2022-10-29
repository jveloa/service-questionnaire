package cu.edu.mes.sigenu.training.core.dto.report;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StudentSportDto {
    private String name;
    private String studentSigenuId;
    private List<String> sports;
}
