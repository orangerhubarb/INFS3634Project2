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
                    TutorialEntry._ID + " INTEGER PRIMARY KEY," +
                    TutorialEntry.COLUMN_TUTORIAL + " INTEGER," +
                    TutorialEntry.COLUMN_FNAME + " TEXT," +
                    TutorialEntry.COLUMN_LNAME + " TEXT" + ")";

    public StudentsContract(SQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }


    public abstract class TutorialEntry implements BaseColumns {
        public static final String COLUMN_TUTORIAL = "tutorial";
        public static final String COLUMN_FNAME = "fname";
        public static final String COLUMN_LNAME = "lname";
    }

    public long insertNewStudent(Student student) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TutorialEntry.COLUMN_TUTORIAL, student.getTutorialID());
        values.put(TutorialEntry.COLUMN_FNAME, student.getFirstName());
        values.put(TutorialEntry.COLUMN_LNAME, student.getLastName());


        long newRowId;
        newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public ArrayList<Student> getStudentsList(int tutorial) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {
                TutorialEntry.COLUMN_TUTORIAL,
                TutorialEntry.COLUMN_FNAME,
                TutorialEntry.COLUMN_LNAME,

        };

        String sortOrder = TutorialEntry.COLUMN_LNAME;

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

            if (cur.getInt(cur.getColumnIndexOrThrow(TutorialEntry.COLUMN_TUTORIAL)) == tutorial) {
                Student student = new Student();
                student.setFirstName(cur.getString(cur.getColumnIndexOrThrow(TutorialEntry.COLUMN_FNAME)));
                student.setLastName(cur.getString(cur.getColumnIndexOrThrow(TutorialEntry.COLUMN_LNAME)));
                studentList.add(student);
            }
        }
        cur.close();
        db.close();
        return studentList;
    }
}
