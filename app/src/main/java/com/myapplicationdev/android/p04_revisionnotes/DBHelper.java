package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	//TODO Define the Database properties
	private static final int DATABASE_VERSION = 1;
	// Filename of the database
	private static final String DATABASE_NAME = "notes.db";

	private static final String TABLE_NOTE = "note";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_CONTENT = "note_content";
	private static final String COLUMN_STAR = "stars";


	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//TODO CREATE TABLE Note
		String createTableSql = "CREATE TABLE " + TABLE_NOTE +  "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_CONTENT + " TEXT NOT NULL,"
				+ COLUMN_STAR + " INTEGER NOT NULL)";
		db.execSQL(createTableSql);
		Log.i("info" ,"created tables");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
		onCreate(db);
	}

	public void insertNote(String noteContent, int stars) {
		//TODO insert the data into the database
		SQLiteDatabase db = this.getWritableDatabase();
		// We use ContentValues object to store the values for
		//  the db operation
		ContentValues values = new ContentValues();
		values.put(COLUMN_CONTENT, noteContent);

		values.put(COLUMN_STAR, stars);

		db.insert(TABLE_NOTE, null, values);
		// Close the database connection
		db.close();
	}

	public ArrayList<Note> getAllNotes() {
		//TODO return records in Java objects
		// Create an ArrayList that holds String objects
		ArrayList<Note> notes = new ArrayList<Note>();
		// Select all the tasks' description
		String selectQuery = "SELECT "+COLUMN_ID+", " + COLUMN_CONTENT+", "+COLUMN_STAR
				+ " FROM " + TABLE_NOTE;

		// Get the instance of database to read
		SQLiteDatabase db = this.getReadableDatabase();
		// Run the SQL query and get back the Cursor object
		Cursor cursor = db.rawQuery(selectQuery, null);

		// moveToFirst() moves to first row, null if no records
		if (cursor.moveToFirst()) {
			// Loop while moveToNext() points to next row
			//  and returns true; moveToNext() returns false
			//  when no more next row to move to
			do {
				int id = cursor.getInt(0);
				String content = cursor.getString(1);
				int star = cursor.getInt(2);

				Note note = new Note(id, content, star);

				notes.add(note);

			} while (cursor.moveToNext());
		}
		// Close connection
		cursor.close();
		db.close();

		return notes;
	}

}
