package br.com.cast.avaliacao.converter;

import br.com.cast.avaliacao.model.entity.Category;
import br.com.cast.avaliacao.model.request.CategoryRequest;
import br.com.cast.avaliacao.model.response.CategoryResponse;

import javax.validation.constraints.NotNull;

public class CategoryConverter {

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
