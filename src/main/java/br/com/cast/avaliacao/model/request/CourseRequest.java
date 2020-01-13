package br.com.cast.avaliacao.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class CourseRequest {

    @NotBlank
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private int students;

    private Long categoryId;
}