package cu.edu.mes.sigenu.training.core.dto.report;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CareerOptionsDto {
    private Integer quantityOptionOne;
    private Integer quantityOptionTwo;
    private Integer quantityOptionThree;
    private Integer quantityOptionPlusThree;

}
