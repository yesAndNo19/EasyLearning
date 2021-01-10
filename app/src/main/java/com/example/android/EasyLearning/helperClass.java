package com.example.android.EasyLearning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class helperClass extends SQLiteOpenHelper {

    public  static  final String FILE_NAME_Quiz="quizdata.dp";
    public  static  final int FILE_VERSION_Quiz=1;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConractClass.CREATE_TABLE_Q_DATA);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public helperClass(@Nullable Context context) {
        super(context,FILE_NAME_Quiz, null, FILE_VERSION_Quiz);
    }
}
