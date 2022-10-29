package cu.edu.mes.sigenu.training.core.dto.report;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DeportArtListDto {
    private List<String> sports;
    private List<String> arts;

}
