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
        public final String DESCRIPTION = "description";
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
  }
}
