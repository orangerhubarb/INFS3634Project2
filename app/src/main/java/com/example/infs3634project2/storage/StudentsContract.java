package com.example.infs3634project2.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.model.Tutorial;

import java.util.ArrayList;

/**
 * Created by Davian on 11/10/16.
 */
public class StudentsContract {
    public static final String TABLE_NAME = "students";
    public final SQLiteOpenHelper dbHelper;

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    StudentEntry._ID + " INTEGER PRIMARY KEY," +
                    StudentEntry.COLUMN_TUTORIAL + " INTEGER," +
                    StudentEntry.COLUMN_FNAME + " TEXT," +
                    StudentEntry.COLUMN_LNAME + " TEXT" + ")";

    public StudentsContract(SQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }


    public abstract class StudentEntry implements BaseColumns {
        public static final String COLUMN_TUTORIAL = "tutorial";
        public static final String COLUMN_FNAME = "fname";
        public static final String COLUMN_LNAME = "lname";
    }

    public long insertNewStudent(Student student) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StudentEntry.COLUMN_TUTORIAL, student.getTutorialID());
        values.put(StudentEntry.COLUMN_FNAME, student.getFirstName());
        values.put(StudentEntry.COLUMN_LNAME, student.getLastName());


        long newRowId;
        newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public ArrayList<Student> getStudentsList(int tutorialID) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {
                StudentEntry._ID,
                StudentEntry.COLUMN_TUTORIAL,
                StudentEntry.COLUMN_FNAME,
                StudentEntry.COLUMN_LNAME,

        };

        String sortOrder = StudentEntry.COLUMN_LNAME;

        Cursor cur = db.query(
                TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Student> studentList = new ArrayList<>();

        while (cur.moveToNext()) {

            if (cur.getInt(cur.getColumnIndexOrThrow(StudentEntry.COLUMN_TUTORIAL)) == tutorialID) {
                Student student = new Student();
                student.setFirstName(cur.getString(cur.getColumnIndexOrThrow(StudentEntry.COLUMN_FNAME)));
                student.setLastName(cur.getString(cur.getColumnIndexOrThrow(StudentEntry.COLUMN_LNAME)));
                student.setStudentID(cur.getInt(cur.getColumnIndexOrThrow(StudentEntry._ID)));
                studentList.add(student);
            }
        }
        cur.close();
        db.close();
        return studentList;
    }

    public Student getStudent(int studentID) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = StudentEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(studentID)};

        String[] columns = {
                StudentEntry.COLUMN_FNAME,
                StudentEntry.COLUMN_LNAME,
        };

        Cursor cur = db.query(
                TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Student student = null;
        if (cur.moveToNext()) {
            student = new Student();
            student.setFirstName(cur.getString(cur.getColumnIndexOrThrow(StudentEntry.COLUMN_FNAME)));
            student.setLastName(cur.getString(cur.getColumnIndexOrThrow(StudentEntry.COLUMN_LNAME)));
        }

        cur.close();
        db.close();
        return student;
    }
}
