package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.converter.CourseConverter;
import br.com.cast.avaliacao.exception.InvalidDateException;
import br.com.cast.avaliacao.exception.ResourceNotFoundException;
import br.com.cast.avaliacao.model.entity.Course;
import br.com.cast.avaliacao.model.response.ApiResponse;
import br.com.cast.avaliacao.model.response.CourseResponse;
import br.com.cast.avaliacao.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseConverter courseConverter;

    public List<CourseResponse> getAllCourses() {
        return courseConverter.getCourseResponseList(courseRepository.findAllByOrderByIdAsc());
    }

    public List<CourseResponse> getAllCoursesByCategoryId(Long categoryId) {
        return courseConverter.getCourseResponseList(courseRepository.findAllByCategoryId(categoryId));
    }

    public CourseResponse getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> courseConverter.getCourseResponse(course))
                .orElseThrow(
                        () -> notFoundException(courseId)
                );
    }

    public CourseResponse addCourse(Course course) {
        if (isValidDate(course)) {
            return courseConverter.getCourseResponse(courseRepository.save(course));
        } else {
            throw new InvalidDateException("Existe(m) curso(s) planejado(s) dentro desse período.");
        }
    }

    public CourseResponse updateCourseDescription(Long courseId, String description) {
        return courseRepository
                .findById(courseId)
                .map(course -> {
                    course.setDescription(description);
                    return courseConverter.getCourseResponse(courseRepository.save(course));
                }).orElseThrow(
                        () -> notFoundException(courseId)
                );
    }

    public ResponseEntity<?> deleteCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    deleteCourse(course);
                    return new ResponseEntity<>(new ApiResponse(true, "Course deleted successfully"), HttpStatus.OK);
                }).orElseThrow(
                        () -> notFoundException(courseId)
                );
    }

    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    private boolean isValidDate(Course course) {
        if (course.getStartDate().isBefore(LocalDate.now())) {
            throw new InvalidDateException("A curso não pode começar no passado.");
        } else {
            return periodHaveCourse(course);
        }
    }

    private boolean periodHaveCourse(Course course) {
        return !courseRepository.findOneByStartDateGreaterThanAndEndDateLessThan(
                course.getStartDate(), course.getEndDate()
        ).isPresent() || !courseRepository.findOneByStartDateLessThanAndEndDateGreaterThan(
                course.getStartDate(), course.getEndDate()
        ).isPresent();
    }

    private ResourceNotFoundException notFoundException(Long id) {
        return new ResourceNotFoundException("Não foi encontrado curso com o id " + id);
    }
}
