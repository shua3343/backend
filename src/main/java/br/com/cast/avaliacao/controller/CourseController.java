package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.exception.ResourceNotFoundException;
import br.com.cast.avaliacao.model.entity.Course;
import br.com.cast.avaliacao.model.request.CourseRequest;
import br.com.cast.avaliacao.model.response.CourseResponse;
import br.com.cast.avaliacao.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/get-all")
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/get-all/from-category/{categoryId}")
    public List<CourseResponse> getAllCoursesByCategoryId(@PathVariable Long categoryId) {
        return courseService.getAllCoursesByCategoryId(categoryId);
    }

    @GetMapping("/get-by-id/{courseId}")
    public CourseResponse getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping("/create")
    public CourseResponse addCourse(@Valid @RequestBody CourseRequest course) {
        return courseService.addCourse(course);
    }

    @PutMapping("/update/{courseId}")
    public CourseResponse updateCourse(
            @PathVariable Long courseId,
            @Valid @RequestBody CourseRequest courseRequest
    ) throws ResourceNotFoundException {
        return courseService.updateCourse(courseId, courseRequest);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long courseId) {
        return courseService.deleteCourse(courseId);
    }
}
