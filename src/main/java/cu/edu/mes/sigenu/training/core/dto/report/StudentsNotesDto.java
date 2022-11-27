package cu.edu.mes.sigenu.training.core.dto.report;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class StudentsNotesDto {
    private String name;
    private  Float  noteAve;
    private  Float  noteMat;
    private  Float  noteHistory;
    private  Float  noteSpanish;
}
