package com.mycompany.Hospital2.ws.services;

import com.mycompany.Hospital2.ws.POJO.EstadoPOJO;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.repositorys.EstadoDAO;
import java.sql.SQLException;
import java.util.List;

public class EstadoServices {
    
    private final EstadoDAO estadoDAO = new EstadoDAO();

    public void validar(EstadoPOJO estado) throws ValidacaoException {
        if (estado == null) {
            throw new ValidacaoException("Estado não informado.");
        }

        if (estado.getNome() == null || estado.getNome().isEmpty()) {
            throw new ValidacaoException("Nome do estado é obrigatório.");
        }

        // Outras validações específicas para Estado
    }

    public List<EstadoPOJO> listAllEstados() throws SQLException {
        return estadoDAO.findAll();
    }

    public EstadoPOJO findByIdEstado(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID do estado é inválido.");
        }
        EstadoPOJO estado = estadoDAO.findById(id);
        if (estado == null) {
            throw new ValidacaoException("Estado com o ID " + id + " não encontrado.");
        }
        return estado;
    }

    public EstadoPOJO insertEstado(EstadoPOJO estado) throws SQLException, ValidacaoException {
        validar(estado);
        estadoDAO.insert(estado);
        return estado;
    }

    public EstadoPOJO updateEstado(EstadoPOJO estado) throws SQLException, ValidacaoException {
        validar(estado);
        estadoDAO.update(estado);
        return estado;
    }

    public void desactivateEstado(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID do estado é inválido para desativação.");
        }
        estadoDAO.deactivate(id);
    }
}
