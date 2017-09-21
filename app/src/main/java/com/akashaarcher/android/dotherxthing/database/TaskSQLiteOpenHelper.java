package com.akashaarcher.android.dotherxthing.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.akashaarcher.android.dotherxthing.model.Task;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by akashaarcher on 9/19/17.
 */

public class TaskSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dotherxthing.db";
    private static final int DATABASE_VERSION = 1;

    private static TaskSQLiteOpenHelper instance;


    public static synchronized TaskSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (instance == null) {
            instance = new TaskSQLiteOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    static {
        //register the model
        cupboard().register(Task.class);
    }


    public TaskSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        cupboard().withDatabase(sqLiteDatabase).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        cupboard().withDatabase(sqLiteDatabase).upgradeTables();
    }
}
