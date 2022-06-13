package cu.edu.mes.sigenu.training.core.dto.question;

import cu.edu.mes.sigenu.training.core.model.Questionnaire;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionnaireStudentDto {

    private String studentSigenuId;
    private Date doneDate;
    private Integer id;
    private QuestionnaireDto questionnarieId;
}
