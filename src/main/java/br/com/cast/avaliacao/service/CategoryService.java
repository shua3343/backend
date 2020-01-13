package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.exception.CategoryInUseException;
import br.com.cast.avaliacao.exception.ResourceNotFoundException;
import br.com.cast.avaliacao.model.entity.Category;
import br.com.cast.avaliacao.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Category> getAllCategories() {
        return categoryRepository.findAllByOrderByIdAsc();
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public Category updateCategoryDescription(Long categoryId, String description) {
        return categoryRepository.findById(categoryId)
                .map(category -> {
                    category.setDescription(description);
                    return categoryRepository.save(category);
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("Não foi encontrada categoria com o id " + categoryId)
                );
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        if (courseService.getAllCoursesByCategoryId(categoryId).isEmpty()) {
            return categoryRepository.findById(categoryId)
                    .map(category -> {
                        deleteCategory(category);
                        return ResponseEntity.ok().build();
                    }).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrada categoria com o id " + categoryId));
        } else {
            throw new CategoryInUseException("A categoria de id " + categoryId + " ainda tem cursos correlacionados");
        }
    }
}
