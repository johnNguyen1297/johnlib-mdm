package com.john.library.mdm.adapter.out.persistence.jpa.entity;

import com.john.library.mdm.common.constant.Database;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Accessors(chain = true)
@Builder(toBuilder = true)
@Entity
@Table(name = Database.Table.Tag.NAME)
public class TagJpa extends Audit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = Database.Table.Tag.Columns.ID, nullable = false)
  private Integer id;

  @Column(name = Database.Table.Tag.Columns.NAME, nullable = false, unique = true)
  private String name;

  @Column(name = Database.Table.Tag.Columns.DESCRIPTION)
  private String description;
}
