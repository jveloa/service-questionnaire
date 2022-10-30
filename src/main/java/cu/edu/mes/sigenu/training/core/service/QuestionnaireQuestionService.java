package cu.edu.mes.sigenu.training.core.service;


import cu.edu.mes.sigenu.training.core.dto.QuestionnaireQuestionDto;

import java.util.List;

public interface QuestionnaireQuestionService {

    void addQuestionToQuestionnaire (Integer questionnaire_id, Integer question_id);

    void deleteQuestionToQuestionnaire (Integer questionnaire_id, Integer question_id);

    List<QuestionnaireQuestionDto> getQuestionsByQuestionnaire(Integer questionnaireId);

}
