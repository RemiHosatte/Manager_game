package risicomp.manager.bdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import risicomp.manager.classes.Categorie;
import risicomp.manager.classes.Genre;
import risicomp.manager.classes.Sous_Categorie;
import risicomp.manager.classes.Type;

/**
 * Created by PC on 24/10/2017.
 */



public class Sous_CategorieBDD extends SQLiteDataBaseHelper{
    // Database Name
    private static final String TABLE_SOUS_CATEGORIE = "SOUS_CATEGORIE";
    private static final String COL_ID = "ID";
    private static final String COL_NOM = "NOM";
    private static final String COL_IMAGE_PATH= "IMAGE";
    private static final String COL_CATEGORIE = "ID_CATEGORIE";
    private static final String COL_TYPE = "ID_TYPE";


    public Sous_CategorieBDD(Context context) {
        super(context);
    }


    public Sous_Categorie getSous_categorie(int id,Context context) {
        SQLiteDatabase db = this.getReadableDatabase();

        Sous_Categorie sous_categorie = new Sous_Categorie();

        String selectQuery = "SELECT * FROM " + TABLE_SOUS_CATEGORIE + " WHERE " + COL_ID + "=" + id;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {

            TypeBDD typebdd = new TypeBDD(context);
            Type type = typebdd.getType(c.getInt(3));

            CategorieBDD categorieBDD = new CategorieBDD(context);
            Categorie categorie = categorieBDD.getCategorie(c.getInt(4));
            System.out.println("ID SOUS CATEGORIE: " + c.getInt(0));
            System.out.println("NOM SOUS CATEGORIE: " + c.getInt(3));
            sous_categorie = new Sous_Categorie(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    type,
                    categorie
            );
        }
        c.close();
        return sous_categorie;
    }

    public ArrayList<Sous_Categorie> getSous_categoryByCategory_id(int id_category, Context context)
    {

        ArrayList<Sous_Categorie> ListSous_Category = new ArrayList<Sous_Categorie>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_SOUS_CATEGORIE + " WHERE " + COL_CATEGORIE + "=" + id_category;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                Sous_Categorie sous_categorie = new Sous_Categorie();
                sous_categorie.setId(cursor.getInt(0));
                sous_categorie.setNom(cursor.getString(1));
                sous_categorie.setImage_path(cursor.getString(2));

                TypeBDD typebdd = new TypeBDD(context);
                typebdd.getType(cursor.getInt(3));
                sous_categorie.setTypeBDD(typebdd);

                CategorieBDD categorieBDD = new CategorieBDD(context);
                categorieBDD.getCategorie(cursor.getInt(4));
                sous_categorie.setCategorieBDD(categorieBDD);

                ListSous_Category.add(sous_categorie);
            } while (cursor.moveToNext());
        }
        db.close();
        return ListSous_Category;

    }
}
