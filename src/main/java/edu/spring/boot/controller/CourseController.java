package edu.spring.boot.controller;

import edu.spring.boot.dto.Course;
import edu.spring.boot.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping
    public ResponseEntity<?> addNewCourse(@RequestBody Course course){
        Course newCourse = courseService.onBoardNewCourse(course);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAllCourses(){
        return new ResponseEntity<>(courseService.viewAllCourses(),HttpStatus.OK);
    }

    @GetMapping("/search/path/{courseId}")
    public ResponseEntity<?> findCourseUsingPathVariable(@PathVariable Integer courseId){
        return new ResponseEntity<>(courseService.findCourseById(courseId),HttpStatus.OK);
    }

    @GetMapping("/search/param")
    public ResponseEntity<?> findCourseUsingRequestParam(@RequestParam Integer courseId){
        return new ResponseEntity<>(courseService.findCourseById(courseId),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("Course with Id:"+courseId+" delete successfully",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer courseId, @RequestBody Course course){
        return new ResponseEntity<>(courseService.updateCourse(courseId,course),HttpStatus.OK);
    }
}
