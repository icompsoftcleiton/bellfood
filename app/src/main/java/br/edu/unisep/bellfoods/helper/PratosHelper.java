package br.edu.unisep.bellfoods.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cleiton on 12/06/2015.
 */
public class PratosHelper extends SQLiteOpenHelper {
    public PratosHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table pratos (");
        sb.append("_id integer,");
        sb.append("curtidas integer,");
        sb.append("nome text,");
        sb.append("descricao text,");
        sb.append("estabelecimento integer,");
        sb.append("latitude text,");
        sb.append("longitude text,");
        sb.append("foto text)");
        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
