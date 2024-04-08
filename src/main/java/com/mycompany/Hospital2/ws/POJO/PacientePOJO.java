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
public class PacientePOJO extends AtivoAbstract {
    private int id;
    private PessoaPOJO pessoa;

    public PacientePOJO() {
    }

    

    public PacientePOJO(int id, PessoaPOJO pessoa, boolean ativo) {
        super(ativo);
        this.id = id;
        this.pessoa = pessoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PessoaPOJO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaPOJO pessoa) {
        this.pessoa = pessoa;
    }
   
}
