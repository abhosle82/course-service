package edu.spring.boot.service;

import edu.spring.boot.dto.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CourseService {

    private List<Course> courseDatabase = new ArrayList<>();

    public Course onBoardNewCourse(Course course){
        course.setCourseId(new Random().nextInt(300));
        courseDatabase.add(course);
        return course;
    }
    public List<Course> viewAllCourses(){
        return courseDatabase;
    }

    public Course findCourseById(Integer courseId){
        return courseDatabase.stream().filter(course-> course.getCourseId()==courseId).findFirst().orElse(null);
    }

    public void deleteCourse(Integer courseId){
        Course course = findCourseById(courseId);
        courseDatabase.remove(course);
    }
    public Course updateCourse(Integer courseId,Course course){
        Course existingCourse = findCourseById(courseId);
        courseDatabase.set(courseDatabase.indexOf(existingCourse),course);
        return course;
    }
}
