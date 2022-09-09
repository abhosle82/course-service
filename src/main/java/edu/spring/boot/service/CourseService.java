package edu.spring.boot.service;

import edu.spring.boot.dao.CourseRepository;
import edu.spring.boot.dto.CourseRequestDTO;
import edu.spring.boot.dto.CourseResponseDTO;
import edu.spring.boot.entity.Course;
import edu.spring.boot.exception.CourseServiceBusinessException;
import edu.spring.boot.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public CourseResponseDTO onBoardNewCourse(CourseRequestDTO courseRequestDTO){
        Course course = AppUtils.mapCourseRequestDTOToEntity(courseRequestDTO);
        CourseResponseDTO courseResponseDTO;
        try{
            courseRepository.save(course);
            courseResponseDTO = AppUtils.mapEntityToCourseResponseDTO(course);
        }catch (Exception exception){
            throw new CourseServiceBusinessException("onboardNewCourse service method failed..");
        }
        return courseResponseDTO;
    }
    public List<CourseResponseDTO> viewAllCourses(){
        Iterable<Course> courseEntries = courseRepository.findAll();
        try {
            return StreamSupport.stream(courseEntries.spliterator(), false)
                    .map(AppUtils::mapEntityToCourseResponseDTO)
                    .collect(Collectors.toList());
        }catch (Exception exception){
            throw new CourseServiceBusinessException("onboardNewCourse service method failed..");
        }
    }

    public CourseResponseDTO findCourseById(Integer courseId){
        try{
            Course course = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("Course Id "+courseId+" not available"));
            return AppUtils.mapEntityToCourseResponseDTO(course);
        }catch (Exception exception){
            throw new CourseServiceBusinessException("onboardNewCourse service method failed..");
        }
    }

    public void deleteCourse(Integer courseId){
        try{
            courseRepository.deleteById(courseId);
        }catch (Exception exception){
            throw new CourseServiceBusinessException("onboardNewCourse service method failed..");
        }
    }
    public CourseResponseDTO updateCourse(Integer courseId,CourseRequestDTO courseRequestDTO){
        try {
            Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course Id " + courseId + " not available"));
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
        }catch (Exception exception){
            throw new CourseServiceBusinessException("onboardNewCourse service method failed..");
        }
    }
}
