package com.bignerdranch.android.myreceipts.database;

public class ReceiptDbSchema {
    public static final class ReceiptTable {
        public static final String NAME = "receipts";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SHOP_NAME = "shop_name";
            public static final String COMMENT = "comment";
            public static final String LOCATION_LAT = "location_lat";
            public static final String LOCATION_LON = "location_lon";
        }
    }
}
