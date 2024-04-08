package com.mycompany.Hospital2.ws.POJO;

import com.mycompany.Hospital2.ws.abstracts.AtivoAbstract;

public class PessoaPOJO extends AtivoAbstract {
    private int id;
    private String nome;
    private String email;
    private String numero;
    private String cpf;
    private EnderecoPOJO endereco;

    public PessoaPOJO() {
    }

    public PessoaPOJO(int id, String nome, String email, String numero, String cpf, EnderecoPOJO endereco, boolean ativo) {
        super(ativo);
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.numero = numero;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnderecoPOJO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoPOJO endereco) {
        this.endereco = endereco;
    }    
}
