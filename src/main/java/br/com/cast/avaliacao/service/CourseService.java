package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.exception.InvalidDateException;
import br.com.cast.avaliacao.exception.ResourceNotFoundException;
import br.com.cast.avaliacao.model.CourseModel;
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

    public List<CourseModel> getAllCourses() {
        return courseRepository.findAllByOrderByIdAsc();
    }

    public List<CourseModel> getAllCoursesByCategoryId(Long categoryId) {
        return courseRepository.findAllByCategoryId(categoryId);
    }

    public Optional<CourseModel> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public CourseModel addCourse(CourseModel courseModel) {
        if (isValidDate(courseModel)) {
            return courseRepository.save(courseModel);
        } else {
            throw new InvalidDateException("Existe(m) curso(s) planejado(s) dentro desse período.");
        }
    }

    private boolean isValidDate(CourseModel courseModel) {
        if (courseModel.getStartDate().isBefore(LocalDate.now())) {
            throw new InvalidDateException("A curso não pode começar no passado.");
        } else {
            return periodHaveCourse(courseModel);
        }
    }

    private boolean periodHaveCourse(CourseModel courseModel) {
        return !courseRepository.findOneByStartDateGreaterThanAndEndDateLessThan(
                courseModel.getStartDate(), courseModel.getEndDate()
        ).isPresent() || !courseRepository.findOneByStartDateLessThanAndEndDateGreaterThan(
                courseModel.getStartDate(), courseModel.getEndDate()
        ).isPresent();
    }

    public void deleteCourse(CourseModel course) {
        courseRepository.delete(course);
    }

    public CourseModel updateCourseDescription(Long courseId, String description) {
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
