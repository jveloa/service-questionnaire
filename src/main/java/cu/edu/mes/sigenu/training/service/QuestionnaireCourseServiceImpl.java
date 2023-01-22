package cu.edu.mes.sigenu.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.mes.sigenu.training.core.model.QuestionnaireCourse;
import cu.edu.mes.sigenu.training.core.repository.QuestionnaireCourseRepository;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireCourseService;

@Service
public class QuestionnaireCourseServiceImpl implements QuestionnaireCourseService {

	@Autowired
	private QuestionnaireCourseRepository questionnaireCourseRepository;

	@Override
	public List<QuestionnaireCourse> listAll() {
		return questionnaireCourseRepository.findAll();
	}

	@Override
	public QuestionnaireCourse findById(Integer id) {
		if (questionnaireCourseRepository.existsById(id))
            return questionnaireCourseRepository.findById(id).get();
        else
            return new QuestionnaireCourse();
	}

	@Override
	public QuestionnaireCourse save(QuestionnaireCourse questionnaireCourse) {
		return questionnaireCourseRepository.save(questionnaireCourse);
	}

	@Override
	public void delete(Integer id) {
		questionnaireCourseRepository.delete(new QuestionnaireCourse(id));
	}
}
