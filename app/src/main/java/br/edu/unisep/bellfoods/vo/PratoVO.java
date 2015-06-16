package br.edu.unisep.bellfoods.vo;

import java.io.Serializable;

/**
 * Created by Cleiton on 12/06/2015.
 */
public class PratoVO implements Serializable {

    private Integer id;
    private String foto;
    private String nome;
    private String descricao;
    private Integer curtidas;
    private EstabelecimentoVO estabelecimento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(Integer curtidas) {
        this.curtidas = curtidas;
    }

    public EstabelecimentoVO getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(EstabelecimentoVO estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
}
