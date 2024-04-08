package com.mycompany.Hospital2.ws.services;

import com.mycompany.Hospital2.ws.POJO.MedicoPOJO;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.repositorys.MedicoDAO;
import java.sql.SQLException;
import java.util.List;

public class MedicoServices {
    
    private final MedicoDAO medicoDAO = new MedicoDAO();

    public void validar(MedicoPOJO medico) throws ValidacaoException {
        if (medico == null) {
            throw new ValidacaoException("Médico não informado.");
        }

        if (medico.getPessoa() == null || medico.getPessoa().getNome().isEmpty()) {
            throw new ValidacaoException("Nome do médico é obrigatório.");
        }

        if (medico.getCrm() == null || medico.getCrm().isEmpty()) {
            throw new ValidacaoException("CRM do médico é obrigatório.");
        }
        
        // Aqui você pode adicionar mais validações conforme necessário
    }

    public List<MedicoPOJO> listAllMedicos() throws SQLException {
        return medicoDAO.findAll();
    }

    public MedicoPOJO findByIdMedico(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID do médico é inválido.");
        }
        MedicoPOJO medico = medicoDAO.findById(id);
        if (medico == null) {
            throw new ValidacaoException("Médico com o ID " + id + " não encontrado.");
        }
        return medico;
    }

    public MedicoPOJO insertMedico(MedicoPOJO medico) throws SQLException, ValidacaoException {
        validar(medico);
        medicoDAO.insert(medico);
        return medico;
    }

    public MedicoPOJO updateMedico(MedicoPOJO medico) throws SQLException, ValidacaoException {
        validar(medico);
        medicoDAO.update(medico);
        return medico;
    }

    public void desactivateMedico(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID do médico é inválido para desativação.");
        }
        medicoDAO.deactivate(id);
    }
}
