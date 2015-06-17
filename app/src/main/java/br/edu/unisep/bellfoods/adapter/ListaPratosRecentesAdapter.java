package br.edu.unisep.bellfoods.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import br.edu.unisep.bellfoods.R;
import br.edu.unisep.bellfoods.vo.PratoVO;

/**
 * Created by Cleiton on 13/06/2015.
 */
public class ListaPratosRecentesAdapter extends ArrayAdapter<PratoVO> {

    private LayoutInflater inflater;
    private List<PratoVO> listaPratos;

    public ListaPratosRecentesAdapter(Context context, int resource, List<PratoVO> objects) {
        super(context, resource, objects);

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaPratos = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.item_lista, null);

            PratoVO prato = this.listaPratos.get(position);

            TextView text = (TextView) view.findViewById(R.id.text);
            text.setText(prato.getNome());

            byte[] decodedString = android.util.Base64.decode(prato.getFoto(), android.util.Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            ImageView picture = (ImageView) view.findViewById(R.id.picture);
            picture.setImageBitmap(decodedByte);

            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
