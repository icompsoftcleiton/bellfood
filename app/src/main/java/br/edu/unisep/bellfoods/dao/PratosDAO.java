package br.edu.unisep.bellfoods.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.unisep.bellfoods.helper.PratosHelper;
import br.edu.unisep.bellfoods.vo.EstabelecimentoVO;
import br.edu.unisep.bellfoods.vo.PratoVO;

/**
 * Created by Cleiton on 12/06/2015.
 */
public class PratosDAO {

    private PratosHelper helper;

    public PratosDAO(Context context) {
        helper = new PratosHelper(context, "pratos", null, 5);
    }

    public void inserir(PratoVO prato) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("_id", prato.getId());
        valores.put("curtidas", prato.getCurtidas());
        valores.put("nome", prato.getNome());
        valores.put("descricao", prato.getDescricao());
        valores.put("estabelecimento", prato.getEstabelecimento().getId());
        valores.put("foto", prato.getFoto());
        valores.put("latitude", prato.getEstabelecimento().getLatitude());
        valores.put("longitude", prato.getEstabelecimento().getLongitude());
        db.insert("pratos", null, valores);
        db.close();

    }

    public void alterar(PratoVO prato) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] valoresWhere = {String.valueOf(prato.getId())};
        ContentValues valores = new ContentValues();
        valores.put("curtidas", prato.getCurtidas() + 1);
        db.update("pratos", valores, "_id = ?", valoresWhere);
        db.close();
    }

    public List<PratoVO> listar() {

        SQLiteDatabase db = helper.getReadableDatabase();
        String[] colunas = {"_id", "curtidas", "nome", "descricao", "estabelecimento", "foto", "latitude", "longitude"};
        Cursor cursor = db.query("pratos", colunas, null, null, null, null, "curtidas");
        List<PratoVO> lista = new ArrayList<PratoVO>();
        while (cursor.moveToNext()) {
            PratoVO prato = new PratoVO();
            prato.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            prato.setCurtidas(cursor.getInt(cursor.getColumnIndex("curtidas")));
            prato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            prato.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            EstabelecimentoVO estabelecimento = new EstabelecimentoVO();
            estabelecimento.setId(cursor.getInt(cursor.getColumnIndex("estabelecimento")));
            estabelecimento.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            estabelecimento.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
            estabelecimento.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
            prato.setEstabelecimento(estabelecimento);
            prato.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
            lista.add(prato);
        }
        return lista;

    }

    public Cursor consultar(long id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] colunas = {"_id", "curtidas", "nome", "descricao", "estabelecimento", "foto", "latitude", "longitude"};
        String[] valoresWhere = {String.valueOf(id)};
        Cursor cursor = db.query("pratos", colunas, "_id = ?", valoresWhere, null, null, null);
        return cursor;
    }

}
