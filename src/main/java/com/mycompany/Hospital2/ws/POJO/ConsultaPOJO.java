package com.mycompany.Hospital2.ws.POJO;

import com.mycompany.Hospital2.ws.abstracts.AtivoAbstract;

import java.time.LocalDateTime;

public class ConsultaPOJO extends AtivoAbstract {
    private int id;
    private PacientePOJO paciente;
    private MedicoPOJO medico;
    private LocalDateTime dataHora;

    public ConsultaPOJO() {
    }

   

    public ConsultaPOJO(int id, PacientePOJO paciente, MedicoPOJO medico, LocalDateTime dataHora, boolean ativo) {
        super(ativo);
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
  
}
