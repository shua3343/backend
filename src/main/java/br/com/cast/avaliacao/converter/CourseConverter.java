package br.com.cast.avaliacao.converter;

import br.com.cast.avaliacao.model.entity.Category;
import br.com.cast.avaliacao.model.entity.Course;
import br.com.cast.avaliacao.model.request.CourseRequest;
import br.com.cast.avaliacao.model.response.CourseResponse;

public class CourseConverter {

    public Course getCourse(CourseRequest courseRequest){
        Category category = new Category();
        category.setId(courseRequest.getCategoryId());

        Course course = new Course();
        course.setDescription(courseRequest.getDescription());
        course.setStartDate(courseRequest.getStartDate());
        course.setEndDate(courseRequest.getEndDate());
        course.setStudents(courseRequest.getStudents());
        course.setCategory(category);

        return course;
    }

    public CourseResponse getCourseResponse(Course course) {
        CourseResponse courseResponse = new CourseResponse();

        courseResponse.setDescription(course.getDescription());
        courseResponse.setStartDate(course.getStartDate());
        courseResponse.setEndDate(course.getEndDate());
        courseResponse.setStudents(course.getStudents());
        courseResponse.setCategoryId(course.getCategory().getId());

        return courseResponse;
    }
}
