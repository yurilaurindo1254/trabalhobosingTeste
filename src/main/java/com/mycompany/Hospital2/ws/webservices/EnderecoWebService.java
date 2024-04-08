package com.mycompany.Hospital2.ws.webservices;

import com.mycompany.Hospital2.ws.POJO.EnderecoPOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.interfaces.EnderecoInterface;
import com.mycompany.Hospital2.ws.services.EnderecoServices;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "com.mycompany.Hospital2.ws.interfaces.EnderecoInterface")
public class EnderecoWebService implements EnderecoInterface {

    private final EnderecoServices enderecoService = new EnderecoServices();

    @Override
    public EnderecoPOJO insertEndereco(EnderecoPOJO endereco) throws SQLException, ValidacaoException, AutorizacaoException {
        return enderecoService.insertEndereco(endereco);
    }

    @Override
    public List<EnderecoPOJO> listAllEnderecos() throws SQLException, ValidacaoException, AutorizacaoException {
        return enderecoService.listAllEnderecos();
    }

    @Override
    public EnderecoPOJO findByIdEndereco(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        return enderecoService.findByIdEndereco(id);
    }

    @Override
    public EnderecoPOJO updateEndereco(EnderecoPOJO endereco) throws SQLException, ValidacaoException, AutorizacaoException {
        return enderecoService.updateEndereco(endereco);
    }

    @Override
    public void desactivateEndereco(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        enderecoService.desactivateEndereco(id);
    }
}
