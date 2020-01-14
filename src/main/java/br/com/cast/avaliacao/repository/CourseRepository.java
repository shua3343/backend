package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByOrderByIdAsc();

    List<Course> findAllByCategoryId(Long categoryId);

    Optional<Course> findOneByStartDateGreaterThanAndEndDateLessThan(LocalDate startDate, LocalDate endDate);

    Optional<Course> findOneByStartDateLessThanAndEndDateGreaterThan(LocalDate startDate, LocalDate endDate);

    Optional<Course> findOneByStartDateEqualsAndEndDateEquals(LocalDate startDate, LocalDate endDate);
}