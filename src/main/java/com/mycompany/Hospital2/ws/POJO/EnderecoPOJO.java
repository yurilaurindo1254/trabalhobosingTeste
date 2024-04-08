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
public class EnderecoPOJO extends AtivoAbstract {
    private int id;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cep;
    private CidadePOJO cidade;

    public EnderecoPOJO() {
    }
    
    public EnderecoPOJO(int id, String logradouro, int numero, String complemento, String bairro, String cep, CidadePOJO cidade, boolean ativo) {
        super(ativo);
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public CidadePOJO getCidade() {
        return cidade;
    }

    public void setCidade(CidadePOJO cidade) {
        this.cidade = cidade;
    }
   
}
