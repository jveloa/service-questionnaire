package cu.edu.mes.sigenu.training.core.utils;



import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import cu.edu.mes.services.common.AcademicLevelCatalog;
import cu.edu.mes.services.common.AcademicLevelCatalogUtil;
import cu.edu.mes.services.common.AcademicSituationCatalog;
import cu.edu.mes.services.common.AcademicSituationCatalogUtil;
import cu.edu.mes.services.common.AwardCatalog;
import cu.edu.mes.services.common.AwardCatalogUtil;
import cu.edu.mes.services.common.BonusCatalog;
import cu.edu.mes.services.common.BonusCatalogUtil;
import cu.edu.mes.services.common.CareerCatalog;
import cu.edu.mes.services.common.CareerCatalogUtil;
import cu.edu.mes.services.common.CountryCatalog;
import cu.edu.mes.services.common.CountryCatalogUtil;
import cu.edu.mes.services.common.CourseCatalog;
import cu.edu.mes.services.common.CourseCatalogUtil;
import cu.edu.mes.services.common.CourseTypeCatalog;
import cu.edu.mes.services.common.CourseTypeCatalogUtil;
import cu.edu.mes.services.common.DisciplineCatalog;
import cu.edu.mes.services.common.DisciplineCatalogUtil;
import cu.edu.mes.services.common.DropMotiveCatalog;
import cu.edu.mes.services.common.DropMotiveCatalogUtil;
import cu.edu.mes.services.common.EntrySourceCatalog;
import cu.edu.mes.services.common.EntrySourceCatalogUtil;
import cu.edu.mes.services.common.EvaluationReasonCatalog;
import cu.edu.mes.services.common.EvaluationReasonCatalogUtil;
import cu.edu.mes.services.common.EvaluationTypeCatalog;
import cu.edu.mes.services.common.EvaluationTypeCatalogUtil;
import cu.edu.mes.services.common.EvaluationValueCatalog;
import cu.edu.mes.services.common.EvaluationValueCatalogUtil;
import cu.edu.mes.services.common.ExaminationTypeCatalog;
import cu.edu.mes.services.common.ExaminationTypeCatalogUtil;
import cu.edu.mes.services.common.FacultyCatalog;
import cu.edu.mes.services.common.FacultyCatalogUtil;
import cu.edu.mes.services.common.GroupCatalog;
import cu.edu.mes.services.common.GroupCatalogUtil;
import cu.edu.mes.services.common.GroupTypeCatalog;
import cu.edu.mes.services.common.GroupTypeCatalogUtil;
import cu.edu.mes.services.common.HandicapCatalog;
import cu.edu.mes.services.common.HandicapCatalogUtil;
import cu.edu.mes.services.common.LicenceMotiveCatalog;
import cu.edu.mes.services.common.LicenceMotiveCatalogUtil;
import cu.edu.mes.services.common.MaritalStatusCatalog;
import cu.edu.mes.services.common.MaritalStatusCatalogUtil;
import cu.edu.mes.services.common.MilitarGradeCatalog;
import cu.edu.mes.services.common.MilitarGradeCatalogUtil;
import cu.edu.mes.services.common.MilitarSpecialtyCatalog;
import cu.edu.mes.services.common.MilitarSpecialtyCatalogUtil;
import cu.edu.mes.services.common.MilitarTypeCatalog;
import cu.edu.mes.services.common.MilitarTypeCatalogUtil;
import cu.edu.mes.services.common.NationalCareerCatalog;
import cu.edu.mes.services.common.NationalCareerCatalogUtil;
import cu.edu.mes.services.common.ONGCatalog;
import cu.edu.mes.services.common.ONGCatalogUtil;
import cu.edu.mes.services.common.OcupationCatalog;
import cu.edu.mes.services.common.OcupationCatalogUtil;
import cu.edu.mes.services.common.OrganismCatalog;
import cu.edu.mes.services.common.OrganismCatalogUtil;
import cu.edu.mes.services.common.OrphanCatalog;
import cu.edu.mes.services.common.OrphanCatalogUtil;
import cu.edu.mes.services.common.PoliticOrgCatalog;
import cu.edu.mes.services.common.PoliticOrgCatalogUtil;
import cu.edu.mes.services.common.PopularOrgCatalog;
import cu.edu.mes.services.common.PopularOrgCatalogUtil;
import cu.edu.mes.services.common.ProvinceCatalog;
import cu.edu.mes.services.common.ProvinceCatalogUtil;
import cu.edu.mes.services.common.ScholasticOriginCatalog;
import cu.edu.mes.services.common.ScholasticOriginCatalogUtil;
import cu.edu.mes.services.common.SciencEspecialtyCatalog;
import cu.edu.mes.services.common.SciencEspecialtyCatalogUtil;
import cu.edu.mes.services.common.SexCatalog;
import cu.edu.mes.services.common.SexCatalogUtil;
import cu.edu.mes.services.common.SkinColorCatalog;
import cu.edu.mes.services.common.SkinColorCatalogUtil;
import cu.edu.mes.services.common.StudentStatusCatalog;
import cu.edu.mes.services.common.StudentStatusCatalogUtil;
import cu.edu.mes.services.common.StudentTypeCatalog;
import cu.edu.mes.services.common.StudentTypeCatalogUtil;
import cu.edu.mes.services.common.StudyProgramCatalog;
import cu.edu.mes.services.common.StudyProgramCatalogUtil;
import cu.edu.mes.services.common.StudyRegimenCatalog;
import cu.edu.mes.services.common.StudyRegimenCatalogUtil;
import cu.edu.mes.services.common.SubjectSituationCatalog;
import cu.edu.mes.services.common.SubjectSituationCatalogUtil;
import cu.edu.mes.services.common.SubjectTypeCatalog;
import cu.edu.mes.services.common.SubjectTypeCatalogUtil;
import cu.edu.mes.services.common.SyndicateCatalog;
import cu.edu.mes.services.common.SyndicateCatalogUtil;
import cu.edu.mes.services.common.TownCatalog;
import cu.edu.mes.services.common.TownCatalogUtil;
import cu.edu.mes.services.common.TownUniversityCatalog;
import cu.edu.mes.services.common.TownUniversityCatalogUtil;
import cu.edu.mes.services.common.UniversityCatalog;
import cu.edu.mes.services.common.UniversityCatalogUtil;
import cu.edu.mes.services.dropstudent.DropDomainData;
import cu.edu.mes.services.dropstudent.DropDomainDataUtil;
import cu.edu.mes.services.dropstudent.DropStudent;
import cu.edu.mes.services.dropstudent.DropStudentUtil;
import cu.edu.mes.services.dropstudent.StudentStatusManager;
import cu.edu.mes.services.dropstudent.StudentStatusManagerUtil;
import cu.edu.mes.services.dss.DateCatalog;
import cu.edu.mes.services.dss.DateCatalogUtil;
import cu.edu.mes.services.enrollment.StudentMatriculateSess;
import cu.edu.mes.services.enrollment.StudentMatriculateSessUtil;
import cu.edu.mes.services.program.AssignedSubjectCatalog;
import cu.edu.mes.services.program.AssignedSubjectCatalogUtil;
import cu.edu.mes.services.program.BasedStudyProgramCatalog;
import cu.edu.mes.services.program.CustomStudyProgramCatalog;
import cu.edu.mes.services.program.CustomStudyProgramCatalogUtil;
import cu.edu.mes.services.program.MatriculatedSubjectCatalog;
import cu.edu.mes.services.program.MatriculatedSubjectCatalogUtil;
import cu.edu.mes.services.program.StudentManage;
import cu.edu.mes.services.program.StudentManageUtil;
import cu.edu.mes.services.program.StudyProgramServices;
import cu.edu.mes.services.program.StudyProgramServicesUtil;
import cu.edu.mes.services.reports.AssistantStudentReportService;
import cu.edu.mes.services.reports.AssistantStudentReportServiceUtil;
import cu.edu.mes.services.reports.GeneralReportCatalog;
import cu.edu.mes.services.reports.GeneralReportCatalogUtil;
import cu.edu.mes.services.reports.StudentReportCatalog;
import cu.edu.mes.services.reports.StudentReportCatalogUtil;
import cu.edu.mes.services.studentcontrol.CourseClosure;
import cu.edu.mes.services.studentcontrol.CourseClosureUtil;
import cu.edu.mes.services.studentcontrol.ServiceElectiveSubject;
import cu.edu.mes.services.studentcontrol.ServiceElectiveSubjectUtil;
import cu.edu.mes.services.studentcontrol.StudentsControl;
import cu.edu.mes.services.studentcontrol.StudentsControlUtil;
import cu.edu.mes.services.studentevaluation.EvaluationDomainData;
import cu.edu.mes.services.studentevaluation.EvaluationDomainDataUtil;
import cu.edu.mes.services.studentevaluation.ExaminationActaService;
import cu.edu.mes.services.studentevaluation.ExaminationActaServiceUtil;
import cu.edu.mes.services.studentevaluation.StudentEvaluation;
import cu.edu.mes.services.studentevaluation.StudentEvaluationUtil;
import cu.edu.mes.subsystem.professor.service.PlanningService;
import cu.edu.mes.subsystem.professor.service.PlanningServiceUtil;
import cu.edu.mes.subsystem.professor.service.ProfessorReportsService;
import cu.edu.mes.subsystem.professor.service.ProfessorReportsServiceUtil;
import cu.edu.mes.subsystem.professor.service.ProfessorService;
import cu.edu.mes.subsystem.professor.service.ProfessorServiceUtil;
import cu.edu.mes.subsystem.security.AuthenticationService;
import cu.edu.mes.subsystem.security.AuthenticationServiceUtil;
import cu.edu.mes.subsystem.security.SecurityLevelService;
import cu.edu.mes.subsystem.security.SecurityLevelServiceUtil;
import cu.edu.mes.subsystem.security.SecurityRoleService;
import cu.edu.mes.subsystem.security.SecurityRoleServiceUtil;
import cu.edu.mes.subsystem.security.UserService;
import cu.edu.mes.subsystem.security.UserServiceUtil;
import cu.edu.mes.subsystem.student.StudentSubsystem;
import cu.edu.mes.subsystem.student.StudentSubsystemUtil;
import cu.edu.mes.subsystem.studyprogram.AdjustmentSubjectSubsystem;
import cu.edu.mes.subsystem.studyprogram.AdjustmentSubjectSubsystemUtil;
import cu.edu.mes.subsystem.studyprogram.OptionalCourseSubsystem;
import cu.edu.mes.subsystem.studyprogram.OptionalCourseSubsystemUtil;
import cu.edu.mes.subsystem.studyprogram.StudyProgramSubsystem;
import cu.edu.mes.subsystem.studyprogram.StudyProgramSubsystemUtil;
import cu.edu.mes.subsystem.subject.SubjectSubsystem;
import cu.edu.mes.subsystem.subject.SubjectSubsystemUtil;
import cu.edu.mes.subsystem.university.UniversitySubsystem;
import cu.edu.mes.subsystem.university.UniversitySubsystemUtil;

public class Client {
	
	private static Properties properties = null;

	private static AcademicLevelCatalog academicLevelCatalog = null;

	private static AcademicSituationCatalog academicSituationCatalog = null;

	private static AssignedSubjectCatalog assignedSubjectCatalog = null;

	private static AuthenticationService authenticationService = null;

	private static BasedStudyProgramCatalog basedStudyProgramCatalog = null;

	private static CareerCatalog careerCatalog = null;

	private static CountryCatalog countryCatalog = null;

	private static CourseCatalog courseCatalog = null;

	private static CourseClosure courseClosure = null;

	private static CourseTypeCatalog courseTypeCatalog = null;

	private static CustomStudyProgramCatalog customStudyProgramCatalog = null;

	private static DisciplineCatalog disciplineCatalog = null;

	private static DropDomainData dropDomainData = null;

	private static DropMotiveCatalog dropMotiveCatalog = null;

	private static DropStudent dropStudent = null;

	private static EntrySourceCatalog entrySourceCatalog = null;

	private static EvaluationDomainData evaluationDomainData = null;

	private static EvaluationReasonCatalog evaluationReasonCatalog = null;

	private static EvaluationTypeCatalog evaluationTypeCatalog = null;

	private static EvaluationValueCatalog evaluationValueCatalog = null;

	private static ExaminationTypeCatalog examinationTypeCatalog = null;

	private static FacultyCatalog facultyCatalog = null;

	private static GeneralReportCatalog generalReportCatalog = null;

	private static GroupCatalog groupCatalog = null;

	private static GroupTypeCatalog groupTypeCatalog = null;

	private static HandicapCatalog handicapCatalog = null;

	private static LicenceMotiveCatalog licenceMotiveCatalog = null;

	private static MaritalStatusCatalog maritalStatusCatalog = null;

	private static MatriculatedSubjectCatalog matriculatedSubjectCatalog = null;

	private static MilitarGradeCatalog militarGradeCatalog = null;

	private static MilitarSpecialtyCatalog militarSpecialtyCatalog = null;

	private static MilitarTypeCatalog militarTypeCatalog = null;

	private static NationalCareerCatalog nationalCareerCatalog = null;

	private static OcupationCatalog ocupationCatalog = null;

	private static ONGCatalog ongCatalog = null;

	private static OrganismCatalog organismCatalog = null;

	private static OrphanCatalog orphanCatalog = null;

	//private static PeriodTypeCatalog periodTypeCatalog = null;

	private static PoliticOrgCatalog politicOrgCatalog = null;

	private static PopularOrgCatalog popularOrgCatalog = null;

	private static ProvinceCatalog provinceCatalog = null;

	private static ScholasticOriginCatalog scholasticOriginCatalog = null;

	private static SciencEspecialtyCatalog sciencEspecialtyCatalog = null;

	private static ServiceElectiveSubject serviceElectiveSubject = null;

	private static SexCatalog sexCatalog = null;

	private static SkinColorCatalog skinColorCatalog = null;

	private static StudentEvaluation studentEvaluation = null;

	private static StudentManage studentManage = null;

	private static StudentMatriculateSess studentMatriculateSess = null;

	private static StudentReportCatalog studentReportCatalog = null;
	
	private static AssistantStudentReportService assistantStudentReportService = null;
	
	private static DateCatalog dateCatalog = null;

	private static StudentsControl studentsControl = null;

	private static StudentStatusCatalog studentStatusCatalog = null;

	private static StudentStatusManager studentStatusManager = null;

	private static StudentTypeCatalog studentTypeCatalog = null;

	private static StudyProgramCatalog studyProgramCatalog = null;

	private static StudyProgramServices studyProgramServices = null;


	private static StudyRegimenCatalog studyRegimenCatalog = null;



	private static SubjectSituationCatalog subjectSituationCatalog = null;

	private static SubjectTypeCatalog subjectTypeCatalog = null;

	private static SyndicateCatalog syndicateCatalog = null;

	private static TownCatalog townCatalog = null;

	private static TownUniversityCatalog townUniversityCatalog = null;

	private static UniversityCatalog universityCatalog = null;
	
	private static BonusCatalog bonusCatalog = null;
	
	private static AwardCatalog awardCatalog = null;	
	
   private static SubjectSubsystem subjectSubsystem = null;	
	/*
	 * Lo nuevo de asignaturas matriculadas	
	 */
	private static StudentSubsystem studentSubsystem = null;
	
	private static UniversitySubsystem universitySubsystem = null;
	
	private static StudyProgramSubsystem studyProgramSubsystem = null;
	
	private static OptionalCourseSubsystem optionalCourseSubsystem = null;
	
	private static AdjustmentSubjectSubsystem adjustmentSubsystem = null;	
	
	private static UserService userService = null;
	
	private static ProfessorService professorService=null;
	
	private static ExaminationActaService examinationActaService=null;
	
	private static PlanningService plannigService = null;
	
	private static ProfessorReportsService professorReportsService = null;
	
    private static SecurityLevelService securityLevelService = null;
	
	private static SecurityRoleService securityRoleService = null;
	
	
	public static ExaminationActaService getExaminationActaService(){
		//if(examinationActaService == null){
		try {
			
				examinationActaService = ExaminationActaServiceUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())).create();
			}  catch (CreateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		return examinationActaService;
	}
	
	
	
	
	
	public static ProfessorService getProfessorService(){
		//if(professorService == null){
		try {
			
				professorService = ProfessorServiceUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())).create();
			}  catch (CreateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		return professorService;
	}
	
	
	
	public static UserService getUserService(){
		//if(userService == null){
		try {
			
				userService = UserServiceUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())).create();
			}  catch (CreateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		return userService;
	}
	
	public static UserService getUserService(String username, String password){
		//if(userService == null){
		try {
			
				userService = UserServiceUtil.getHome(ApplicationSetting.loadEnvironmet(username, password)).create();
			}  catch (CreateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		return userService;
	}

	public static AcademicLevelCatalog getAcademicLevelCatalog() {
		//if (academicLevelCatalog == null) {
		try {
			
				academicLevelCatalog = AcademicLevelCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return academicLevelCatalog;
	}

	public static AcademicSituationCatalog getAcademicSituationCatalog() {
		//if (academicSituationCatalog == null) {
		try {
			
				academicSituationCatalog = AcademicSituationCatalogUtil
						.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return academicSituationCatalog;
	}

	public static AssignedSubjectCatalog getAssignedSubjectCatalog() {
		//if (assignedSubjectCatalog == null) {
		try {
			
				assignedSubjectCatalog = AssignedSubjectCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return assignedSubjectCatalog;
	}

	public static AuthenticationService getAuthenticationService(String username, String password) {
		//LoginContext loginContext = loginContextJboss();
			//if (authenticationService == null) {
				try {
					authenticationService = AuthenticationServiceUtil.getHome(ApplicationSetting.loadEnvironmet(username, password))
							.create();
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (CreateException e) {
					e.printStackTrace();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			//}
		//logoutContextJboss(loginContext);
		
		return authenticationService;
	}

	/*public static BasedStudyProgramCatalog getBasedStudyProgramCatalog() {
		if (basedStudyProgramCatalog == null) {
			try {
				basedStudyProgramCatalog = BasedStudyProgramCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return basedStudyProgramCatalog;
	}*/

	public static CareerCatalog getCareerCatalog() {
		//if (careerCatalog == null) {
		try {
			
				careerCatalog = CareerCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return careerCatalog;
	}

	public static CountryCatalog getCountryCatalog() {
		//if (countryCatalog == null) {
		try {
			
				countryCatalog = CountryCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return countryCatalog;
	}

	public static CourseCatalog getCourseCatalog() {
		//if (courseCatalog == null) {
		try {
			
				courseCatalog = CourseCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return courseCatalog;
	}

	public static CourseClosure getCourseClosure() {
		//if (courseClosure == null) {
		try {
			
				courseClosure = CourseClosureUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return courseClosure;
	}

	public static CourseTypeCatalog getCourseTypeCatalog() {
		//if (courseTypeCatalog == null) {
		try {
			
				courseTypeCatalog = CourseTypeCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return courseTypeCatalog;
	}

	public static CustomStudyProgramCatalog getCustomStudyProgramCatalog() {
		//if (customStudyProgramCatalog == null) {
		try {
			
				customStudyProgramCatalog = CustomStudyProgramCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return customStudyProgramCatalog;
	}

	public static DisciplineCatalog getDisciplineCatalog() {
		//if (disciplineCatalog == null) {

		try {
			
				disciplineCatalog = DisciplineCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return disciplineCatalog;

	}

	public static DropDomainData getDropDomainData() {
		//if (dropDomainData == null) {
		try {
			
				dropDomainData = DropDomainDataUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return dropDomainData;
	}

	public static DropMotiveCatalog getDropMotiveCatalog() {
		//if (dropMotiveCatalog == null) {
		try {
			
				dropMotiveCatalog = DropMotiveCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return dropMotiveCatalog;
	}

	public static DropStudent getDropStudent() {
		//if (dropStudent == null) {
		try {
			
				dropStudent = DropStudentUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return dropStudent;
	}

	public static EntrySourceCatalog getEntrySourceCatalog() {
		//if (entrySourceCatalog == null) {
		try {
			
				entrySourceCatalog = EntrySourceCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return entrySourceCatalog;
	}

	public static EvaluationDomainData getEvaluationDomainData() {
		//if (evaluationDomainData == null) {
		try {
			
				evaluationDomainData = EvaluationDomainDataUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return evaluationDomainData;
	}

	public static EvaluationReasonCatalog getEvaluationReasonCatalog() {
		//if (evaluationReasonCatalog == null) {
		try {
			
				evaluationReasonCatalog = EvaluationReasonCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return evaluationReasonCatalog;

	}

	public static EvaluationTypeCatalog getEvaluationTypeCatalog() {
		//if (evaluationTypeCatalog == null) {

		try {
			
				evaluationTypeCatalog = EvaluationTypeCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return evaluationTypeCatalog;

	}

	public static EvaluationValueCatalog getEvaluationValueCatalog() {
		//if (evaluationValueCatalog == null) {

		try {
			
				evaluationValueCatalog = EvaluationValueCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}

		return evaluationValueCatalog;
	}

	public static ExaminationTypeCatalog getExaminationTypeCatalog() {
		//if (examinationTypeCatalog == null) {

		try {
			
				examinationTypeCatalog = ExaminationTypeCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}

		return examinationTypeCatalog;
	}

	public static FacultyCatalog getFacultyCatalog() {
		//if (facultyCatalog == null) {
		try {
			
				facultyCatalog = FacultyCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return facultyCatalog;
	}

	public static GeneralReportCatalog getGeneralReportCatalog() {
		//if (generalReportCatalog == null) {
		try {
			
				generalReportCatalog = GeneralReportCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return generalReportCatalog;
	}

	public static GroupCatalog getGroupCatalog() {
		//if (groupCatalog == null) {
		try {
			
				groupCatalog = GroupCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return groupCatalog;
	}

	public static GroupTypeCatalog getGroupTypeCatalog() {
		//if (groupTypeCatalog == null) {
		try {
			
				groupTypeCatalog = GroupTypeCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return groupTypeCatalog;
	}

	public static HandicapCatalog getHandicapCatalog() {
		//if (handicapCatalog == null) {
		try {
			
				handicapCatalog = HandicapCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return handicapCatalog;
	}

	public static LicenceMotiveCatalog getLicenceMotiveCatalog() {
		//if (licenceMotiveCatalog == null) {
		try {
			
				licenceMotiveCatalog = LicenceMotiveCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return licenceMotiveCatalog;
	}
	
	public static MaritalStatusCatalog getMaritalStatusCatalog() {
		//if (maritalStatusCatalog == null) {
		try {
			
				maritalStatusCatalog = MaritalStatusCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return maritalStatusCatalog;
	}

	public static MatriculatedSubjectCatalog getMatriculatedSubjectCatalog() {
		//if (matriculatedSubjectCatalog == null) {
		try {
			
				matriculatedSubjectCatalog = MatriculatedSubjectCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return matriculatedSubjectCatalog;
	}
	
	public static MilitarGradeCatalog getMilitarGradeCatalog() {
		//if (militarGradeCatalog == null) {
		try {
			
				militarGradeCatalog = MilitarGradeCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return militarGradeCatalog;
	}

	public static MilitarSpecialtyCatalog getMilitarSpecialtyCatalog() {
		//if (militarSpecialtyCatalog == null) {
		try {
			
				militarSpecialtyCatalog = MilitarSpecialtyCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return militarSpecialtyCatalog;
	}
	
	public static MilitarTypeCatalog getMilitarTypeCatalog() {
		//if (militarTypeCatalog == null) {
		try {
			
				militarTypeCatalog = MilitarTypeCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return militarTypeCatalog;
	}

	public static NationalCareerCatalog getNationalCareerCatalog() {
		//if (nationalCareerCatalog == null) {
		try {
			
				nationalCareerCatalog = NationalCareerCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return nationalCareerCatalog;
	}
	
	public static OcupationCatalog getOcupationCatalog() {
		//if (ocupationCatalog == null) {
		try {
			
				ocupationCatalog = OcupationCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return ocupationCatalog;
	}

	public static ONGCatalog getONGCatalog() {
		//if (ongCatalog == null) {
		try {
			
				ongCatalog = ONGCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return ongCatalog;
	}
	
	public static OrganismCatalog getOrganismCatalog() {
		//if (organismCatalog == null) {
		try {
			
				organismCatalog = OrganismCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return organismCatalog;
	}

	public static OrphanCatalog getOrphanCatalog() {
		//if (orphanCatalog == null) {
		try {
			
				orphanCatalog = OrphanCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return orphanCatalog;
	}
	
	/*public static PeriodTypeCatalog getPeriodTypeCatalog() {
		if (periodTypeCatalog == null) {
			try {
				periodTypeCatalog = PeriodTypeCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return periodTypeCatalog;
	}*/

	public static PoliticOrgCatalog getPoliticOrgCatalog() {
		//if (politicOrgCatalog == null) {
			try {
				
				politicOrgCatalog = PoliticOrgCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return politicOrgCatalog;
	}
	
	public static PopularOrgCatalog getPopularOrgCatalog() {
		//if (popularOrgCatalog == null) {
			try {
				
				popularOrgCatalog = PopularOrgCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return popularOrgCatalog;
	}

	public static ProvinceCatalog getProvinceCatalog() {
		//if (provinceCatalog == null) {
			try {
				
				provinceCatalog = ProvinceCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return provinceCatalog;
	}
	
	public static ScholasticOriginCatalog getScholasticOriginCatalog() {
		//if (scholasticOriginCatalog == null) {
			try {
				
				scholasticOriginCatalog = ScholasticOriginCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return scholasticOriginCatalog;
	}

	public static SciencEspecialtyCatalog getSciencEspecialtyCatalog() {
		//if (sciencEspecialtyCatalog == null) {
			try {
				
				sciencEspecialtyCatalog = SciencEspecialtyCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return sciencEspecialtyCatalog;
	}
	
	public static ServiceElectiveSubject getServiceElectiveSubject() {
		//if (serviceElectiveSubject == null) {
			try {
				
				serviceElectiveSubject = ServiceElectiveSubjectUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return serviceElectiveSubject;
	}

	public static SexCatalog getSexCatalog() {
		//if (sexCatalog == null) {
			try {
				
				sexCatalog = SexCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return sexCatalog;
	}
	
	public static SkinColorCatalog getSkinColorCatalog() {
		//if (skinColorCatalog == null) {

		try {
			
				skinColorCatalog = SkinColorCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return skinColorCatalog;
	}

	public static StudentEvaluation getStudentEvaluation() {
		//if (studentEvaluation == null) {
		try {
			
				studentEvaluation = StudentEvaluationUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studentEvaluation;
	}
	
	public static StudentManage getStudentManage() {
		//if (studentManage == null) {
		try {
			
				studentManage = StudentManageUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studentManage;
	}

	public static StudentMatriculateSess getStudentMatriculateSess() {
		//if (studentMatriculateSess == null) {

		try {
			
				studentMatriculateSess = StudentMatriculateSessUtil.
					getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studentMatriculateSess;

	}
	
	public static StudentReportCatalog getStudentReportCatalog() {
		//if (studentReportCatalog == null) {
		try {
			
				studentReportCatalog = StudentReportCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studentReportCatalog;
	}

	public static AssistantStudentReportService getAssistantStudentReportService() {
		//if (assistantStudentReportService == null) {
		try {
			
				assistantStudentReportService = AssistantStudentReportServiceUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return assistantStudentReportService;
	}
	
	public static DateCatalog getDateCatalog() {
		//if (dateCatalog == null) {
		try {
			
				dateCatalog = DateCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return dateCatalog;
	}
	
	
	public static StudentsControl getStudentsControl() {
		//if (studentsControl == null) {
		try {
			
				studentsControl = StudentsControlUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studentsControl;
	}
	
	public static StudentStatusCatalog getStudentStatusCatalog() {
		//if (studentStatusCatalog == null) {
		try {
			
				studentStatusCatalog = StudentStatusCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studentStatusCatalog;
	}

	public static StudentStatusManager getStudentStatusManager() {
		//if (studentStatusManager == null) {
		try {
			
				studentStatusManager = StudentStatusManagerUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studentStatusManager;
	}
	
	public static StudentTypeCatalog getStudentTypeCatalog() {
		//if (studentTypeCatalog == null) {
		try {
			
				studentTypeCatalog = StudentTypeCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studentTypeCatalog;
	}

	public static StudyProgramCatalog getStudyProgramCatalog() {
		//if (studyProgramCatalog == null) {
		try {
			
				studyProgramCatalog = StudyProgramCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studyProgramCatalog;
	}
	
	public static StudyProgramServices getStudyProgramServices() {
		//if (studyProgramServices == null) {
		try {
			
				studyProgramServices = StudyProgramServicesUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studyProgramServices;
	}


	/*public static StudyProgramTypeCatalog getStudyProgramTypeCatalog() {
		if (studyProgramTypeCatalog == null) {
			try {
				studyProgramTypeCatalog = StudyProgramTypeCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return studyProgramTypeCatalog;
	}*/

//	public static StudyProgramTypeCatalog getStudyProgramTypeCatalog() {
//		if (studyProgramTypeCatalog == null) {
//			try {
//				studyProgramTypeCatalog = StudyProgramTypeCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
//						).create();
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			} catch (CreateException e) {
//				e.printStackTrace();
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}
//		return studyProgramTypeCatalog;
//	}

	
	public static StudyRegimenCatalog getStudyRegimenCatalog() {
		//if (studyRegimenCatalog == null) {
		try {
			
				studyRegimenCatalog = StudyRegimenCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studyRegimenCatalog;
	}


	
	public static SubjectSituationCatalog getSubjectSituationCatalog() {
		//if (subjectSituationCatalog == null) {
		try {
			
				subjectSituationCatalog = SubjectSituationCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return subjectSituationCatalog;
	}

	public static SubjectTypeCatalog getSubjectTypeCatalog() {
		//if (subjectTypeCatalog == null) {
		try {
			
				subjectTypeCatalog = SubjectTypeCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain()))
						.create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return subjectTypeCatalog;
	}
	
	public static SyndicateCatalog getSyndicateCatalog() {
		//if (syndicateCatalog == null) {
		try {
			
				syndicateCatalog = SyndicateCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return syndicateCatalog;
	}

	public static TownCatalog getTownCatalog() {
		//if (townCatalog == null) {
		try {
			
				townCatalog = TownCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return townCatalog;
	}
	
	public static TownUniversityCatalog getTownUniversityCatalog() {
		//if (townUniversityCatalog == null) {
		try {
			
				townUniversityCatalog = TownUniversityCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return townUniversityCatalog;
	}

	public static UniversityCatalog getUniversityCatalog() {
		//if (universityCatalog == null) {

		try {
			
				universityCatalog = UniversityCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
						).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return universityCatalog;
	}
	
	public static BonusCatalog getBonusCatalog() {
		//if (bonusCatalog == null) {
		try {
			
				bonusCatalog = BonusCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
				).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return bonusCatalog;
	}
	
	public static PlanningService getPlannigService(){
		//if (plannigService == null) {
		try {
			
				plannigService = PlanningServiceUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
				).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return plannigService;
	}
	
	
	public static AwardCatalog getAwardCatalog() {
		//if (awardCatalog == null) {
		try {
			
				awardCatalog = AwardCatalogUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
				).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return awardCatalog;
	}
	
	public static AdjustmentSubjectSubsystem getAdjustmentSubjectSubsystem() {
		//if (adjustmentSubsystem == null) {
		try {
			
				adjustmentSubsystem = AdjustmentSubjectSubsystemUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
				).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return adjustmentSubsystem;
	}
	
	public static StudentSubsystem getStudentSubsystem() {
		//if (studentSubsystem == null) {
		try {
			
				studentSubsystem = StudentSubsystemUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
				).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studentSubsystem;
	}
	
	
	
	
	public static UniversitySubsystem getUniversitySubsystem() {
		//LoginContext login = loginContextJboss();
		//if (universitySubsystem == null) {
		try {
			
				universitySubsystem = UniversitySubsystemUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
				).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		//logoutContextJboss(login);
		return universitySubsystem;
	}
	

	
	public static StudyProgramSubsystem getStudyProgramSubsystem() {
		//if (studyProgramSubsystem == null) {
		try {
			
				studyProgramSubsystem = StudyProgramSubsystemUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
				).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return studyProgramSubsystem;
	}
	
	public static SubjectSubsystem getSubjectSubsystem() {
		//if (subjectSubsystem == null) {
		try {
			
				subjectSubsystem = SubjectSubsystemUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
				).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return subjectSubsystem;
	}
	
	public static OptionalCourseSubsystem getOptionalCourseSubsystem() {
		//if (optionalCourseSubsystem == null) {
		try {
			
				optionalCourseSubsystem = OptionalCourseSubsystemUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
				).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return optionalCourseSubsystem;
	}




	public static ProfessorReportsService getProfessorReportsService() {
		//if (professorReportsService == null) {
		try {
			
				professorReportsService = ProfessorReportsServiceUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())
				).create();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//}
		return professorReportsService;
	}
	
	public static SecurityRoleService getSecurityRoleService(){
		//if(securityRoleService == null){
		try {
			
				securityRoleService = SecurityRoleServiceUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())).create();
			}  catch (CreateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		return securityRoleService;
	}
	
	public static SecurityLevelService getSecurityLevelService(){
		//if(securityLevelService == null){
		try {
			
				securityLevelService = SecurityLevelServiceUtil.getHome(ApplicationSetting.loadEnvironmet(CurrentUserUtils.getUsername(), CurrentUserUtils.getPasswordPlain())).create();
			}  catch (CreateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		return securityLevelService;
	}




	
	
	
	
	
}