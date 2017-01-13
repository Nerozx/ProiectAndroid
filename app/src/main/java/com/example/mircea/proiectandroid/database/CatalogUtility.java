package com.example.mircea.proiectandroid.database;

/**
 * Created by palti13 on 1/13/2017.
 */

public class CatalogUtility {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String PUNCTAJ_TABLE = "PUNCTAJ";
    public static final String USER_ID = "IDUser";
    public static final String TEST_ID = "IDTest";
    public static final String MATERIE_ID = "IDMaterie";
    public static final String PUNCTAJ = "Punctaj";
    public static final String CREATE_TABLE_PUNCTAJ = "create table " +
            PUNCTAJ_TABLE + "(" +
            USER_ID + " integer not null " + "FOREIGN KEY (" +USER_ID+") REFERENCES " + LoginUtility.USER_TABLE+"("+
            LoginUtility.USER_ID+")" +
            TEST_ID + " integer not null " + "FOREIGN KEY (" +TEST_ID+") REFERENCES ";


}
