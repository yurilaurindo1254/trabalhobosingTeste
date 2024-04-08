/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Hospital2.ws.POJO;

import com.mycompany.Hospital2.ws.abstracts.AtivoAbstract;



/**
 *
 * @author yuriz
 */
public class EstadoPOJO extends AtivoAbstract {
    private int id_estado;
    private PaisPOJO pais;
    private String nome;
    private String sigla;

    public EstadoPOJO() {
    }

    public EstadoPOJO(int id_estado, PaisPOJO pais, String nome, String sigla, boolean ativo) {
        super(ativo);
        this.id_estado = id_estado;
        this.pais = pais;
        this.nome = nome;
        this.sigla = sigla;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public PaisPOJO getPais() {
        return pais;
    }

    public void setPais(PaisPOJO pais) {
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
   
}
