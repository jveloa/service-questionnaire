package cu.edu.mes.sigenu.training.core.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionnaireStudentDto {

    private String studentSigenuId;
    private Date doneDate;
    private Integer id;
    private QuestionnaireDto questionnarieId;
}
