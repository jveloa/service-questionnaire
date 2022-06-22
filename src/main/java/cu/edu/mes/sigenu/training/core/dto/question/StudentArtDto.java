package cu.edu.mes.sigenu.training.core.dto.question;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StudentArtDto {
    private String name;
    private String studentSigenuId;
    private List<String> arts;
}
