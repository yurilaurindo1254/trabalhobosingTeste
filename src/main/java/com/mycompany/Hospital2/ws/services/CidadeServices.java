package com.mycompany.Hospital2.ws.services;

import com.mycompany.Hospital2.ws.POJO.CidadePOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.repositorys.CidadeDAO;
import java.sql.SQLException;
import java.util.List;

public class CidadeServices {
    
    private final CidadeDAO cidadeDAO = new CidadeDAO();

    public void validar(CidadePOJO cidade) throws ValidacaoException {
        if (cidade == null) {
            throw new ValidacaoException("Cidade não informada.");
        }

        if (cidade.getNome() == null || cidade.getNome().isEmpty()) {
            throw new ValidacaoException("Nome da cidade é obrigatório.");
        }

        if (cidade.getEstado() == null) {
            throw new ValidacaoException("Estado da cidade é obrigatório.");
        }

        // Outras validações podem ser adicionadas aqui
    }

    public List<CidadePOJO> listAllCidades() throws SQLException {
        return cidadeDAO.findAll();
    }

    public CidadePOJO findByIdCidade(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID da cidade é inválido.");
        }
        CidadePOJO cidade = cidadeDAO.findById(id);
        if (cidade == null) {
            throw new ValidacaoException("Cidade com o ID " + id + " não encontrada.");
        }
        return cidade;
    }

    public CidadePOJO insertCidade(CidadePOJO cidade) throws SQLException, ValidacaoException {
        validar(cidade);
        cidadeDAO.insert(cidade);
        return cidade;
    }

    public CidadePOJO updateCidade(CidadePOJO cidade) throws SQLException, ValidacaoException {
        validar(cidade);
        cidadeDAO.update(cidade);
        return cidade;
    }

    public void desactivateCidade(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID da cidade é inválido para desativação.");
        }
        cidadeDAO.deactivate(id);
    }

}
