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
public class EspecialidadePOJO extends AtivoAbstract {
    
    private int id;
    private String descricao;

    public EspecialidadePOJO() {
    }

    public EspecialidadePOJO(int id, String descricao, boolean ativo) {
        super(ativo);
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    
}
