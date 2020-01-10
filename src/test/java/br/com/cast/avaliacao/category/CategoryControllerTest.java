package br.com.cast.avaliacao.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.cast.avaliacao.controller.category.CategoryEndPoints.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllCategoriesTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(CATEGORIES + GET_ALL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createCategoryTest() throws Exception {
        String teste = "{" +
                "\"description\": \"Description Teste\"" +
                "}";

        mvc.perform(MockMvcRequestBuilders.post(CATEGORIES + CREATE)
                .content(teste)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

//    @Test
//    public void updateCategoryDescriptionTest() {
//
//    }
//
//    @Test
//    public void deleteCategoryTest() {
//
//    }
}
