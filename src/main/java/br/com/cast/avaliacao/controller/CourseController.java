package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.exception.ResourceNotFoundException;
import br.com.cast.avaliacao.model.CourseModel;
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
    public List<CourseModel> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/get-all/from-category/{categoryId}")
    public List<CourseModel> getAllCoursesByCategoryId(@PathVariable Long categoryId) {
        return courseService.getAllCoursesByCategoryId(categoryId);
    }

    @GetMapping("/get-by-id/{courseId}")
    public Optional<CourseModel> getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping("/create")
    public CourseModel addCourse(@Valid @RequestBody CourseModel courseModel) {
        return courseService.addCourse(courseModel);
    }

    @PutMapping("/update/{courseId}")
    public CourseModel updateCourseDescription(
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
