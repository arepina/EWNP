package com.example.anast.ewnp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WordOperationsIM {

    // Database fields
    private DataBaseWrapperIM dbHelper;
    private String[] STUDENT_TABLE_COLUMNS = { DataBaseWrapperIM.WORD_ID, DataBaseWrapperIM.WORD_NAME, DataBaseWrapperIM.WORD_NAME2 };
    public static SQLiteDatabase database;

    public WordOperationsIM(Context context) {
        dbHelper = new DataBaseWrapperIM(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public WordIM addStudent(String rus, String eng, int id) {
        ContentValues values = new ContentValues();
        values.put(DataBaseWrapperIM.WORD_ID, id);
        values.put(DataBaseWrapperIM.WORD_NAME, rus);
        values.put(DataBaseWrapperIM.WORD_NAME2, eng);
        long studId = database.insert(DataBaseWrapperIM.WORDS, null, values);
        Cursor cursor = database.query(DataBaseWrapperIM.WORDS, STUDENT_TABLE_COLUMNS, DataBaseWrapperIM.WORD_ID + " = " + studId, null, null, null, null);
        cursor.moveToFirst();
        WordIM newComment = parseStudent(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteStudent(WordIM comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DataBaseWrapperIM.WORDS, DataBaseWrapperIM.WORD_ID
                + " = " + id, null);
    }

    public List getAllStudents() {
        List students = new ArrayList();
        Cursor cursor = database.query(DataBaseWrapperIM.WORDS, STUDENT_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            WordIM student = parseStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }

    private WordIM parseStudent(Cursor cursor) {
        WordIM student = new WordIM();
        student.setId((cursor.getInt(0)));
        student.setWordrus(cursor.getString(1));
        student.setWordeng(cursor.getString(2));
        return student;
    }
}