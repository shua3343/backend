package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Long> {
    List<CourseModel> findAllByOrderByIdAsc();

    List<CourseModel> findAllByCategoryId(Long categoryId);

    Optional<CourseModel> findOneByStartDateGreaterThanAndEndDateLessThan(LocalDate startDate, LocalDate endDate);

    Optional<CourseModel> findOneByStartDateLessThanAndEndDateGreaterThan(LocalDate startDate, LocalDate endDate);
}