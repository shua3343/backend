package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.exception.CategoryInUseException;
import br.com.cast.avaliacao.exception.ResourceNotFoundException;
import br.com.cast.avaliacao.model.CategoryModel;
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

    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAllByOrderByIdAsc();
    }

    public CategoryModel createCategory(CategoryModel categoryModel) {
        return categoryRepository.save(categoryModel);
    }

    public void deleteCategory(CategoryModel category) {
        categoryRepository.delete(category);
    }

    public CategoryModel updateCategoryDescription(Long categoryId, String description) {
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
