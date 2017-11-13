package risicomp.manager.bdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import risicomp.manager.classes.Categorie;
import risicomp.manager.classes.Genre;

/**
 * Created by PC on 24/10/2017.
 */

public class GenreBDD extends SQLiteDataBaseHelper{
    private static final String TABLE_GENRE = "GENRE";
    private static final String COL_ID = "ID";
    private static final String COL_NOM = "NOM";


    public GenreBDD(Context context) {
        super(context);
    }


    public Genre getGenre(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GENRE, new String[] { COL_ID,
                        COL_NOM}, COL_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Genre genre = new Genre(cursor.getInt(0),cursor.getString(1));
        db.close();
        return genre;
    }
    public ArrayList<Genre> getAllGenres() {
        ArrayList<Genre> ListGenre = new ArrayList<Genre>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_GENRE;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Genre genre = new Genre();
                genre.setId(c.getInt((c.getColumnIndex(COL_ID))));
                genre.setNom(c.getString((c.getColumnIndex(COL_NOM))));
                ListGenre.add(genre);
            } while (c.moveToNext());
        }
        db.close();
        return ListGenre;
    }
}
