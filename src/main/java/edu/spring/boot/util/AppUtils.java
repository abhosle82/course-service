package edu.spring.boot.util;

import edu.spring.boot.dto.CourseRequestDTO;
import edu.spring.boot.dto.CourseResponseDTO;
import edu.spring.boot.entity.Course;

import java.util.UUID;

public class AppUtils {

    public static Course mapCourseRequestDTOToEntity(CourseRequestDTO courseRequestDTO){
        Course course = new Course();

        course.setName(courseRequestDTO.getName());
        course.setTrainerName(courseRequestDTO.getTrainerName());
        course.setDuration(courseRequestDTO.getDuration());
        course.setStartDate(courseRequestDTO.getStartDate());
        course.setCourseType(courseRequestDTO.getCourseType());
        course.setFees(courseRequestDTO.getFees());
        course.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        course.setDescription(courseRequestDTO.getDescription());
        return course;
    }

    public static CourseResponseDTO mapEntityToCourseResponseDTO(Course course){
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

        courseResponseDTO.setCourseId(course.getCourseId());
        courseResponseDTO.setName(course.getName());
        courseResponseDTO.setTrainerName(course.getTrainerName());
        courseResponseDTO.setDuration(course.getDuration());
        courseResponseDTO.setStartDate(course.getStartDate());
        courseResponseDTO.setCourseType(course.getCourseType());
        courseResponseDTO.setFees(course.getFees());
        courseResponseDTO.setCertificateAvailable(course.isCertificateAvailable());
        courseResponseDTO.setDescription(course.getDescription());
        courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
        return courseResponseDTO;
    }
}
