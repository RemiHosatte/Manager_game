package risicomp.manager.bdd;

/**
 * Created by PC on 24/10/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PC on 18/10/2017.
 */


public class SQLiteDataBaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 13;
    protected static final String DATABASE_NAME = "game";

    private static final String CREATE_TABLE_CATEGORIE = "CREATE TABLE CATEGORIE" +
            "(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NOM TEXT NOT NULL," +
            "IMAGE TEXT NOT NULL" +
            ");";

    private static final String CREATE_TABLE_TYPE = "CREATE TABLE TYPE" +
            "(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NOM TEXT NOT NULL" +
            ");";

    private static final String CREATE_TABLE_SOUS_CATEGORIE = "CREATE TABLE SOUS_CATEGORIE" +
            "(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NOM INTEGER NOT NULL," +
            "IMAGE TEXT NOT NULL," +
            "ID_TYPE INTEGER NOT NULL," +
            "ID_CATEGORIE," +
            "FOREIGN KEY(ID_CATEGORIE) REFERENCES CATEGORIE(ID)," +
            "FOREIGN KEY(ID_TYPE) REFERENCES TYPE(ID)" +
            ");";

    private static final String CREATE_TABLE_GENRE = "CREATE TABLE GENRE" +
            "(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NOM TEXT NOT NULL" +
            ");";

    private static final String CREATE_TABLE_GAME = "CREATE TABLE GAME" +
            "(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NOM TEXT NOT NULL," +
            "IMAGE TEXT NOT NULL," +
            "ID_GENRE TEXT NOT NULL," +
            "DEVELOPPEUR TEXT," +
            "EDITEUR TEXT," +
            "ANNEE TEXT," +
            "RARETE INTEGER NOT NULL," +
            "QUANTITE INTEGER NOT NULL," +
            "ID_SOUS_CATEGORIE INTEGER NOT NULL," +
            "FOREIGN KEY(ID_SOUS_CATEGORIE) REFERENCES SOUS_CATEGORIE(ID)," +
            "FOREIGN KEY(ID_GENRE) REFERENCES GENRE(ID)" +
            ");";

    private static final String INSERT_TABLE_CATEGORIE =
            "INSERT INTO CATEGORIE (NOM, IMAGE) VALUES " +
                    "('Nintendo', 'nintendo')," +
                    "('Sega', 'sega')," +
                    "('Sony', 'sony')," +
                    "('Microsoft', 'microsoft')," +
                    "('PC', 'pc');";

    private static final String INSERT_TABLE_TYPE =
            "INSERT INTO TYPE (NOM) VALUES" +
                    "('Portable')," +
                    "('Console')," +
                    "('Unknown');";

    private static final String INSERT_TABLE_GENRE =
            "INSERT INTO GENRE (NOM) VALUES" +
                    "('Action')," +
                    "('Fantastique')," +
                    "('RPG');";

    private static final String INSERT_TABLE_SOUS_CATEGORIE =
            "INSERT INTO SOUS_CATEGORIE (NOM, IMAGE, ID_TYPE, ID_CATEGORIE) VALUES" +
                    "('Game Boy & Color','game_boy_color',1,1),"+
                    "('Game Boy Advance','game_boy_advance',1,1)," +
                    "('DS','ds',1,1)," +
                    "('3DS','3ds',1,1)," +
                    "('Nes','nes',2,1)," +
                    "('Super Nintendo','super_nintendo',2,1)," +
                    "('64','64',2,1)," +
                    "('Gamecube','gamecube',2,1)," +
                    "('Wii','wii',2,1)," +
                    "('Wii U','wii_u',2,1)," +

                    "('Game Gear','game_gear',1,2)," +
                    "('Master System','master_system',2,2)," +
                    "('Mega Drive','mega_drive',2,2)," +
                    "('Mega CD','mega_cd',2,2)," +
                    "('32X','32x',2,2)," +
                    "('Saturne','saturne',2,2)," +
                    "('Dreamcast','dreamcast',2,2)," +

                    "('Psp','psp',1,3)," +
                    "('Vita','vita',1,3)," +
                    "('Playstation 1','ps1',2,3)," +
                    "('Playstation 2','ps2',2,3)," +
                    "('Playstation 3','ps3',2,3)," +
                    "('Playstation 4','ps4',2,3)," +

                    "('Xbox','xbox',2,4)," +
                    "('Xbox 360','xbox_360',2,4)," +
                    "('Xbox One','xbox_one',2,4)," +

                    "('1980-89','1980_89',3,5)," +
                    "('1990-99','1990_99',3,5)," +
                    "('2000-09','2000_09',3,5)," +
                    "('2010-19','2010_19',3,5);";


    public SQLiteDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CATEGORIE);
        db.execSQL(CREATE_TABLE_TYPE);
        db.execSQL(CREATE_TABLE_SOUS_CATEGORIE);
        db.execSQL(CREATE_TABLE_GENRE);
        db.execSQL(CREATE_TABLE_GAME);
        db.execSQL(INSERT_TABLE_CATEGORIE);
        db.execSQL(INSERT_TABLE_TYPE);
        db.execSQL(INSERT_TABLE_SOUS_CATEGORIE);
        db.execSQL(INSERT_TABLE_GENRE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "GAME");
        db.execSQL("DROP TABLE IF EXISTS " + "GENRE");
        db.execSQL("DROP TABLE IF EXISTS " + "SOUS_CATEGORIE");
        db.execSQL("DROP TABLE IF EXISTS " + "TYPE");
        db.execSQL("DROP TABLE IF EXISTS " + "CATEGORIE");
        onCreate(db);
    }
}
