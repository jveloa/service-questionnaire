package cu.edu.mes.sigenu.training.core.dto.report;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentNotComputerDto {
    private  String name;
    private String studentSigenuId;
}
