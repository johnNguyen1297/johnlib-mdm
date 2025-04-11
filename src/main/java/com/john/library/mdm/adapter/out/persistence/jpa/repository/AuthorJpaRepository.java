package com.john.library.mdm.adapter.out.persistence.jpa.repository;

import com.john.library.mdm.adapter.out.persistence.jpa.entity.AuthorJpa;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorJpaRepository extends JpaRepository<AuthorJpa, Integer> {

  Optional<AuthorJpa> findByName(String name);

  @Query("""
      SELECT a
      FROM AuthorJpa a
      WHERE COALESCE(:q, NULL) IS NULL
                  OR (LOWER(a.name) LIKE LOWER(CONCAT('%', :q, '%'))
                  OR LOWER(a.bio) LIKE LOWER(CONCAT('%', :q, '%'))
                  OR LOWER(a.nationality) LIKE LOWER(CONCAT('%', :q, '%')))
      """)
  Page<AuthorJpa> listByFilter(String q, Pageable pageable);
}
