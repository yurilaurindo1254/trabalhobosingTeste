package com.mycompany.Hospital2.ws.services;

import com.mycompany.Hospital2.ws.POJO.PessoaPOJO;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;

import com.mycompany.Hospital2.ws.repositorys.PessoaDAO;
import java.sql.SQLException;
import java.util.List;

public class PessoaServices {
    
    private final PessoaDAO pessoaDAO = new PessoaDAO();

    public void validar(PessoaPOJO pessoa) throws ValidacaoException {
        if (pessoa == null) {
            throw new ValidacaoException("Pessoa não informada.");
        }

        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new ValidacaoException("Nome da pessoa é obrigatório.");
        }

        // Outras validações específicas para Pessoa
    }

    public List<PessoaPOJO> listAllPessoas() throws SQLException {
        return pessoaDAO.findAll();
    }

    public PessoaPOJO findByIdPessoa(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID da pessoa é inválido.");
        }
        PessoaPOJO pessoa = pessoaDAO.findById(id);
        if (pessoa == null) {
            throw new ValidacaoException("Pessoa com o ID " + id + " não encontrada.");
        }
        return pessoa;
    }

    public PessoaPOJO insertPessoa(PessoaPOJO pessoa) throws SQLException, ValidacaoException {
        validar(pessoa);
        pessoaDAO.insert(pessoa);
        return pessoa;
    }

    public PessoaPOJO updatePessoa(PessoaPOJO pessoa) throws SQLException, ValidacaoException {
        validar(pessoa);
        pessoaDAO.update(pessoa);
        return pessoa;
    }

    public void desactivatePessoa(int id) throws SQLException, ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("ID da pessoa é inválido para desativação.");
        }
        pessoaDAO.deactivate(id);
    }
}
