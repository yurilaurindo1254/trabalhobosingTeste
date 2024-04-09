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
    private EspecialidadePOJO especialidade;

    public MedicoPOJO() {
    }

    public MedicoPOJO(int id, PessoaPOJO pessoa, String crm, EspecialidadePOJO especialidade, boolean ativo) {
        super(ativo);
        this.id = id;
        this.pessoa = pessoa;
        this.crm = crm;
        this.especialidade = especialidade;
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

    public EspecialidadePOJO getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadePOJO especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
 
}
