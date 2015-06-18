package br.edu.unisep.bellfoods;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.unisep.bellfoods.task.ListaPratosTask;
import br.edu.unisep.bellfoods.task.QualificarPratoTask;
import br.edu.unisep.bellfoods.vo.PratoVO;


public class DetalhesActivity extends Activity {

    private PratoVO prato;

    private ImageView foto;

    private TextView txtPrato;

    private TextView txtEstabelecimento;

    private TextView txtDescricao;

    private Button btnCurtir;

    private Button btnDescurtir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        foto = (ImageView) findViewById(R.id.foto);
        txtPrato = (TextView) findViewById(R.id.txtPrato);
        txtEstabelecimento = (TextView) findViewById(R.id.txtEstabelecimento);
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        btnCurtir = (Button) findViewById(R.id.btnCurtir);
        btnDescurtir = (Button) findViewById(R.id.btnDescurtir);

        Intent intent = getIntent();

        prato = (PratoVO) intent.getSerializableExtra("prato");

        byte[] decodedString = android.util.Base64.decode(prato.getFoto(), android.util.Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        foto.setImageBitmap(decodedByte);

        txtPrato.setText(prato.getNome());
        txtEstabelecimento.setText(prato.getEstabelecimento().getNome());
        txtDescricao.setText(prato.getDescricao());
    }

    public void verMapa(View v) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("prato", prato);
        startActivity(intent);
    }

    public void curtir(View v) {
        QualificarPratoTask task = new QualificarPratoTask("curtir", prato.getId());
        task.execute();
    }

    public void descurtir(View v)  {
        QualificarPratoTask task = new QualificarPratoTask("descurtir", prato.getId());
        task.execute();
    }

}
