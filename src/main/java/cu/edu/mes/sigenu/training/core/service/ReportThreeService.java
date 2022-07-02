package cu.edu.mes.sigenu.training.core.service;

import java.util.List;

import cu.edu.mes.sigenu.training.core.model.StudentAnswer;

public interface ReportThreeService {
	int studentTotalByYear(Integer year);
	
	int interpretationQuestionTotalByYear(Integer year);
	
	float studentCorrectInterpretation(Integer year);
	
	float studentWrongInterpretation(Integer year);
	
	float studentWhoEnterCareerBecauseTheyLikeIt(Integer year);
	
	float studentWhoEnterCareerBecauseTheyPleaseParents(Integer year);
	
	int contestsQuestionTotalByYear(Integer year);
	
	float studentWhoNeverMadeContests(Integer year);
	
	float studentWhoMadeMathContest(Integer year);
	
	float studentWhoMadeSpanishContest(Integer year);
	
	float studentWhoMadeChemistryContest(Integer year);
	
	float studentWhoMadeBiologyContest(Integer year);
	
	float studentWhoMadeInformaticsContest(Integer year);
	
	float studentWhoMadePoliticCultureContest(Integer year);
	
	float studentWhoMadeEnglishContest(Integer year);
	
	float studentWhoMadePhysicsContest(Integer year);
	
	float studentWhoMadeHistoryContest(Integer year);
	
	float studentWhoMadeContestByYearBySubject(Integer year, String subject);
	
	List<String> studentsCorrectInterpretation (Integer year);
	
	List<StudentAnswer> studentCorrectInterpretationList (Integer year);

}
