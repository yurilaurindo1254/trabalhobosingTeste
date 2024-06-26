package com.mycompany.Hospital2.ws.services;

import com.mycompany.Hospital2.ws.POJO.PacientePOJO;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.repositorys.PacienteDAO;
import java.sql.SQLException;
import java.util.List;

public class PacienteServices {
    
    private final PacienteDAO pacienteDAO = new PacienteDAO();

    public void validar(PacientePOJO paciente) throws ValidacaoException {
        if (paciente == null) {
            throw new ValidacaoException("Paciente não informado.");
        }

        if (paciente.getPessoa() == null || paciente.getPessoa().getNome().isEmpty()) {
            throw new ValidacaoException("Nome do paciente é obrigatório.");
        }
        
        // Adicione mais validações conforme necessário
    }

    public List<PacientePOJO> listAllPacientes() throws SQLException {
        return pacienteDAO.findAll();
    }

    public PacientePOJO findByIdPaciente(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID do paciente é inválido.");
        }
        PacientePOJO paciente = pacienteDAO.findById(id);
        if (paciente == null) {
            throw new ValidacaoException("Paciente com o ID " + id + " não encontrado.");
        }
        return paciente;
    }

    public PacientePOJO insertPaciente(PacientePOJO paciente) throws SQLException, ValidacaoException {
        validar(paciente);
        pacienteDAO.insert(paciente);
        return paciente;
    }

    public PacientePOJO updatePaciente(PacientePOJO paciente) throws SQLException, ValidacaoException {
        validar(paciente);
        pacienteDAO.update(paciente);
        return paciente;
    }

    public void desactivatePaciente(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID do paciente é inválido para desativação.");
        }
        pacienteDAO.deactivate(id);
    }
}
