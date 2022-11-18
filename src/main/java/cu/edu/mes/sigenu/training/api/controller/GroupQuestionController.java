package cu.edu.mes.sigenu.training.api.controller;



import cu.edu.mes.sigenu.training.core.dto.GroupQuestionDto;
import cu.edu.mes.sigenu.training.core.model.GroupQuestion;
import cu.edu.mes.sigenu.training.core.service.GroupQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import cu.edu.mes.sigenu.training.core.utils.ApiResponse;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Api(tags = "Group Question enpoint controller")
@RequestMapping(value = "/group-question",produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupQuestionController {

    @Autowired
    private GroupQuestionService groupQuestionService;

    @GetMapping("")
    @ApiOperation(value = "Get a List with all group question")
    public List<GroupQuestionDto> list(){
        ModelMapper modelMapper = new ModelMapper();
        return groupQuestionService.listAll().stream()
                                   .map(item -> modelMapper.map(item,GroupQuestionDto.class))
                                   .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Group question by id")
    public GroupQuestionDto get(@PathVariable Integer id){
            ModelMapper modelMapper = new ModelMapper();
            GroupQuestion item = groupQuestionService.findById(id);
            return modelMapper.map(item, GroupQuestionDto.class);

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create group question")
    public ResponseEntity<ApiResponse> save(@RequestBody GroupQuestionDto item){
        try{
            ModelMapper modelMapper = new ModelMapper();
            GroupQuestion groupQuestion = modelMapper.map(item,GroupQuestion.class);
            groupQuestionService.save(groupQuestion);
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: Group Question hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Group Question created successfully"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("")
    @ApiOperation(value = "Update group question registered")
    public ResponseEntity<ApiResponse> update(@ApiParam(value = "Values for updating", name = "Body") @RequestBody GroupQuestionDto item) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            GroupQuestion groupQuestion = modelMapper.map(item, GroupQuestion.class);
            groupQuestionService.update(groupQuestion);
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Error: Group question hasn't been updated@"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Group question updated successfully@"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete group question registered")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        try {
            groupQuestionService.delete(id);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException)
                return ResponseEntity.ok(new ApiResponse(false, "Group question CAN'T be deleted because has been reference by another entity"));
            return ResponseEntity.ok(new ApiResponse(false, "Group question CAN'T be deleted"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Group question deleted successfully@"));
    }


}
