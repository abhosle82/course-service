package edu.spring.boot.service;

import edu.spring.boot.dao.CourseRepository;
import edu.spring.boot.dto.CourseRequestDTO;
import edu.spring.boot.dto.CourseResponseDTO;
import edu.spring.boot.entity.Course;
import edu.spring.boot.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    private List<Course> courseDatabase = new ArrayList<>();


    public CourseResponseDTO onBoardNewCourse(CourseRequestDTO courseRequestDTO){
        Course course = AppUtils.mapCourseRequestDTOToEntity(courseRequestDTO);
        courseRepository.save(course);
        CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToCourseResponseDTO(course);
        return courseResponseDTO;
    }
    public List<CourseResponseDTO> viewAllCourses(){
        Iterable<Course> courseEntries = courseRepository.findAll();
        return StreamSupport.stream(courseEntries.spliterator(),false)
                .map(AppUtils::mapEntityToCourseResponseDTO)
                .collect(Collectors.toList());
    }

    public CourseResponseDTO findCourseById(Integer courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("Course Id "+courseId+" not available"));
        return AppUtils.mapEntityToCourseResponseDTO(course);
    }

    public void deleteCourse(Integer courseId){
        courseRepository.deleteById(courseId);
    }
    public CourseResponseDTO updateCourse(Integer courseId,CourseRequestDTO courseRequestDTO){

        Course existingCourse = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("Course Id "+courseId+" not available"));
        existingCourse.setName(courseRequestDTO.getName());
        existingCourse.setTrainerName(courseRequestDTO.getTrainerName());
        existingCourse.setDuration(courseRequestDTO.getDuration());
        existingCourse.setStartDate(courseRequestDTO.getStartDate());
        existingCourse.setCourseType(courseRequestDTO.getCourseType());
        existingCourse.setFees(courseRequestDTO.getFees());
        existingCourse.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        existingCourse.setDescription(courseRequestDTO.getDescription());

        Course updatedEntity = courseRepository.save(existingCourse);
        return AppUtils.mapEntityToCourseResponseDTO(updatedEntity);
    }
}
