package br.edu.unisep.bellfoods.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

/**
 * Created by Cleiton on 13/06/2015.
 */
public class ListaPratosRecentesAdapter extends CursorAdapter {

    private LayoutInflater inflater;

    public ListaPratosRecentesAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        try {
            byte[] decodedString = android.util.Base64.decode(cursor.getString(cursor.getColumnIndex("foto")), android.util.Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            ImageView foto = new ImageView(context);
            foto.setImageBitmap(decodedByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
