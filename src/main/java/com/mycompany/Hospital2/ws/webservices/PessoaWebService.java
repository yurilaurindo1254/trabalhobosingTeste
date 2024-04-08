package com.mycompany.Hospital2.ws.webservices;

import com.mycompany.Hospital2.ws.POJO.PessoaPOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.interfaces.PessoaInterface;
import com.mycompany.Hospital2.ws.services.PessoaServices;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "com.mycompany.Hospital2.ws.interfaces.PessoaInterface")
public class PessoaWebService implements PessoaInterface {

    private final PessoaServices pessoaService = new PessoaServices();

    @Override
    public PessoaPOJO insertPessoa(PessoaPOJO pessoa) throws SQLException, ValidacaoException, AutorizacaoException {
        return pessoaService.insertPessoa(pessoa);
    }

    @Override
    public List<PessoaPOJO> listAllPessoas() throws SQLException, ValidacaoException, AutorizacaoException {
        return pessoaService.listAllPessoas();
    }

    @Override
    public PessoaPOJO findByIdPessoa(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        return pessoaService.findByIdPessoa(id);
    }

    @Override
    public PessoaPOJO updatePessoa(PessoaPOJO pessoa) throws SQLException, ValidacaoException, AutorizacaoException {
        return pessoaService.updatePessoa(pessoa);
    }

    @Override
    public void desactivatePessoa(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        pessoaService.desactivatePessoa(id);
    }
}
