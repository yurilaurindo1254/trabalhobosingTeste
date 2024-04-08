package com.mycompany.Hospital2.ws.abstracts;

public abstract class AtivoAbstract {
    protected boolean ativo;  // Tornando-o protected para acesso nas subclasses

    public AtivoAbstract() {
    }

    public AtivoAbstract(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}