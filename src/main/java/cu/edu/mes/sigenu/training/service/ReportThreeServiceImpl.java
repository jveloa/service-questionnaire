package cu.edu.mes.sigenu.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.mes.sigenu.training.core.model.StudentAnswer;
import cu.edu.mes.sigenu.training.core.repository.QuestionRepository;
import cu.edu.mes.sigenu.training.core.repository.QuestionnaireStudentRepository;
import cu.edu.mes.sigenu.training.core.repository.StudentAnswerRepository;
import cu.edu.mes.sigenu.training.core.service.ReportThreeService;

@Service
public class ReportThreeServiceImpl implements ReportThreeService{
	@Autowired
	private QuestionnaireStudentRepository questionnaireStudentRepository;
	
	@Autowired
	private StudentAnswerRepository studentAnswerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public int studentTotalByYear(Integer year) {
		int studentTotal = questionnaireStudentRepository.findAllByDoneDate(year).size();
		return studentTotal;
	}

	@Override
	public int interpretationQuestionTotalByYear(Integer year) {
		int interpretationQuestionTotal = questionRepository.findInterpretativeQuestions(year).size();
		return interpretationQuestionTotal;
	}

	@Override
	public float studentCorrectInterpretation(Integer year) {
		float result = 0;
		List<StudentAnswer> studentCorrectInterpretation = studentAnswerRepository.findStudentsCorrectAnswerInterpretation(year);
		int totalStudents = studentTotalByYear(year);
		int totalInterpretationQuestions = interpretationQuestionTotalByYear(year);
		int count = 0, aux = 0;
        for (int i = 1; i < studentCorrectInterpretation.size(); i++) {
            if (studentCorrectInterpretation.get(i).getStudentSigenuId().equals(studentCorrectInterpretation.get(i - 1).getStudentSigenuId())) {
                aux++;
                if (aux == totalInterpretationQuestions - 1) {
                    count++;
                    aux = 0;
                }
            }
            else aux = 0;
        }
		result = ((float) (count * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWrongInterpretation(Integer year) {
		float result = 0;
		List<StudentAnswer> studentWrongInterpretation = studentAnswerRepository.findStudentsWrongAnswerInterpretation(year);
		int totalStudents = studentTotalByYear(year);
		int totalInterpretationQuestions = interpretationQuestionTotalByYear(year);
		int count = 0, aux = 0;
        for (int i = 1; i < studentWrongInterpretation.size(); i++) {
            if (studentWrongInterpretation.get(i).getStudentSigenuId().equals(studentWrongInterpretation.get(i - 1).getStudentSigenuId())) {
                aux++;
                if (aux == totalInterpretationQuestions - 1) {
                    count++;
                    aux = 0;
                }
            }
            else aux = 0;
        }
		result = ((float) (count * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWhoEnterCareerBecauseTheyLikeIt(Integer year) {
		float result = 0;
		List<StudentAnswer>studentWhoEnterCareerBecauseTheyLikeIt = studentAnswerRepository.findStudentsWhoEnterCareerBecauseTheyLikeIt(year);
		int totalStudents = studentTotalByYear(year);
		int totalStudentsWhoEnterCareerBecauseTheyLikeIt = studentWhoEnterCareerBecauseTheyLikeIt.size();
		result = ((float) (totalStudentsWhoEnterCareerBecauseTheyLikeIt * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWhoEnterCareerBecauseTheyPleaseParents(Integer year) {
		float result = 0;
		List<StudentAnswer> studentWhoEnterCareerBecauseTheyPleaseParents = studentAnswerRepository.findStudentsWhoEnterCareerBecauseTheyPleaseParents(year);
		int totalStudents = studentTotalByYear(year);
		int totalStudentsWhoEnterCareerBecauseTheyPleaseParents = studentWhoEnterCareerBecauseTheyPleaseParents.size();
		result = ((float) (totalStudentsWhoEnterCareerBecauseTheyPleaseParents * 100)) / totalStudents;
		return result;
	}

	@Override
	public int contestsQuestionTotalByYear(Integer year) {
		int contestQuestionTotal = questionRepository.findContestQuestions(year).size();
		return contestQuestionTotal;
	}

	@Override
	public float studentWhoNeverMadeContests(Integer year) {
		float result = 0;
		List<StudentAnswer> studentWhoNeverMadeContest = studentAnswerRepository.findStudentsWhoNeverMadeContest(year);
		int totalStudents = studentTotalByYear(year);
		int totalContestQuestion = contestsQuestionTotalByYear(year);
		int aux = 0, count = 0;
		
		for(int i = 1; i < studentWhoNeverMadeContest.size(); i++) {
			 if (studentWhoNeverMadeContest.get(i).getStudentSigenuId().equals(studentWhoNeverMadeContest.get(i - 1).getStudentSigenuId())) {
	                aux++;
	                if (aux == totalContestQuestion - 1) {
	                    count++;
	                    aux = 0;
	                }
	            }
	            else aux = 0;
		}
		result = ((float) (count * 100)) / totalStudents;
		return result;
	}

}
