package cu.edu.mes.sigenu.training.core.service;


import cu.edu.mes.sigenu.training.core.dto.QuestionDto;
import cu.edu.mes.sigenu.training.core.dto.QuestionnaireQuestionByGroupDto;

import java.util.List;

public interface QuestionnaireQuestionService {
	
	List<QuestionDto> getQuestionsByQuestionnaireId(Integer questionnaireId);

    void addQuestionToQuestionnaire(Integer questionnaireId, Integer questionId);

    void deleteQuestionToQuestionnaire(Integer questionnaireId, Integer questionId);

    List<QuestionnaireQuestionByGroupDto> getQuestionsByQuestionnaire(Integer questionnaireId);
}
