package com.john.library.mdm.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Database {

  @UtilityClass
  public static class Table {

    @UtilityClass
    public static class Publisher {

      public final String NAME = "publishers";

      @UtilityClass
      public static class Columns {

        public final String ID = "id";
        public final String NAME = "name";
        public final String LOCATION = "location";
        public final String PHONE = "phone";
        public final String EMAIL = "email";
        public final String ESTABLISHED_YEAR = "established_year";
        public final String DESCRIPTION = "description"; // NOSONAR
      }
    }

    @UtilityClass
    public static class Tag {

      public final String NAME = "tags";

      @UtilityClass
      public static class Columns {

        public final String ID = "id";
        public final String NAME = "name";
        public final String DESCRIPTION = "description";
      }
    }

    @UtilityClass
    public static class Author {

      public final String NAME = "authors";

      @UtilityClass
      public static class Columns {

        public final String ID = "id";
        public final String NAME = "name";
        public final String BIO = "bio";
        public final String NATIONALITY = "nationality";
      }
    }

    @UtilityClass
    public static class Book {

      public final String NAME = "books";

      @UtilityClass
      public static class Columns {

        public final String ID = "id";
        public final String ISBN = "isbn";
        public final String TITLE = "title";
        public final String PUBLISHER_ID = "publisher_id";
        public final String PUBLICATION_YEAR = "publication_year";
        public final String FORMAT = "format";
        public final String PRINT_LENGTH = "print_length";
        public final String DESCRIPTION = "description";
        public final String LANGUAGE = "language";
      }
    }
  }
}

