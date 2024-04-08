package com.mycompany.Hospital2.ws.services;

import com.mycompany.Hospital2.ws.POJO.EspecialidadePOJO;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.repositorys.EspecialidadeDAO;
import java.sql.SQLException;
import java.util.List;

public class EspecialidadeServices {
    
    private final EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();

    public void validar(EspecialidadePOJO especialidade) throws ValidacaoException {
        if (especialidade == null) {
            throw new ValidacaoException("Especialidade não informada.");
        }

        if (especialidade.getDescricao() == null || especialidade.getDescricao().isEmpty()) {
            throw new ValidacaoException("Descrição da especialidade é obrigatória.");
        }

        // Adicione outras validações específicas para Especialidade aqui
    }

    public List<EspecialidadePOJO> listAllEspecialidades() throws SQLException {
        return especialidadeDAO.findAll();
    }

    public EspecialidadePOJO findByIdEspecialidade(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID da especialidade é inválido.");
        }
        EspecialidadePOJO especialidade = especialidadeDAO.findById(id);
        if (especialidade == null) {
            throw new ValidacaoException("Especialidade com o ID " + id + " não encontrada.");
        }
        return especialidade;
    }

    public EspecialidadePOJO insertEspecialidade(EspecialidadePOJO especialidade) throws SQLException, ValidacaoException {
        validar(especialidade);
        especialidadeDAO.insert(especialidade);
        return especialidade;
    }

    public EspecialidadePOJO updateEspecialidade(EspecialidadePOJO especialidade) throws SQLException, ValidacaoException {
        validar(especialidade);
        especialidadeDAO.update(especialidade);
        return especialidade;
    }

    public void desactivateEspecialidade(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID da especialidade é inválido para desativação.");
        }
        
    }
    
}
    
