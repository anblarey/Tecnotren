package com.example.tecnotren.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "tecnotren.db";
    public static final String TABLE_TRENES = "t_trenes";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TRENES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "serie TEXT NOT NULL," +
                "servicio TEXT NOT NULL," +
                "nombre TEXT," +
                "traccion TEXT NOT NULL," +
                "velocidad TEXT NOT NULL," +
                "anchovia TEXT NOT NULL," +
                "plazas TEXT NOT NULL," +
                "puestaservicio TEXT NOT NULL," +
                "unidades TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TRENES);
        onCreate(sqLiteDatabase);
    }
}
