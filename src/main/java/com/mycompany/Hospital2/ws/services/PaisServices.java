package com.mycompany.Hospital2.ws.services;

import com.mycompany.Hospital2.ws.POJO.PaisPOJO;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.repositorys.PaisDAO;
import java.sql.SQLException;
import java.util.List;

public class PaisServices {
    
    private final PaisDAO paisDAO = new PaisDAO();

    public void validar(PaisPOJO pais) throws ValidacaoException {
        if (pais == null) {
            throw new ValidacaoException("País não informado.");
        }

        if (pais.getNome() == null || pais.getNome().isEmpty()) {
            throw new ValidacaoException("Nome do país é obrigatório.");
        }

        // Adicione outras validações específicas para País aqui
    }

    public List<PaisPOJO> listAllPaises() throws SQLException {
        return paisDAO.findAll();
    }

    public PaisPOJO findByIdPais(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID do país é inválido.");
        }
        PaisPOJO pais = paisDAO.findById(id);
        if (pais == null) {
            throw new ValidacaoException("País com o ID " + id + " não encontrado.");
        }
        return pais;
    }

    public PaisPOJO insertPais(PaisPOJO pais) throws SQLException, ValidacaoException {
        validar(pais);
        paisDAO.insert(pais);
        return pais;
    }

    public PaisPOJO updatePais(PaisPOJO pais) throws SQLException, ValidacaoException {
        validar(pais);
        paisDAO.update(pais);
        return pais;
    }

    public void desactivatePais(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID do país é inválido para desativação.");
        }
        paisDAO.deactivate(id);
    }
}
