package com.mycompany.Hospital2.ws.services;

import com.mycompany.Hospital2.ws.POJO.ConsultaPOJO;
import com.mycompany.Hospital2.ws.POJO.MedicoPOJO;
import com.mycompany.Hospital2.ws.POJO.PacientePOJO;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.repositorys.ConsultaDAO;
import com.mycompany.Hospital2.ws.repositorys.MedicoDAO;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ConsultaServices {

    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO(); // Adiciona uma referência ao MedicoDAO

    public ConsultaPOJO insertConsulta(ConsultaPOJO consulta) throws SQLException, ValidacaoException {
    // Primeiro, verifique se um médico foi especificado e está ativo; caso contrário, tente atribuir um aleatoriamente.
    if (consulta.getMedico() == null || consulta.getMedico().getId() == 0) {
        MedicoPOJO medicoDisponivel = assignRandomMedico(consulta.getDataHora());
        if (medicoDisponivel == null || !medicoDisponivel.isAtivo()) {
            throw new ValidacaoException("Não foi possível encontrar um médico disponível e ativo.");
        }
        consulta.setMedico(medicoDisponivel);
    } else if (!consulta.getMedico().isAtivo()) {
        throw new ValidacaoException("O médico especificado é inválido ou inativo.");
    }

    // Continua com a validação agora que um médico válido está garantido
    validarConsulta(consulta, true);

    return consultaDAO.insert(consulta);
}
    
    


    public List<ConsultaPOJO> listAllConsultas() throws SQLException {
        return consultaDAO.findAll();
    }

    public ConsultaPOJO findByIdConsulta(int id) throws SQLException, ValidacaoException {
        ConsultaPOJO consulta = consultaDAO.findById(id);
        if (consulta == null) {
            throw new ValidacaoException("Consulta não encontrada.");
        }
        return consulta;
    }

    public void updateConsulta(ConsultaPOJO consulta) throws SQLException, ValidacaoException {
        validarConsulta(consulta, false);
        consultaDAO.update(consulta);
    }

    public void desactivateConsulta(int id) throws SQLException, ValidacaoException {
        ConsultaPOJO consulta = consultaDAO.findById(id);
        if (consulta == null) {
            throw new ValidacaoException("Consulta não encontrada.");
        }
        consultaDAO.deactivate(id);
    }

    public void cancelarConsulta(int consultaId, String motivoCancelamento) throws SQLException, ValidacaoException {
    if (motivoCancelamento == null || motivoCancelamento.trim().isEmpty()) {
        throw new ValidacaoException("Motivo do cancelamento deve ser informado.");
    }
    ConsultaPOJO consulta = consultaDAO.findById(consultaId);
    if (consulta == null) {
        throw new ValidacaoException("Consulta não encontrada.");
    }
    Calendar calNow = Calendar.getInstance();
    Calendar calConsulta = Calendar.getInstance();
    calConsulta.setTime(consulta.getDataHora());
    calNow.add(Calendar.HOUR, 24);
    if (calConsulta.getTime().before(calNow.getTime())) {
        throw new ValidacaoException("A consulta só pode ser cancelada com 24 horas de antecedência.");
    }
    consultaDAO.cancelarConsulta(consultaId, motivoCancelamento); // Modifique para chamar o método ajustado no DAO
    }

    private void validarConsulta(ConsultaPOJO consulta, boolean isAgendamento) throws ValidacaoException, SQLException {
        if (consulta == null) {
            throw new ValidacaoException("A consulta não pode ser nula.");
        }

        PacientePOJO paciente = consulta.getPaciente();
        MedicoPOJO medico = consulta.getMedico();
        Date dataHoraConsulta = consulta.getDataHora();

        if (paciente == null || paciente.getId() == 0 || !paciente.isAtivo()) {
            throw new ValidacaoException("Paciente inválido ou inativo.");
        }
        
        

        if (dataHoraConsulta == null) {
            throw new ValidacaoException("Data e hora da consulta são obrigatórias.");
        }

        Calendar calConsulta = Calendar.getInstance();
        calConsulta.setTime(dataHoraConsulta);
        Calendar calNow = Calendar.getInstance();

        if (dataHoraConsulta.before(calNow.getTime())) {
            throw new ValidacaoException("A data e hora da consulta devem ser futuras.");
        }

        if (isAgendamento && calNow.getTimeInMillis() + 30 * 60 * 1000 > calConsulta.getTimeInMillis()) {
            throw new ValidacaoException("A consulta deve ser agendada com no mínimo 30 minutos de antecedência.");
        }

        if (calConsulta.get(Calendar.HOUR_OF_DAY) < 7 || calConsulta.get(Calendar.HOUR_OF_DAY) >= 19 || calConsulta.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            throw new ValidacaoException("A consulta deve ser agendada entre 07:00 e 19:00 de segunda a sábado.");
        }

        if (isAgendamento && consultaDAO.existsConsultaNoDia(paciente.getId(), calConsulta.getTime())) {
            throw new ValidacaoException("O paciente já possui uma consulta marcada para este dia.");
        }

        if (isAgendamento && consultaDAO.existsConsultaNoHorario(medico.getId(), dataHoraConsulta)) {
            throw new ValidacaoException("O médico já possui uma consulta marcada para este horário.");
        }
    }

     private MedicoPOJO assignRandomMedico(Date dateTime) throws SQLException, ValidacaoException {
        List<MedicoPOJO> medicosDisponiveis = medicoDAO.findMedicosDisponiveis(dateTime); // Chama o método de MedicoDAO
        if (medicosDisponiveis.isEmpty()) {
            throw new ValidacaoException("Não há médicos disponíveis para o horário escolhido.");
        }
        Random random = new Random();
        return medicosDisponiveis.get(random.nextInt(medicosDisponiveis.size()));
    }
}
