package cu.edu.mes.sigenu.training.api.controller;


import cu.edu.mes.sigenu.training.core.dto.QuestionCarrerDto;
import cu.edu.mes.sigenu.training.core.model.QuestionCarrer;
import cu.edu.mes.sigenu.training.core.service.QuestionCarrerService;
import cu.edu.mes.sigenu.training.core.service.SigenuService;
import cu.edu.mes.sigenu.training.core.utils.ApiResponse;
import cu.edu.mes.vo.CareerVO;
import cu.edu.mes.vo.NationalCareerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Question Carrer endpoint controller")
@RequestMapping(value = "/question-carrer",produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionCarrerController {
    @Autowired
    private QuestionCarrerService questionCarrerService;

    @Autowired
    private SigenuService sigenuService;

    @GetMapping("")
    @ApiOperation(value = "Get a List with all question carrer")
    public List<QuestionCarrerDto> list(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<QuestionCarrer,QuestionCarrerDto> propertyMapper = modelMapper.createTypeMap(QuestionCarrer.class,QuestionCarrerDto.class);
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionId().getId(),QuestionCarrerDto::setQuestionId));

        return questionCarrerService.listAll().stream()
                                   .map(item -> modelMapper.map(item,QuestionCarrerDto.class))
                                   .collect(Collectors.toList());

    }

    @GetMapping("/career")
    @ApiOperation(value = "Get a List with all career")
    public List<NationalCareerVO> listCareer(){
        List<CareerVO> careerVOList= sigenuService.getCareersSigenu();
        List<NationalCareerVO> nationalCareers = new ArrayList<>();

        for (CareerVO careerVO:careerVOList) {

            if(!nationalCareers.stream().anyMatch(n -> n.getIdNationalCareer().equals(careerVO.getNationalCareerVO().getIdNationalCareer()))){
                nationalCareers.add(careerVO.getNationalCareerVO());
            }
        }

        return nationalCareers;

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get question carrer by id")
    public QuestionCarrerDto get(@PathVariable Integer id){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<QuestionCarrer,QuestionCarrerDto> propertyMapper = modelMapper.createTypeMap(QuestionCarrer.class,QuestionCarrerDto.class);
        propertyMapper.addMappings( mapper -> mapper.map(src -> src.getQuestionId().getId(),QuestionCarrerDto::setQuestionId));
        QuestionCarrer item = questionCarrerService.findById(id);
        return modelMapper.map(item, QuestionCarrerDto.class);

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create question carrer")
    public ResponseEntity<ApiResponse> save(@RequestBody QuestionCarrerDto item){
        try{
            ModelMapper modelMapper = new ModelMapper();
            QuestionCarrer questionCarrer = modelMapper.map(item,QuestionCarrer.class);
            questionCarrerService.save(questionCarrer);
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse(false,"Error: Question carrer hasn't created"));
        }
        return ResponseEntity.ok(new ApiResponse(true,"Question carrer created successfully"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("")
    @ApiOperation(value = "Update question carrer registered")
    public ResponseEntity<ApiResponse> update(@ApiParam(value = "Values for updating", name = "Body") @RequestBody QuestionCarrerDto item) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            QuestionCarrer groupQuestion = modelMapper.map(item, QuestionCarrer.class);
            questionCarrerService.update(groupQuestion);
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Error: question carrer hasn't been updated@"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "question carrer updated successfully@"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete question carrer registered")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        try {
            questionCarrerService.delete(id);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException)
                return ResponseEntity.ok(new ApiResponse(false, "question carrer CAN'T be deleted because has been reference by another entity"));
            return ResponseEntity.ok(new ApiResponse(false, "question carrer CAN'T be deleted"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "question carrer deleted successfully@"));
    }

}
