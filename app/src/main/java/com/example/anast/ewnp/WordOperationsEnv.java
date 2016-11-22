package com.example.anast.ewnp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WordOperationsEnv {

    // Database fields
    private DataBaseWrapperEnv dbHelper;
    private String[] STUDENT_TABLE_COLUMNS = { DataBaseWrapperEnv.WORD_ID, DataBaseWrapperEnv.WORD_NAME, DataBaseWrapperEnv.WORD_NAME2 };
    public static SQLiteDatabase database;

    public WordOperationsEnv(Context context) {
        dbHelper = new DataBaseWrapperEnv(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public WordEnv addStudent(String rus, String eng, int id) {
        ContentValues values = new ContentValues();
        values.put(DataBaseWrapperEnv.WORD_ID, id);
        values.put(DataBaseWrapperEnv.WORD_NAME, rus);
        values.put(DataBaseWrapperEnv.WORD_NAME2, eng);
        long studId = database.insert(DataBaseWrapperEnv.WORDS, null, values);

        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseWrapperEnv.WORDS,
                STUDENT_TABLE_COLUMNS, DataBaseWrapperEnv.WORD_ID + " = "
                        + studId, null, null, null, null);

        cursor.moveToFirst();

        WordEnv newComment = parseStudent(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteStudent(WordEnv comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DataBaseWrapperEnv.WORDS, DataBaseWrapperEnv.WORD_ID
                + " = " + id, null);
    }

    public List getAllStudents() {
        List students = new ArrayList();

        Cursor cursor = database.query(DataBaseWrapperEnv.WORDS, STUDENT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            WordEnv student = parseStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }

        cursor.close();
        return students;
    }

    private WordEnv parseStudent(Cursor cursor) {
        WordEnv student = new WordEnv();
        student.setId((cursor.getInt(0)));
        student.setWordrus(cursor.getString(1));
        student.setWordeng(cursor.getString(2));
        return student;
    }
}