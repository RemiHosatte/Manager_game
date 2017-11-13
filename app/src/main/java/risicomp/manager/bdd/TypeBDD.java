package risicomp.manager.bdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import risicomp.manager.classes.Type;
/**
 * Created by PC on 24/10/2017.
 */



public class TypeBDD  extends SQLiteDataBaseHelper{
    // Database Name
    private static final String TABLE_TYPE = "TYPE";
    private static final String COL_ID = "ID";
    private static final String COL_NOM = "NOM";

    public TypeBDD(Context context) {
        super(context);
    }

    public Type getType(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TYPE, new String[]{COL_ID,
                        COL_NOM}, COL_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Type type = new Type(cursor.getInt(0), cursor.getString(1));
        db.close();
        return type;
    }
}
