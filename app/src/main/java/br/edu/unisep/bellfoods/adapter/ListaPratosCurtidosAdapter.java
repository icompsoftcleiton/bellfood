package br.edu.unisep.bellfoods.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import br.edu.unisep.bellfoods.vo.PratoVO;

/**
 * Created by Cleiton on 13/06/2015.
 */
public class ListaPratosCurtidosAdapter extends ArrayAdapter<PratoVO> {
    private List<PratoVO> lista;
    private Context context;

    public ListaPratosCurtidosAdapter(Context context, int resource, List<PratoVO> objects) {
        super(context, resource, objects);

        this.lista = objects;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            PratoVO prato = lista.get(position);
            byte[] decodedString = android.util.Base64.decode(prato.getFoto(), android.util.Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            ImageView foto = new ImageView(context);
            foto.setImageBitmap(decodedByte);
            return foto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
