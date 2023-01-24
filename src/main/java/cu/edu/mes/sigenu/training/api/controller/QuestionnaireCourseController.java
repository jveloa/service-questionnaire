package cu.edu.mes.sigenu.training.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.mes.sigenu.training.core.dto.QuestionnaireCourseDto;
import cu.edu.mes.sigenu.training.core.model.QuestionnaireCourse;
import cu.edu.mes.sigenu.training.core.service.QuestionnaireCourseService;
import cu.edu.mes.sigenu.training.core.utils.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Questionnaire Course endpoint controller")
@RequestMapping(value = "/questionnaire-course",produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionnaireCourseController {
	@Autowired
	private QuestionnaireCourseService questionnaireCourseService;
	
	@GetMapping("")
    @ApiOperation(value = "Get a list with all questionnaire courses")
    public List<QuestionnaireCourseDto> list(){
        ModelMapper modelMapper = new ModelMapper();
        return questionnaireCourseService.listAll().stream()
                                         .map(item -> modelMapper.map(item,QuestionnaireCourseDto.class))
                                         .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get questionnaaire ccourse by id")
    public QuestionnaireCourseDto get(@PathVariable Integer id){
    	ModelMapper modelMapper = new ModelMapper();
        QuestionnaireCourse item = questionnaireCourseService.findById(id);
        return modelMapper.map(item, QuestionnaireCourseDto.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create questionnaire course")
    public ResponseEntity<ApiResponse> save(@RequestBody QuestionnaireCourseDto item){
        try{
            ModelMapper modelMapper = new ModelMapper();
            QuestionnaireCourse questionnaireCourse = modelMapper.map(item,QuestionnaireCourse.class);
            questionnaireCourseService.save(questionnaireCourse);
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: Questionnaire Course hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Questionnaire Course created successfully"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete questionnaire course registered")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        try {
            questionnaireCourseService.delete(id);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException)
                return ResponseEntity.ok(new ApiResponse(false,
                		"Error: Questionnaire Course can't be deleted because has been reference by another entity"));
            return ResponseEntity.ok(new ApiResponse(false, "Error: Questionnaire Course can't be deleted"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Questionnaire Course deleted successfully"));
    }
}
