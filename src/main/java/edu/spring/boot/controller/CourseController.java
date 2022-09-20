package edu.spring.boot.controller;

import edu.spring.boot.dto.CourseRequestDTO;
import edu.spring.boot.dto.CourseResponseDTO;
import edu.spring.boot.dto.ServiceResponse;
import edu.spring.boot.entity.Course;
import edu.spring.boot.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Provider;
import java.util.List;
/*
http://localhost:8080/v3/api-docs
http://localhost:8080/v3/api-docs.yaml
http://localhost:8080/swagger-ui/index.html
*/

@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "add a new course to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "course added successfully",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = CourseResponseDTO.class))
                    }),
            @ApiResponse(responseCode = "400",description = "validation error")
    })
    @PostMapping
    public ServiceResponse<CourseResponseDTO> addNewCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO){
        ServiceResponse<CourseResponseDTO> serviceResponse = new ServiceResponse<>();
        try {
            CourseResponseDTO newCourse = courseService.onBoardNewCourse(courseRequestDTO);
            serviceResponse.setResponse(newCourse);
            serviceResponse.setStatus(HttpStatus.CREATED);
        }catch(Exception ex){
            serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResponse;
    }

    @Operation(summary = "Fetch all course object")
    @GetMapping
    public ServiceResponse<List<CourseResponseDTO>> findAllCourses(){
        return new ServiceResponse<>(HttpStatus.OK,courseService.viewAllCourses());
    }

    @Operation(summary = "Find course by courseId using path variable")
    @GetMapping("/search/path/{courseId}")
    public ServiceResponse<CourseResponseDTO> findCourseUsingPathVariable(@PathVariable Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK,courseService.findCourseById(courseId));
    }

    @Operation(summary = "Find course by courseId using request param")
    @GetMapping("/search/param")
    public ServiceResponse<CourseResponseDTO> findCourseUsingRequestParam(@RequestParam Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK,courseService.findCourseById(courseId));
    }

    @Operation(summary = "Delete a course")
    @DeleteMapping("/delete/{courseId}")
    public ServiceResponse<?> deleteCourse(@PathVariable Integer courseId){
        courseService.deleteCourse(courseId);
        return new ServiceResponse<>(HttpStatus.OK,"Course with Id:"+courseId+" delete successfully");
    }

    @Operation(summary = "Update a course")
    @PutMapping("/update/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable Integer courseId, @RequestBody CourseRequestDTO courseRequestDTO){
        return new ServiceResponse<>(HttpStatus.OK,courseService.updateCourse(courseId,courseRequestDTO));
    }

}
