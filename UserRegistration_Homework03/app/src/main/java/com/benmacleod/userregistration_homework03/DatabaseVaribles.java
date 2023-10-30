package com.benmacleod.userregistration_homework03;

public class DatabaseVaribles {
    public static final String DB_NAME = "homework_03_user_database";
    public static  final int DB_VERSION = 1;
    public static  final String TABLE_NAME = "users";

    // Short hand var for query strings.
    public static  String TABLE = TABLE_NAME;

    // Create User Table.
    public static  final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE + " (username TEXT PRIMARY KEY NOT NULL, firstName TEXT, lastName TEXT, email TEXT, password TEXT, age TEXT);";
    public static  final String DROP_USER_TABLE = "DROP TABLE " + TABLE + ";";

    public static  final String INSERT_USER_QUERY = "INSERT INTO " + TABLE + " VALUES ";
    public static  final String UNIQUE_USERNAME_QUERY = "SELECT username FROM " + TABLE + " WHERE username = ";
    public static  final String DELETE_USER_QUERY = "DELETE FROM " + TABLE + " WHERE username = ";
    public static  final  String GET_USER_QUERY = "SELECT * FROM " + TABLE + " WHERE username = ";
    public static  final String UPDATE_USER_QUERY = "UPDATE " + TABLE + " ";
    public  static  final String UPDATE_USER_QUERY_WHERE = "WHERE username = ";
    public static  final String GET_ALL_USERS_QUERY = "SELECT * FROM " + TABLE + " ORDER BY username";
 }
