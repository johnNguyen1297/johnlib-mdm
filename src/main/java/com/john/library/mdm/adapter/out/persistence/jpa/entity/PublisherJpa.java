package com.john.library.mdm.adapter.out.persistence.jpa.entity;

import com.john.library.mdm.common.constant.Database;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Year;
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
@Table(name = Database.Table.Publisher.NAME)
public class PublisherJpa extends Audit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = Database.Table.Publisher.Columns.ID, nullable = false)
  private Integer id;

  @Column(name = Database.Table.Publisher.Columns.NAME, nullable = false, unique = true)
  private String name;

  @Column(name = Database.Table.Publisher.Columns.LOCATION)
  private String location;

  @Column(name = Database.Table.Publisher.Columns.PHONE, length = 20)
  private String phone;

  @Column(name = Database.Table.Publisher.Columns.EMAIL)
  private String email;

  @Column(name = Database.Table.Publisher.Columns.ESTABLISHED_YEAR, nullable = false)
  private Year establishedYear;

  @Column(name = Database.Table.Publisher.Columns.DESCRIPTION)
  private String description;
}
