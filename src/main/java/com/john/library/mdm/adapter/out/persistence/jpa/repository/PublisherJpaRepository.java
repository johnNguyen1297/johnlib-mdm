package com.john.library.mdm.adapter.out.persistence.jpa.repository;

import com.john.library.mdm.adapter.out.persistence.jpa.entity.PublisherJpa;
import java.time.Year;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PublisherJpaRepository extends JpaRepository<PublisherJpa, Integer> {

  @Query("""
      SELECT p
      FROM PublisherJpa p
      WHERE COALESCE(:q, NULL) IS NULL
                  OR (LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%'))
                  OR LOWER(p.location) LIKE LOWER(CONCAT('%', :q, '%'))
                  OR LOWER(p.phone) LIKE LOWER(CONCAT('%', :q, '%'))
                  OR LOWER(p.email) LIKE LOWER(CONCAT('%', :q, '%')))
          AND COALESCE(:establishedYear, NULL) IS NULL
                  OR p.establishedYear = :establishedYear
      """)
  Page<PublisherJpa> listByFilter(String q, Year establishedYear, Pageable pageable);

  Optional<PublisherJpa> findByName(String name);
}
