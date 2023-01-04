package cu.edu.mes.sigenu.training.core.service;


import cu.edu.mes.sigenu.training.core.dto.QuestionDto;
import cu.edu.mes.sigenu.training.core.dto.QuestionnaireQuestionByGroupDto;

import java.util.List;

public interface QuestionnaireQuestionService {

    void addQuestionToQuestionnaire (Integer questionnaire_id, Integer question_id);

    void deleteQuestionToQuestionnaire (Integer questionnaire_id, Integer question_id);

    List<QuestionnaireQuestionByGroupDto> getQuestionsByQuestionnaire(Integer questionnaireId);

    List<QuestionDto> getQuestionsByQuestionnaireId(Integer questionnaire_id);

}
