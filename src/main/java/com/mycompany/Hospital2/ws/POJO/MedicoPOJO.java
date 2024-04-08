package com.mycompany.Hospital2.ws.POJO;
import com.mycompany.Hospital2.ws.abstracts.AtivoAbstract;

/**
 *
 * @author yuriz
 */
public class MedicoPOJO extends AtivoAbstract {
    
    private int id;
    private PessoaPOJO pessoa;
    private String crm;

    public MedicoPOJO() {
    }

    public MedicoPOJO(int id, PessoaPOJO pessoa, String crm, boolean ativo) {
        super(ativo);
        this.id = id;
        this.pessoa = pessoa;
        this.crm = crm;
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
 
}
