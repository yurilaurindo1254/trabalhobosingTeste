package com.mycompany.Hospital2.ws.POJO;

import com.mycompany.Hospital2.ws.abstracts.AtivoAbstract;

import java.time.LocalDateTime;
import java.util.Date;

public class ConsultaPOJO extends AtivoAbstract {
    private int id;
    private PacientePOJO paciente;
    private MedicoPOJO medico;
    private Date dataHora;

    public ConsultaPOJO() {
    }

    public ConsultaPOJO(int id, PacientePOJO paciente, MedicoPOJO medico, Date dataHora) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PacientePOJO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacientePOJO paciente) {
        this.paciente = paciente;
    }

    public MedicoPOJO getMedico() {
        return medico;
    }

    public void setMedico(MedicoPOJO medico) {
        this.medico = medico;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    
  
}
