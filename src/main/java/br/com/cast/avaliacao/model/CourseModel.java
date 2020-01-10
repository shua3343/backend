package br.com.cast.avaliacao.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "COURSE")
@EntityListeners(AuditingEntityListener.class)
public class CourseModel {

    @Id
    private Long id;

    @NotBlank
    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    @CreatedDate
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "students_qnt")
    private int students;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category")
    private CategoryModel category;
}
