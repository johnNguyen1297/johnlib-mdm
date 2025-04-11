package com.john.library.mdm.adapter.out.persistence.jpa.entity;

import com.john.library.mdm.common.constant.Database;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = Database.Table.Book.NAME)
public class BookJpa extends Audit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = Database.Table.Book.Columns.ID, nullable = false)
  private Integer id;

  @Column(name = Database.Table.Book.Columns.ISBN, nullable = false, unique = true)
  private String isbn;

  @Column(name = Database.Table.Book.Columns.TITLE, nullable = false)
  private String title;

  @ManyToOne
  @JoinColumn(name = Database.Table.Book.Columns.PUBLISHER_ID, nullable = false)
  private PublisherJpa publisher;

  @Column(name = Database.Table.Book.Columns.PUBLICATION_YEAR, nullable = false)
  private Integer publicationYear;

  @Column(name = Database.Table.Book.Columns.FORMAT)
  private String format;

  @Column(name = Database.Table.Book.Columns.PRINT_LENGTH)
  private Integer printLength;

  @Column(name = Database.Table.Book.Columns.DESCRIPTION)
  private String description;

  @Column(name = Database.Table.Book.Columns.LANGUAGE)
  private String language;

  // TODO 11/04/2025: Xref tables
}

