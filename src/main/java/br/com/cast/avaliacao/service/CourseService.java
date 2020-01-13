package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.exception.InvalidDateException;
import br.com.cast.avaliacao.exception.ResourceNotFoundException;
import br.com.cast.avaliacao.model.entity.Course;
import br.com.cast.avaliacao.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAllByOrderByIdAsc();
    }

    public List<Course> getAllCoursesByCategoryId(Long categoryId) {
        return courseRepository.findAllByCategoryId(categoryId);
    }

    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public Course addCourse(Course course) {
        if (isValidDate(course)) {
            return courseRepository.save(course);
        } else {
            throw new InvalidDateException("Existe(m) curso(s) planejado(s) dentro desse período.");
        }
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

    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    public Course updateCourseDescription(Long courseId, String description) {
        return courseRepository
                .findById(courseId)
                .map(course -> {
                    course.setDescription(description);
                    return courseRepository.save(course);
                }).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado curso com o id " + courseId));
    }

    public ResponseEntity<?> deleteQuestion(Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    deleteCourse(course);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + courseId));
    }
}
