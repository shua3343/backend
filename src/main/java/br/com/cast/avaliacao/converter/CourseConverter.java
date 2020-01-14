package br.com.cast.avaliacao.converter;

import br.com.cast.avaliacao.model.entity.Category;
import br.com.cast.avaliacao.model.entity.Course;
import br.com.cast.avaliacao.model.request.CourseRequest;
import br.com.cast.avaliacao.model.response.CourseResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseConverter {

    public List<CourseResponse> getCourseResponseList(List<Course> courses) {
        return courses.stream()
                .map(this::getCourseResponse)
                .collect(Collectors.toList());
    }

    public Course getCourse(CourseRequest courseRequest){
        Category category = new Category();
        category.setId(courseRequest.getCategoryId());

        Course course = new Course();
        course.setId(courseRequest.getId());
        course.setDescription(courseRequest.getDescription());
        course.setStartDate(courseRequest.getStartDate());
        course.setEndDate(courseRequest.getEndDate());
        course.setStudents(courseRequest.getStudents());
        course.setCategory(category);

        return course;
    }

    public CourseResponse getCourseResponse(Course course) {
        CourseResponse courseResponse = new CourseResponse();

        courseResponse.setId(course.getId());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setStartDate(course.getStartDate());
        courseResponse.setEndDate(course.getEndDate());
        courseResponse.setStudents(course.getStudents());
        courseResponse.setCategoryId(course.getCategory().getId());

        return courseResponse;
    }
}
