package br.com.cast.avaliacao.model.response;

import br.com.cast.avaliacao.model.entity.Category;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseResponse {

    private Long id;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private int students;

    private Long categoryId;
}