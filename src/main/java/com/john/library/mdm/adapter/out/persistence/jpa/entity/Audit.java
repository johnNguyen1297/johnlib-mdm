package com.john.library.mdm.adapter.out.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Audit {

  private static final String CREATED_BY = "created_by";
  private static final String CREATED_AT = "created_date";
  private static final String LAST_MODIFIED_BY = "last_modified_by";
  private static final String LAST_MODIFIED_DATE = "last_modified_date";

  @CreatedBy
  @Column(name = CREATED_BY, length = 100, nullable = false, updatable = false)
  private String createdBy = "Admin";

  @CreatedDate
  @Column(name = CREATED_AT, nullable = false, updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  @LastModifiedBy
  @Column(name = LAST_MODIFIED_BY, length = 100, nullable = false)
  private String updatedBy;

  @LastModifiedDate
  @Column(name = LAST_MODIFIED_DATE, nullable = false)
  private LocalDateTime updatedAt;
}
