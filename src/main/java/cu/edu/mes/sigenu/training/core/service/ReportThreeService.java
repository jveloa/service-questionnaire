package cu.edu.mes.sigenu.training.core.service;

public interface ReportThreeService {
	int studentTotalByYear(Integer year);
	
	int interpretationQuestionTotalByYear(Integer year);
	
	float studentCorrectInterpretation(Integer year);
	
	float studentWrongInterpretation(Integer year);
	
	float studentWhoEnterCareerBecauseTheyLikeIt(Integer year);
	
	float studentWhoEnterCareerBecauseTheyPleaseParents(Integer year);

}
