package com.benmacleod.userregistration_homework03;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper
{

    public Database(Context ctx)
    {
        // Establish the connection to the database.
        // Version: creates a new instance of the database via the version.
        super(ctx, DatabaseVaribles.DB_NAME, null, DatabaseVaribles.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Create the users table.
        db.execSQL(DatabaseVaribles.CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        // Drop existing user table.
        db.execSQL(DatabaseVaribles.DROP_USER_TABLE);

        // Rerun create sequence.
        onCreate(db);
    }

    // Initialize the database and it's database for the first time.
    public boolean initialize()
    {
        // If row count isn't 0 then it has been initialized.
        if (getRowCount() != 0)
        {
            return false;
        }

        // Writeable database.
        SQLiteDatabase db = this.getWritableDatabase();

        /* Insert data here */

        db.close();
        return true;
    }

    // Get the number of rows in the database.
    public int getRowCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        // Get the row count here.
        int rowCount = (int) DatabaseUtils.queryNumEntries(db, DatabaseVaribles.TABLE);

        db.close();
        return rowCount;
    }

    // Create new user.
    public void createUser(User user)
    {
        // Writeable database.
        SQLiteDatabase db = this.getWritableDatabase();

        // Checked on the front-end, but double check here for redundancy.
        if (!isValidUsername(user.getUsername())) return;

        // Insert the user into the database.
        db.execSQL(DatabaseVaribles.INSERT_USER_QUERY + "('" + user.getUsername() + "','" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getEmail() + "','" + user.getPassword() + "','" + user.getAge() + "');");

        // Close connection.
        db.close();
    }

    // Get all of a users details.
    @SuppressLint("Range")
    public User getUser(String username)
    {
        // Readable Database.
        SQLiteDatabase db = this.getReadableDatabase();

        // Search for the user that has a unique username.
        Cursor record = db.rawQuery(DatabaseVaribles.GET_USER_QUERY + "'" + username + "';", null);

        // New user var.
        User user = new User();

        // If the record exist extract it into user var ^.
        if (record.moveToFirst())
        {
            String _username = record.getString(record.getColumnIndex("username"));
            String firstName = record.getString(record.getColumnIndex("firstName"));
            String lastName = record.getString(record.getColumnIndex("lastName"));
            String email = record.getString(record.getColumnIndex("email"));
            String password = record.getString(record.getColumnIndex("password"));
            String age = record.getString(record.getColumnIndex("age"));

            user = new User(_username, firstName, lastName, email ,password, age);
        }

        return user;
    }

    // Update a user details.
    public void updateUser(User user)
    {
        // Writeable database.
        SQLiteDatabase db = this.getWritableDatabase();

        // Update the user.
        db.execSQL(DatabaseVaribles.UPDATE_USER_QUERY + "SET firstName = '" + user.getFirstName() +"', lastName = '" + user.getLastName() + "', email = '" + user.getEmail() + "', password = '" + user.getPassword() + "', age = '" + user.getAge() + "' " + DatabaseVaribles.UPDATE_USER_QUERY_WHERE + "'" + user.getUsername() +"';");

        // Close the connection.
        db.close();
    }

    public void deleteUser(String username)
    {
        // Writeable Database
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete the user from the database.
        db.execSQL(DatabaseVaribles.DELETE_USER_QUERY + "'" + username +"';");

        // Close connection
        db.close();
    }

    // Get a list of all users.
    @SuppressLint("Range")
    public ArrayList<User> getAllUsers()
    {
        // Readable Database.
        SQLiteDatabase db = this.getReadableDatabase();

        // List to store all of the users to be passed.
        ArrayList<User> listOfUsers = new ArrayList<User>();

        Cursor cursor = db.rawQuery(DatabaseVaribles.GET_ALL_USERS_QUERY, null);

        // If we find a record of any kind, move forward.
        if (cursor.moveToFirst())
        {
            User tempUser;

            // Loop throw all the records.
            do
            {
                String _username = cursor.getString(cursor.getColumnIndex("username"));
                String firstName = cursor.getString(cursor.getColumnIndex("firstName"));
                String lastName = cursor.getString(cursor.getColumnIndex("lastName"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String age = cursor.getString(cursor.getColumnIndex("age"));

                // Create a temp new user object.
                tempUser = new User(_username, firstName, lastName, email, password, age);

                // Add temp user to list of users.
                listOfUsers.add(tempUser);
            }
            while (cursor.moveToNext());
        }


        db.close();
        return listOfUsers;
    }

    // Check if a username is unique before adding it to the database.
    public boolean isValidUsername(String username)
    {
        // Readable database.
        SQLiteDatabase db = this.getReadableDatabase();

        // Query for any records that have the same username.
        Cursor records = db.rawQuery(DatabaseVaribles.UNIQUE_USERNAME_QUERY + "'" + username +"';", null);

        // If the count of records is not 0, then it's not unique.
        if (records.getCount() != 0)
        {
            return false;
        }

        return true;
    }
}
