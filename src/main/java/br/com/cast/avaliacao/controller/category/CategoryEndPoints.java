package br.com.cast.avaliacao.controller.category;

public interface CategoryEndPoints {
    String CATEGORIES = "/categories";
    String GET_ALL = "/get-all";
    String CREATE = "/create";
    String UPDATE_BY_CATEGORY_ID = "/update/{categoryId}";
    String DELETE_BY_CATEGORY_ID = "/delete/{categoryId}";
}
