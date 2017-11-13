package risicomp.manager.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import risicomp.manager.classes.Categorie;
import risicomp.manager.classes.Genre;
import risicomp.manager.classes.Jeu;
import risicomp.manager.classes.Sous_Categorie;

/**
 * Created by PC on 24/10/2017.
 */

public class JeuBDD extends SQLiteDataBaseHelper {

    // Database Name
    private static final String TABLE_JEU = "GAME";
    private static final String COL_ID = "ID";
    private static final String COL_NOM = "NOM";
    private static final String COL_IMAGE_PATH = "IMAGE";
    private static final String COL_ID_GENRE = "ID_GENRE";
    private static final String COL_DEVELOPPEUR = "DEVELOPPEUR";
    private static final String COL_EDITEUR = "EDITEUR";
    private static final String COL_ANNEE = "ANNEE";
    private static final String COL_RARETE = "RARETE";
    private static final String COL_QUANTITE = "QUANTITE";
    private static final String COL_ID_SOUS_CATEGORIE = "ID_SOUS_CATEGORIE";



    public JeuBDD(Context context) {
        super(context);
    }


    public boolean insertJeu(Jeu jeu){
        ContentValues values = new ContentValues();

        values.put(COL_NOM, jeu.getNom());
        values.put(COL_IMAGE_PATH, jeu.getImage_path());
        values.put(COL_ID_GENRE, jeu.getGenre().getId());
        values.put(COL_DEVELOPPEUR, jeu.getDeveloppeur());
        values.put(COL_EDITEUR, jeu.getEditeur());
        values.put(COL_ANNEE, jeu.getAnnee());
        values.put(COL_RARETE, jeu.getRarete());
        values.put(COL_QUANTITE, jeu.getQuantité());
        values.put(COL_ID_SOUS_CATEGORIE, jeu.getSous_categorie().getId());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert(TABLE_JEU, null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public Jeu getJeu(int id, Context context) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_JEU, new String[] {
                        COL_ID,
                        COL_NOM,
                        COL_IMAGE_PATH,
                        COL_ID_GENRE,
                        COL_DEVELOPPEUR,
                        COL_EDITEUR,
                        COL_ANNEE,
                        COL_RARETE,
                        COL_QUANTITE,
                        COL_ID_SOUS_CATEGORIE}, COL_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        GenreBDD genrebdd = new GenreBDD(context);
        Genre genre = genrebdd.getGenre(cursor.getInt(3));

        Sous_CategorieBDD sous_categoriebdd = new Sous_CategorieBDD(context);
        Sous_Categorie sous_cat = sous_categoriebdd.getSous_categorie(cursor.getInt(9), context);
        Jeu jeu = new Jeu(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                genre,
                cursor.getString(4),
                cursor.getString(5),
                cursor.getInt(6),
                cursor.getInt(7),
                cursor.getInt(8),
                sous_cat);

        db.close();
        return jeu;
    }
    public List<Jeu> getJeuBySous_categorie(int id_sous_categorie, Context context)
    {

        List<Jeu> ListJeu = new ArrayList<Jeu>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_JEU + " WHERE " + COL_ID_SOUS_CATEGORIE + "=" + id_sous_categorie;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                Jeu jeu = new Jeu();
                jeu.setId(cursor.getInt(0));
                jeu.setNom(cursor.getString(1));
                jeu.setImage_path(cursor.getString(2));

                GenreBDD genrebdd = new GenreBDD(context);
                genrebdd.getGenre(cursor.getInt(3));
                jeu.setGenreBDD(genrebdd);
                jeu.setDeveloppeur(cursor.getString(4));
                jeu.setEditeur(cursor.getString(5));
                jeu.setAnnee(cursor.getInt(6));
                jeu.setRarete(cursor.getInt(7));
                jeu.setQuantité(cursor.getInt(8));

                Sous_CategorieBDD sous_categoriebdd = new Sous_CategorieBDD(context);
                sous_categoriebdd.getSous_categorie(cursor.getInt(8), context);
                jeu.setSous_categorieBDD(sous_categoriebdd);

                ListJeu.add(jeu);
            } while (cursor.moveToNext());
        }
        db.close();
        return ListJeu;

    }
}
