package com.mycompany.Hospital2.ws.services;

import com.mycompany.Hospital2.ws.POJO.EnderecoPOJO;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.repositorys.EnderecoDAO;
import java.sql.SQLException;
import java.util.List;

public class EnderecoServices {
    
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();

    public void validar(EnderecoPOJO endereco) throws ValidacaoException {
        if (endereco == null) {
            throw new ValidacaoException("Endereço não informado.");
        }

        if (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty()) {
            throw new ValidacaoException("Logradouro é obrigatório.");
        }

        if (endereco.getCidade() == null) {
            throw new ValidacaoException("Cidade é obrigatória.");
        }

        // Adicionar mais validações conforme necessário
    }

    public List<EnderecoPOJO> listAllEnderecos() throws SQLException {
        return enderecoDAO.findAll();
    }

    public EnderecoPOJO findByIdEndereco(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID do endereço é inválido.");
        }

        EnderecoPOJO endereco = enderecoDAO.findById(id);
        if (endereco == null) {
            throw new ValidacaoException("Endereço com o ID " + id + " não encontrado.");
        }
        return endereco;
    }

    public EnderecoPOJO insertEndereco(EnderecoPOJO endereco) throws SQLException, ValidacaoException {
        validar(endereco);
        enderecoDAO.insert(endereco);
        return endereco;
    }

    public EnderecoPOJO updateEndereco(EnderecoPOJO endereco) throws SQLException, ValidacaoException {
        validar(endereco);
        enderecoDAO.update(endereco);
        return endereco;
    }

    public void desactivateEndereco(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID do endereço é inválido para desativação.");
        }
        enderecoDAO.deactivate(id);
    }
}
