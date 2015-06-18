package br.edu.unisep.bellfoods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.unisep.bellfoods.adapter.ListaPratosCurtidosAdapter;
import br.edu.unisep.bellfoods.adapter.ListaPratosRecentesAdapter;
import br.edu.unisep.bellfoods.dao.PratosDAO;
import br.edu.unisep.bellfoods.task.ListaPratosTask;
import br.edu.unisep.bellfoods.vo.EstabelecimentoVO;
import br.edu.unisep.bellfoods.vo.PratoVO;


public class MainActivity extends Activity {

    private ListaPratosTask task;

    private List<PratoVO> listaPratoCurtidos;

    private List<PratoVO> listaPratoRecentes;

    private GridView gridRecentes;

    private GridView gridCurtidos;

    private ListaPratosCurtidosAdapter adapterCurtidos;

    private ListaPratosRecentesAdapter adapterRecentes;

    private PratosDAO dao;

    private Cursor cursor;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.activity = this;

        this.gridCurtidos = (GridView) findViewById(R.id.gridViewCurtidos);
        this.listaPratoCurtidos = new ArrayList<PratoVO>();
        this.task = new ListaPratosTask(this, "");
        this.task.execute();
        this.adapterCurtidos = new ListaPratosCurtidosAdapter(this, 0, this.listaPratoCurtidos);
        this.gridCurtidos.setAdapter(this.adapterCurtidos);
        this.gridCurtidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                cursor = dao.consultar(id + 1);
                cursor.move(1);
                // adiciona ou atualiza o registro nos recentes
                PratoVO prato = new PratoVO();
                if (cursor.getCount() > 0) {
                    prato.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    prato.setCurtidas(cursor.getInt(cursor.getColumnIndex("curtidas")));
                    prato.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
                    prato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                    prato.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                    EstabelecimentoVO estabelecimento = new EstabelecimentoVO();
                    estabelecimento.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                    estabelecimento.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                    estabelecimento.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                    prato.setEstabelecimento(estabelecimento);
                    dao.alterar(prato);
                } else {
                    prato.setId(listaPratoCurtidos.get(position).getId());
                    prato.setNome(listaPratoCurtidos.get(position).getNome());
                    prato.setCurtidas(0);
                    prato.setDescricao(listaPratoCurtidos.get(position).getDescricao());
                    prato.setEstabelecimento(listaPratoCurtidos.get(position).getEstabelecimento());
                    prato.setFoto(listaPratoCurtidos.get(position).getFoto());
                    EstabelecimentoVO estabelecimento = new EstabelecimentoVO();
                    estabelecimento.setNome(listaPratoCurtidos.get(position).getEstabelecimento().getNome());
                    estabelecimento.setLatitude(listaPratoCurtidos.get(position).getEstabelecimento().getLatitude());
                    estabelecimento.setLongitude(listaPratoCurtidos.get(position).getEstabelecimento().getLongitude());
                    prato.setEstabelecimento(estabelecimento);
                    dao.inserir(prato);
                }
                Intent intent = new Intent(activity, DetalhesActivity.class);
                intent.putExtra("prato", prato);
                startActivity(intent);
            }
        });

        this.gridRecentes = (GridView) findViewById(R.id.gridViewRecentes);
        this.dao = new PratosDAO(this);
        this.listaPratoRecentes = dao.listar();
        this.adapterRecentes = new ListaPratosRecentesAdapter(this, 0, this.listaPratoRecentes);
        this.gridRecentes.setAdapter(this.adapterRecentes);
        this.gridRecentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                cursor = dao.consultar(id + 1);
                cursor.move(1);
                // adiciona ou atualiza o registro nos recentes
                PratoVO prato = new PratoVO();
                prato.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                prato.setCurtidas(cursor.getInt(cursor.getColumnIndex("curtidas")));
                prato.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
                prato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                prato.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                EstabelecimentoVO estabelecimento = new EstabelecimentoVO();
                estabelecimento.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                estabelecimento.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                estabelecimento.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                prato.setEstabelecimento(estabelecimento);
                dao.alterar(prato);

                Intent intent = new Intent(activity, DetalhesActivity.class);
                intent.putExtra("prato", prato);
                startActivity(intent);
            }
        });
    }

    public void atualizarLista(List<PratoVO> lista) {
        this.listaPratoCurtidos.clear();
        this.listaPratoCurtidos.addAll(lista);
        this.adapterCurtidos.notifyDataSetChanged();
    }

    public void pesquisar(View v) {
        TextView txtPesquisa = (TextView) findViewById(R.id.txtPesquisa);
        String pesquisa = txtPesquisa.getText().toString();
        if (pesquisa.equals("")) {
            AlertDialog msg = new AlertDialog.Builder(this).create();
            msg.setTitle("Erro");
            msg.setMessage("Digite algo para pesquisar!");
            msg.show();
        }
        this.task = new ListaPratosTask(this, pesquisa);
        this.task.execute();
    }

}
