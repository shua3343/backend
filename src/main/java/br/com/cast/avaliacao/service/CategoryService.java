package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.converter.CategoryConverter;
import br.com.cast.avaliacao.exception.CategoryInUseException;
import br.com.cast.avaliacao.exception.ResourceNotFoundException;
import br.com.cast.avaliacao.model.entity.Category;
import br.com.cast.avaliacao.model.response.ApiResponse;
import br.com.cast.avaliacao.model.response.CategoryResponse;
import br.com.cast.avaliacao.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryConverter categoryConverter;

    public List<CategoryResponse> getAllCategories() {
        return categoryConverter.getCategoryResponseList(categoryRepository.findAllByOrderByIdAsc());
    }

    public CategoryResponse createCategory(Category category) {
        return categoryConverter.getCategoryResponse(categoryRepository.save(category));
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public CategoryResponse updateCategoryDescription(Long categoryId, String description) {
        return categoryRepository.findById(categoryId)
                .map(category -> {
                    category.setDescription(description);
                    return categoryConverter.getCategoryResponse(categoryRepository.save(category));
                })
                .orElseThrow(
                        () -> notFoundException(categoryId)
                );
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        if (courseService.getAllCoursesByCategoryId(categoryId).isEmpty()) {
            return categoryRepository.findById(categoryId)
                    .map(category -> {
                        deleteCategory(category);
                        return new ResponseEntity<>(new ApiResponse(true, "Category deleted successfully"), HttpStatus.OK);
                    }).orElseThrow(
                            () -> notFoundException(categoryId)
                    );
        } else {
            throw new CategoryInUseException("A categoria de id " + categoryId + " ainda tem cursos correlacionados");
        }
    }

    private ResourceNotFoundException notFoundException(Long id){
        return new ResourceNotFoundException("Não foi encontrada categoria com o id " + id);
    }
}
