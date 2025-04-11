package com.john.library.mdm.adapter.out.persistence.jpa.repository;

import com.john.library.mdm.adapter.out.persistence.jpa.entity.TagJpa;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagJpaRepository extends JpaRepository<TagJpa, Integer> {

  Optional<TagJpa> findByName(String name);

  @Query("""
      SELECT t
      FROM TagJpa t
      WHERE COALESCE(:q, NULL) IS NULL
                  OR (LOWER(t.name) LIKE LOWER(CONCAT('%', :q, '%'))
                  OR LOWER(t.description) LIKE LOWER(CONCAT('%', :q, '%')))
      """)
  Page<TagJpa> listByFilter(String q, Pageable pageable);

}
