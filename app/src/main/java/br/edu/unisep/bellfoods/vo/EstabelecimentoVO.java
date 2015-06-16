package br.edu.unisep.bellfoods.vo;

import java.io.Serializable;

/**
 * Created by Cleiton on 12/06/2015.
 */
public class EstabelecimentoVO implements Serializable {

    private Integer id;
    private String nome;
    private String latitude;
    private String longitude;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
