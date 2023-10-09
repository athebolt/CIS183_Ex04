package com.example.databaseexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Users.db";
    private static final String TABLE_NAME = "Users";
    public DatabaseHelper(Context context)
    {
        //We will use this to create the database
        //it accepts the context, the name, factory (leave null), and version number
        //if your database becomes corrupt or the info in the database is wrong
        //change the version number
        //super is used to call the functionality of the base class SQLiteOpenHelper
        //the executes the extended class (DatabaseHelper)


        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Create table in the database
        //execute the sql statement on the database that was passed to the function onCreate called db
        //This can be trick because we have to write our sql statements as strings

        //3 attributes: username, first name, and last name
        //all 3 attributes will be TEXT and username will be the primary key
        //username has to be unique or else it will break
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (username TEXT PRIMARY KEY NOT NULL, firstname TEXT, lastname TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        //This is used if we change the version number of the database.

        //delete the table if you upgrade the database (change the version number in the constructor)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");

        //create a new table once the old table has been deleted
        onCreate(db);
    }
    //this is used to insert default info into the table.
    public boolean initializeDB()
    {
        if(numberOfRowsInTable() == 0)
        {
            //connect to the database
            //notice we are getting a writeable database because we need to insert info into our database
            SQLiteDatabase db = this.getWritableDatabase();

            //execute insert statements
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('Athebolt','Alex','Thebolt');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('Ssample','Sam','Sample');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('Ttest','Tim','Test');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('Zmoore','Zack','Moore');");

            db.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public int numberOfRowsInTable()
    {
        //Look at the database we created
        //get a readable version.
        SQLiteDatabase db = this.getReadableDatabase();

        //store the number of records in the table called TABLE_NAME
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);

        //close the database
        db.close();

        return numRows;
    }

    @SuppressLint("Range")
    public ArrayList<User> getAllRows()
    {
        //This will be used to store the info that the table returns
        ArrayList<User> listUsers = new ArrayList<User>();

        //query to get all rows and attributes from our table
        //select * means get all attributes
        String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";

        //get an instance of a readable database and store it in db
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query. Cursor will be used to cycle through the results
        Cursor cursor = db.rawQuery(selectQuery, null);

        //used to store attributes
        String fname;
        String lname;
        String uname;

        //if there was something returned move the cursor to the begining of the list
        if(cursor.moveToFirst())
        {
            do
            {
                //find the username column from the return results
                uname = cursor.getString(cursor.getColumnIndex("username"));

                //find the firstname column from the return results
                fname = cursor.getString(cursor.getColumnIndex("firstname"));

                //find the lastname column from the return results
                lname = cursor.getString(cursor.getColumnIndex("lastname"));

                //add the return results to my list
                listUsers.add(new User(uname, fname, lname));
            }
            while (cursor.moveToNext());

            db.close();
        }

        return listUsers;
    }

    public void addNewUser(User u)
    {
        //get an instance of a writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //This line is a little complicated the sql statement should look as follows:
        //INSERT INTO users VALUES('zmoore','Zack','Moore')

        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('" + u.getUname() + "','" + u.getFname() + "','" + u.getLname() + "');");
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllUsernames()
    {
        ArrayList<String> usernames = new ArrayList<String>();

        //query to get all usernames from table
        String selectUsernames = "SELECT username FROM " + TABLE_NAME + ";";

        //Get instance of a readable database and store it db
        SQLiteDatabase db = this.getReadableDatabase();

        //execute query. Cursor will be used to cycle through results
        Cursor cursor = db.rawQuery(selectUsernames, null);

        String username;

        //if there was something returned, move the cursor to the beginning of the list
        if(cursor.moveToFirst())
        {
            do {
                username = cursor.getString(cursor.getColumnIndex("username"));

                usernames.add(username);
            }
            while (cursor.moveToNext());
        }
        //close database
        db.close();
        return usernames;
    }

    //used to delete a specific user
    //this will be passed a username because it is our primary key
    //you MUST delete off the primary key
    public void deleteUser(String uName)
    {
        //get an instance of our database
        //needs to be writeable
        SQLiteDatabase db = this.getWritableDatabase();

        //create our delete command
        //DELETE FROM Users WHERE username = 'zmoore';
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE username = '" + uName + "';");

        //close database
        db.close();
    }

}
