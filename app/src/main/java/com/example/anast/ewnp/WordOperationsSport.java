package com.example.anast.ewnp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WordOperationsSport {

    // Database fields
    private DataBaseWrapperSport dbHelper;
    private String[] STUDENT_TABLE_COLUMNS = { DataBaseWrapperSport.WORD_ID, DataBaseWrapperSport.WORD_NAME, DataBaseWrapperSport.WORD_NAME2 };
    public static SQLiteDatabase database;

    public WordOperationsSport(Context context) {
        dbHelper = new DataBaseWrapperSport(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public WordSport addStudent(String rus, String eng, int id) {

        ContentValues values = new ContentValues();
        values.put(DataBaseWrapperSport.WORD_ID, id);
        values.put(DataBaseWrapperSport.WORD_NAME, rus);
        values.put(DataBaseWrapperSport.WORD_NAME2, eng);
        long studId = database.insert(DataBaseWrapperSport.WORDS, null, values);

        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseWrapperSport.WORDS,
                STUDENT_TABLE_COLUMNS, DataBaseWrapperSport.WORD_ID + " = "
                        + studId, null, null, null, null);

        cursor.moveToFirst();

        WordSport newComment = parseStudent(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteStudent(WordSport comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DataBaseWrapperSport.WORDS, DataBaseWrapperSport.WORD_ID
                + " = " + id, null);
    }

    public List getAllStudents() {
        List students = new ArrayList();

        Cursor cursor = database.query(DataBaseWrapperSport.WORDS,
                STUDENT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            WordSport student = parseStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }

        cursor.close();
        return students;
    }

    private WordSport parseStudent(Cursor cursor) {
        WordSport student = new WordSport();
        student.setId((cursor.getInt(0)));
        student.setWordrus(cursor.getString(1));
        student.setWordeng(cursor.getString(2));
        return student;
    }
}