package com.example.listainventario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "Inventario.db";
    private static final int DATABASE_VERSION = 5;

    private static final String TABLE_NAME = "t_productos";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCTO = "producto";
    private static final String COLUMN_PRECIO = "precio";
    private static final String COLUMN_CANTIDAD = "cantidad";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTO + " TEXT, " +
                COLUMN_PRECIO + " INTEGER, " +
                COLUMN_CANTIDAD + " INTEGER);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void agregar(String producto, String precio, int cantidad){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PRODUCTO, producto);
        cv.put(COLUMN_PRECIO, precio);
        cv.put(COLUMN_CANTIDAD, cantidad);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Agregado con éxito!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor leertodo(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void actualizar(String row_id, String producto, String precio, String cantidad){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCTO, producto);
        cv.put(COLUMN_PRECIO, precio);
        cv.put(COLUMN_CANTIDAD, cantidad);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Actualizado con éxito!", Toast.LENGTH_SHORT).show();
        }

    }

    void eliminarfila(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Error al eliminar.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Eliminado con éxito.", Toast.LENGTH_SHORT).show();
        }
    }

    void eliminartodo(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
