package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.exception.ResourceNotFoundException;
import br.com.cast.avaliacao.model.entity.Course;
import br.com.cast.avaliacao.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/get-all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/get-all/from-category/{categoryId}")
    public List<Course> getAllCoursesByCategoryId(@PathVariable Long categoryId) {
        return courseService.getAllCoursesByCategoryId(categoryId);
    }

    @GetMapping("/get-by-id/{courseId}")
    public Optional<Course> getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping("/create")
    public Course addCourse(@Valid @RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @PutMapping("/update/{courseId}")
    public Course updateCourseDescription(
            @PathVariable Long courseId,
            @Valid @RequestBody String description
    ) throws ResourceNotFoundException {
        return courseService.updateCourseDescription(courseId, description);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long courseId) {
        return courseService.deleteQuestion(courseId);
    }
}
