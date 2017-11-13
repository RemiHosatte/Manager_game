package risicomp.manager.bdd;

/**
 * Created by PC on 24/10/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import risicomp.manager.classes.Categorie;

/**
 * Created by PC on 18/10/2017.
 */



public class CategorieBDD extends SQLiteDataBaseHelper{
    // Database Name
    private static final String TABLE_CATEGORIE = "CATEGORIE";
    private static final String COL_ID = "ID";
    private static final String COL_NOM = "NOM";
    private static final String COL_IMAGE_PATH = "IMAGE";


    public CategorieBDD(Context context) {
        super(context);
    }



    public boolean insertCategorie(Categorie categorie){
        ContentValues values = new ContentValues();

        values.put(COL_NOM, categorie.getNom());
        values.put(COL_IMAGE_PATH, categorie.getImage_path());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert(TABLE_CATEGORIE, null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public Categorie getCategorie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORIE, new String[] { COL_ID,
                        COL_NOM, COL_IMAGE_PATH }, COL_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Categorie categorie = new Categorie(cursor.getInt(0),cursor.getString(1), cursor.getString(2));
        db.close();
        return categorie;
    }
    public ArrayList<Categorie> getAllCategories() {
        ArrayList<Categorie> ListCategories = new ArrayList<Categorie>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_CATEGORIE;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Categorie categorie = new Categorie();
                categorie.setId(c.getInt((c.getColumnIndex(COL_ID))));
                categorie.setNom(c.getString((c.getColumnIndex(COL_NOM))));
                categorie.setImage_path(c.getString((c.getColumnIndex(COL_IMAGE_PATH))));
                ListCategories.add(categorie);
            } while (c.moveToNext());
        }
        db.close();
        return ListCategories;
    }
}
