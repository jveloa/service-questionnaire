package cu.edu.mes.sigenu.training.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.mes.sigenu.training.core.model.StudentAnswer;
import cu.edu.mes.sigenu.training.core.repository.QuestionRepository;
import cu.edu.mes.sigenu.training.core.repository.QuestionnaireStudentRepository;
import cu.edu.mes.sigenu.training.core.repository.StudentAnswerRepository;
import cu.edu.mes.sigenu.training.core.service.ReportThreeService;
import cu.edu.mes.sigenu.training.core.utils.Client;
import cu.edu.mes.subsystem.student.vo.StudentVO;

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
		int listSize  = studentCorrectInterpretationList(year).size();
		int totalStudents = studentTotalByYear(year);
		result = ((float) (listSize * 100)) / totalStudents;
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

	@Override
	public float studentWhoMadeMathContest(Integer year) {
		float result = 0;
		int totalStudents = studentTotalByYear(year);
		int studentMathContest = studentAnswerRepository.findStudentsWhoMadeMathContest(year).size();
		result = ((float) (studentMathContest * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWhoMadeSpanishContest(Integer year) {
		float result = 0;
		int totalStudents = studentTotalByYear(year);
		int studentSpanishContest = studentAnswerRepository.findStudentsWhoMadeSpanishContest(year).size();
		result = ((float) (studentSpanishContest * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWhoMadeChemistryContest(Integer year) {
		float result = 0;
		int totalStudents = studentTotalByYear(year);
		int studentChemistryContest = studentAnswerRepository.findStudentsWhoMadeChemistryContest(year).size();
		result = ((float) (studentChemistryContest * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWhoMadeBiologyContest(Integer year) {
		float result = 0;
		int totalStudents = studentTotalByYear(year);
		int studentBiologyContest = studentAnswerRepository.findStudentsWhoMadeBiologyContest(year).size();
		result = ((float) (studentBiologyContest * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWhoMadeInformaticsContest(Integer year) {
		float result = 0;
		int totalStudents = studentTotalByYear(year);
		int studentInformaticsContest = studentAnswerRepository.findStudentsWhoMadeInformaticsContest(year).size();
		result = ((float) (studentInformaticsContest * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWhoMadePoliticCultureContest(Integer year) {
		float result = 0;
		int totalStudents = studentTotalByYear(year);
		int studentPoliticCultureContest = studentAnswerRepository.findStudentsWhoMadePoliticCultureContest(year).size();
		result = ((float) (studentPoliticCultureContest * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWhoMadeEnglishContest(Integer year) {
		float result = 0;
		int totalStudents = studentTotalByYear(year);
		int studentEnglishContest = studentAnswerRepository.findStudentsWhoMadeEnglishContest(year).size();
		result = ((float) (studentEnglishContest * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWhoMadePhysicsContest(Integer year) {
		float result = 0;
		int totalStudents = studentTotalByYear(year);
		int studentPhysicsContest = studentAnswerRepository.findStudentsWhoMadePhysicsContest(year).size();
		result = ((float) (studentPhysicsContest * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWhoMadeHistoryContest(Integer year) {
		float result = 0;
		int totalStudents = studentTotalByYear(year);
		int studentHistoryContest = studentAnswerRepository.findStudentsWhoMadeHistoryContest(year).size();
		result = ((float) (studentHistoryContest * 100)) / totalStudents;
		return result;
	}

	@Override
	public float studentWhoMadeContestByYearBySubject(Integer year, String subject) {
		float result = 0;
		switch(subject) {
		
		case "Math":
		result = studentWhoMadeMathContest(year);
		break;
		
		case "Spanish":
		result = studentWhoMadeSpanishContest(year);
		break;
		
		case "Chemistry":
		result = studentWhoMadeChemistryContest(year);
		break;
			
		case "Biology":
		result = studentWhoMadeBiologyContest(year);
		break;
			
		case "Informatics":
		result = studentWhoMadeInformaticsContest(year);
		break;
		
		case "PoliticCulture":
		result = studentWhoMadePoliticCultureContest(year);
		break;	
			
		case "English":
		result = studentWhoMadeEnglishContest(year);
		break;
			
		case "Physics":
		result = studentWhoMadePhysicsContest(year);
		break;
			
		case "History":
		result = studentWhoMadeHistoryContest(year);
		break;
		
		default: result = 0;
		}
		return result;
	}

	@Override
	public List<String> studentsCorrectInterpretation(Integer year) {
		List<String> studentsCorrectInterpretation = new ArrayList<>();
		List<StudentAnswer> studentCorrectInterpretation = studentCorrectInterpretationList(year);
		String auxiliar;
        for (int i = 0; i < studentCorrectInterpretation.size(); i++) {
                    StudentVO studentSigenu = getInfoStudent(studentCorrectInterpretation.get(i).getStudentSigenuId());
                    auxiliar = studentSigenu.getName() + " " + studentSigenu.getMiddleName() + " " + studentSigenu.getLastName();
                    studentsCorrectInterpretation.add(auxiliar);
        }
		return studentsCorrectInterpretation;
	}
	
	 public StudentVO getInfoStudent(String studentId) {
	        try {
	            return Client.getStudentSubsystem().getStudent(studentId);
	        } catch (RemoteException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
		private List<StudentAnswer> studentCorrectInterpretationList(Integer year) {
			List<StudentAnswer> studentCorrectInterpretationQuery = studentAnswerRepository.findStudentsCorrectAnswerInterpretation(year);
			int totalInterpretationQuestions = interpretationQuestionTotalByYear(year);
			List<StudentAnswer> studentCorrectInterpretationFinal = new ArrayList<>();
			int aux = 0;
	        for (int i = 1; i < studentCorrectInterpretationQuery.size(); i++) {
	            if (studentCorrectInterpretationQuery.get(i).getStudentSigenuId().equals(studentCorrectInterpretationQuery.get(i - 1).getStudentSigenuId())) {
	                aux++;
	                if (aux == totalInterpretationQuestions - 1) {
	                   aux = 0;
	                   studentCorrectInterpretationFinal.add(studentCorrectInterpretationQuery.get(i));	                   
	                }
	            }
	            else aux = 0;
	        }
			return studentCorrectInterpretationFinal;
		}

		@Override
		public float studentWhoNotRead(Integer year) {
			float result = 0;
			int listSize  = studentWhoNotReadList(year).size();
			int totalStudents = studentTotalByYear(year);
			result = ((float) (listSize * 100)) / totalStudents;
			return result;
		}

		@Override
		public List<String> studentsWhoNotRead(Integer year) {
			List<String> studentsWhoNotRead = new ArrayList<>();
			List<StudentAnswer> studentWhoNotRead = studentWhoNotReadList(year);
			String auxiliar;
	        for (int i = 0; i < studentWhoNotRead.size(); i++) {
	                    StudentVO studentSigenu = getInfoStudent(studentWhoNotRead.get(i).getStudentSigenuId());
	                    auxiliar = studentSigenu.getName() + " " + studentSigenu.getMiddleName() + " " + studentSigenu.getLastName();
	                    studentsWhoNotRead.add(auxiliar);
	        }
			return studentsWhoNotRead;
		}
		
		private List<StudentAnswer> studentWhoNotReadList(Integer year) {
			List<StudentAnswer> studentWhoNotReadQuery = studentAnswerRepository.findStudentsWhoNotRead(year);
			List<StudentAnswer> studentWhoNotReadFinal = new ArrayList<>();
			int aux = 0;
	        for (int i = 1; i < studentWhoNotReadQuery.size(); i++) {
	            if (studentWhoNotReadQuery.get(i).getStudentSigenuId().equals(studentWhoNotReadQuery.get(i - 1).getStudentSigenuId())) {
	                aux++;
	                if (aux == 2) {
	                   aux = 0;
	                   studentWhoNotReadFinal.add(studentWhoNotReadQuery.get(i));	                   
	                }
	            }
	            else aux = 0;
	        }
			return studentWhoNotReadFinal;
		}
}
