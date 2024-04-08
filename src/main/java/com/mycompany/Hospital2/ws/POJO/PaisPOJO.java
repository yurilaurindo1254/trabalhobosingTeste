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
public class PaisPOJO extends AtivoAbstract {
    private int id_pais;
    private String nome;
    private String sigla;

    public PaisPOJO() {
    }
    
    

    public PaisPOJO(int id_pais, String nome, String sigla, boolean ativo) {
        super(ativo);
        this.id_pais = id_pais;
        this.nome = nome;
        this.sigla = sigla;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
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
