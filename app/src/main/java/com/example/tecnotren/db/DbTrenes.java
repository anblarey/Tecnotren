package com.example.tecnotren.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tecnotren.entidades.Trenes;

import java.util.ArrayList;

public class DbTrenes extends DbHelper {

    Context context;
    public DbTrenes(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaTren(String serie, String servicio, String nombre, String traccion, String velocidad, String anchovia, String plazas, String puestaservicio, String unidades) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("serie", serie);
            values.put("servicio", servicio);
            values.put("nombre", nombre);
            values.put("traccion", traccion);
            values.put("velocidad", velocidad);
            values.put("ancho de via", anchovia);
            values.put("plazas", plazas);
            values.put("puesta en servicio", puestaservicio);
            values.put("unidades", serie);

            id = db.insert(TABLE_TRENES, null, values);

        } catch(Exception exception){
            exception.toString();
        }

        return id;
    }

    public ArrayList<Trenes> mostrarTrenes(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Trenes> listaTrenes = new ArrayList<>();
        Trenes tren = null;
        Cursor cursorTrenes = null;

        cursorTrenes = db.rawQuery("SELECT * FROM " + TABLE_TRENES, null);

        if(cursorTrenes.moveToFirst()){
            do{
                tren = new Trenes();
                tren.setId(cursorTrenes.getInt(0));
                tren.setSerie(cursorTrenes.getString(1));
                tren.setServicio(cursorTrenes.getString(2));
                tren.setNombre(cursorTrenes.getString(3));
                tren.setTraccion(cursorTrenes.getString(4));
                tren.setVelocidad(cursorTrenes.getString(5));
                tren.setAnchovia(cursorTrenes.getString(6));
                tren.setPlazas(cursorTrenes.getString(7));
                tren.setPuestaservicio(cursorTrenes.getString(8));
                tren.setUnidades(cursorTrenes.getString(9));
                listaTrenes.add(tren);
            } while (cursorTrenes.moveToNext());
        }

        cursorTrenes.close();

        return listaTrenes;

    }

    public Trenes verTren(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Trenes tren = null;
        Cursor cursorTrenes = null;

        cursorTrenes = db.rawQuery("SELECT * FROM " + TABLE_TRENES + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorTrenes.moveToFirst()){

            tren = new Trenes();
            tren.setId(cursorTrenes.getInt(0));
            tren.setSerie(cursorTrenes.getString(1));
            tren.setServicio(cursorTrenes.getString(2));
            tren.setNombre(cursorTrenes.getString(3));
            tren.setTraccion(cursorTrenes.getString(4));
            tren.setVelocidad(cursorTrenes.getString(5));
            tren.setAnchovia(cursorTrenes.getString(6));
            tren.setPlazas(cursorTrenes.getString(7));
            tren.setPuestaservicio(cursorTrenes.getString(8));
            tren.setUnidades(cursorTrenes.getString(9));
        }

        cursorTrenes.close();

        return tren;

    }

    public boolean editarTren(int id, String serie, String servicio, String nombre, String traccion, String velocidad, String anchovia, String plazas, String puestaservicio, String unidades) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLE_TRENES + "SET serie = '"+serie+"', servicio = '"+servicio+"', nombre = '"+nombre+"', traccion = '"+traccion+"', velocidad = '"+velocidad+"', anchovia = '"+anchovia+"', plazas = '"+plazas+"', puestaservicio = '"+puestaservicio+"', unidades = '"+unidades+"' WHERE id='"+id+"' ");
            correcto = true;
        } catch(Exception exception){
            exception.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarTren(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("DELETE FROM " + TABLE_TRENES + "WHERE id = '"+ id +"'");
            correcto = true;
        } catch(Exception exception){
            exception.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
