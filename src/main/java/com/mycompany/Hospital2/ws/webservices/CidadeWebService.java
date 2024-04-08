package com.mycompany.Hospital2.ws.webservices;

import com.mycompany.Hospital2.ws.POJO.CidadePOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.interfaces.CidadeInterface;
import com.mycompany.Hospital2.ws.services.CidadeServices;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "com.mycompany.Hospital2.ws.interfaces.CidadeInterface")
public class CidadeWebService implements CidadeInterface {

    private final CidadeServices cidadeService = new CidadeServices();

    @Override
    public CidadePOJO insertCidade(CidadePOJO cidade) throws SQLException, ValidacaoException, AutorizacaoException {
        return cidadeService.insertCidade(cidade);
    }

    @Override
    public List<CidadePOJO> listAllCidades() throws SQLException, ValidacaoException, AutorizacaoException {
        return cidadeService.listAllCidades();
    }

    @Override
    public CidadePOJO findByIdCidade(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        return cidadeService.findByIdCidade(id);
    }

    @Override
    public CidadePOJO updateCidade(CidadePOJO cidade) throws SQLException, ValidacaoException, AutorizacaoException {
        return cidadeService.updateCidade(cidade);
    }

    @Override
    public void desactivateCidade(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        cidadeService.desactivateCidade(id);
    }
}
