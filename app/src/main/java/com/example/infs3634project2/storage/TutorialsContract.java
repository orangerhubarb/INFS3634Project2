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
 * Created by Davian on 6/10/16.
 */
public class TutorialsContract {

    public static final String TABLE_NAME = "tutorials";
    public final SQLiteOpenHelper dbHelper;

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    TutorialEntry._ID + " INTEGER PRIMARY KEY," +
                    TutorialEntry.COLUMN_NAME + " TEXT" +
                    TutorialEntry.COLUMN_STUDENTS + " TEXT" + ")";

    public TutorialsContract(SQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }


    public abstract class TutorialEntry implements BaseColumns {
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_STUDENTS = "students";


    }

    public long insertNewTutorial(Tutorial tutorial) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TutorialEntry.COLUMN_NAME, tutorial.getName());

        long newRowId;
        newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public ArrayList<Tutorial> getTutorials() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {
                TutorialEntry._ID,
                TutorialEntry.COLUMN_NAME,

        };

        String sortOrder = TutorialEntry._ID;

        Cursor cur = db.query(
                TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Tutorial> tutorialsList = new ArrayList<>();

        while (cur.moveToNext()){
            Tutorial tutorial = new Tutorial();
            tutorial.setName(cur.getString(cur.getColumnIndexOrThrow(TutorialEntry.COLUMN_NAME)));


            tutorialsList.add(tutorial);
        }

        cur.close();
        db.close();
        return tutorialsList;
    }


}