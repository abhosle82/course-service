package edu.spring.boot.controller;

import edu.spring.boot.dto.CourseRequestDTO;
import edu.spring.boot.dto.CourseResponseDTO;
import edu.spring.boot.dto.ServiceResponse;
import edu.spring.boot.entity.Course;
import edu.spring.boot.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Provider;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

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

    @GetMapping
    public ServiceResponse<List<CourseResponseDTO>> findAllCourses(){
        return new ServiceResponse<>(HttpStatus.OK,courseService.viewAllCourses());
    }

    @GetMapping("/search/path/{courseId}")
    public ServiceResponse<CourseResponseDTO> findCourseUsingPathVariable(@PathVariable Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK,courseService.findCourseById(courseId));
    }

    @GetMapping("/search/param")
    public ServiceResponse<CourseResponseDTO> findCourseUsingRequestParam(@RequestParam Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK,courseService.findCourseById(courseId));
    }

    @DeleteMapping("/delete/{courseId}")
    public ServiceResponse<?> deleteCourse(@PathVariable Integer courseId){
        courseService.deleteCourse(courseId);
        return new ServiceResponse<>(HttpStatus.OK,"Course with Id:"+courseId+" delete successfully");
    }

    @PutMapping("/update/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable Integer courseId, @RequestBody CourseRequestDTO courseRequestDTO){
        return new ServiceResponse<>(HttpStatus.OK,courseService.updateCourse(courseId,courseRequestDTO));
    }
}
