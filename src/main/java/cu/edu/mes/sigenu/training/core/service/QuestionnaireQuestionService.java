package cu.edu.mes.sigenu.training.core.service;





public interface QuestionnaireQuestionService {

    void addQuestionToQuestionnaire (Integer questionnaire_id, Integer question_id);

    void deleteQuestionToQuestionnaire (Integer questionnaire_id, Integer question_id);

}
