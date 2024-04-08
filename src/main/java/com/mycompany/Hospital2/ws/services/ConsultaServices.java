package com.mycompany.Hospital2.ws.services;

import com.mycompany.Hospital2.ws.POJO.ConsultaPOJO;
import com.mycompany.Hospital2.ws.POJO.MedicoPOJO;
import com.mycompany.Hospital2.ws.POJO.PacientePOJO;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.repositorys.ConsultaDAO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class ConsultaServices {

    private final ConsultaDAO consultaDAO = new ConsultaDAO();

    public ConsultaPOJO insertConsulta(ConsultaPOJO consulta) throws SQLException, ValidacaoException {
        validarConsulta(consulta, true);
        if (consulta.getMedico() == null) {
            MedicoPOJO medicoDisponivel = assignRandomMedico(consulta.getDataHora());
            consulta.setMedico(medicoDisponivel);
        }
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
        if (LocalDateTime.now().isAfter(consulta.getDataHora().minusHours(24))) {
            throw new ValidacaoException("A consulta só pode ser cancelada com 24 horas de antecedência.");
        }
        consultaDAO.deactivate(consultaId);
    }

    private void validarConsulta(ConsultaPOJO consulta, boolean isAgendamento) throws ValidacaoException, SQLException {
    if (consulta == null) {
        throw new ValidacaoException("A consulta não pode ser nula.");
    }

    if (consulta.getPaciente() == null || consulta.getPaciente().getId() == 0) {
        throw new ValidacaoException("Paciente inválido.");
    }

    if (consulta.getMedico() == null || consulta.getMedico().getId() == 0) {
        throw new ValidacaoException("Médico inválido.");
    }

    if (consulta.getDataHora() == null) {
        throw new ValidacaoException("Data e hora da consulta são obrigatórias.");
    }

    LocalDateTime now = LocalDateTime.now();
    if (consulta.getDataHora().isBefore(now)) {
        throw new ValidacaoException("A data e hora da consulta devem ser futuras.");
    }

    if (isAgendamento && consulta.getDataHora().isBefore(now.plusMinutes(30))) {
        throw new ValidacaoException("A consulta deve ser agendada com no mínimo 30 minutos de antecedência.");
    }

    if (consulta.getDataHora().toLocalTime().isBefore(LocalTime.of(7, 0)) || consulta.getDataHora().toLocalTime().isAfter(LocalTime.of(19, 0))) {
        throw new ValidacaoException("A consulta deve ser agendada entre 07:00 e 19:00.");
    }

    // Verifica se o paciente já tem consulta no mesmo dia
    if (isAgendamento && consultaDAO.existsConsultaNoDia(consulta.getPaciente().getId(), consulta.getDataHora().toLocalDate())) {
        throw new ValidacaoException("O paciente já possui uma consulta marcada para este dia.");
    }

    // Verifica se o médico já tem consulta no mesmo horário
    if (isAgendamento && consultaDAO.existsConsultaNoHorario(consulta.getMedico().getId(), consulta.getDataHora())) {
        throw new ValidacaoException("O médico já possui uma consulta marcada para este horário.");
    }
}


    private MedicoPOJO assignRandomMedico(LocalDateTime dateTime) throws SQLException, ValidacaoException {
        List<MedicoPOJO> medicosDisponiveis = consultaDAO.findMedicosDisponiveis(dateTime.toLocalDate());
        if (medicosDisponiveis.isEmpty()) {
            throw new ValidacaoException("Não há médicos disponíveis para o horário escolhido.");
        }
        Random random = new Random();
        return medicosDisponiveis.get(random.nextInt(medicosDisponiveis.size()));
    }
}
