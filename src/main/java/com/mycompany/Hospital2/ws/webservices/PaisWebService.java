package com.mycompany.Hospital2.ws.webservices;

import com.mycompany.Hospital2.ws.POJO.PaisPOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.interfaces.PaisInterface;

import com.mycompany.Hospital2.ws.services.PaisServices;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "com.mycompany.Hospital2.ws.interfaces.PaisInterface")
public class PaisWebService implements PaisInterface {

    private final PaisServices paisService = new PaisServices();

    @Override
    public PaisPOJO insertPais(PaisPOJO pais) throws SQLException, ValidacaoException, AutorizacaoException {
        return paisService.insertPais(pais);
    }

    @Override
    public List<PaisPOJO> listAllPaises() throws SQLException, ValidacaoException, AutorizacaoException {
        return paisService.listAllPaises();
    }

    @Override
    public PaisPOJO findByIdPais(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        return paisService.findByIdPais(id);
    }

    @Override
    public PaisPOJO updatePais(PaisPOJO pais) throws SQLException, ValidacaoException, AutorizacaoException {
        return paisService.updatePais(pais);
    }

    @Override
    public void desactivatePais(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        paisService.desactivatePais(id);
    }
}
