package cu.edu.mes.sigenu.training.service;


import cu.edu.mes.sigenu.training.core.model.QuestionnaireStudent;
import cu.edu.mes.sigenu.training.core.repository.QuestionnaireStudentRepository;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireStudentService;
import cu.edu.mes.sigenu.training.core.service.SigenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireStudentServiceImpl implements QuestionnaireStudentService {

    @Autowired
    private QuestionnaireStudentRepository questionnaireStudentRepository;

    @Autowired
    private SigenuService sigenuService;

	@Override
	public List<String> listAllByQuestionnaire(Integer questionnaireId) {
		return questionnaireStudentRepository.findAllByQuestionnaire(questionnaireId);
	}

	@Override
	public QuestionnaireStudent findById(Integer id) {
		if (questionnaireStudentRepository.existsById(id))
            return questionnaireStudentRepository.findById(id).get();
        else
            return new QuestionnaireStudent();
	}

	@Override
	public QuestionnaireStudent save(QuestionnaireStudent questionnaireStudent, String identification) {
		String studentSigenuId = sigenuService.getStudentIdByIdentification(identification);
        questionnaireStudent.setStudentSigenuId(studentSigenuId);
        return questionnaireStudentRepository.save(questionnaireStudent);
	}
}
