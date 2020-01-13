package br.com.cast.avaliacao.converter;

import br.com.cast.avaliacao.model.entity.Category;
import br.com.cast.avaliacao.model.request.CategoryRequest;
import br.com.cast.avaliacao.model.response.CategoryResponse;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    public List<CategoryResponse> getCategoryResponseList(List<Category> categories) {
        return categories.stream()
                .map(this::getCategoryResponse)
                .collect(Collectors.toList());
    }

    public Category getCategory(@NotNull CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setDescription(categoryRequest.getDescription());

        return category;
    }

    public CategoryResponse getCategoryResponse(@NotNull Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setDescription(category.getDescription());

        return categoryResponse;
    }
}
