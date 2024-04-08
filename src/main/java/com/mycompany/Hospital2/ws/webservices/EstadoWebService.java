package com.mycompany.Hospital2.ws.webservices;

import com.mycompany.Hospital2.ws.POJO.EstadoPOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.interfaces.EstadoInterface;
import com.mycompany.Hospital2.ws.services.EstadoServices;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "com.mycompany.Hospital2.ws.interfaces.EstadoInterface")
public class EstadoWebService implements EstadoInterface {

    private final EstadoServices estadoService = new EstadoServices();

    @Override
    public EstadoPOJO insertEstado(EstadoPOJO estado) throws SQLException, ValidacaoException, AutorizacaoException {
        return estadoService.insertEstado(estado);
    }

    @Override
    public List<EstadoPOJO> listAllEstados() throws SQLException, ValidacaoException, AutorizacaoException {
        return estadoService.listAllEstados();
    }

    @Override
    public EstadoPOJO findByIdEstado(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        return estadoService.findByIdEstado(id);
    }

    @Override
    public EstadoPOJO updateEstado(EstadoPOJO estado) throws SQLException, ValidacaoException, AutorizacaoException {
        return estadoService.updateEstado(estado);
    }

    @Override
    public void desactivateEstado(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        estadoService.desactivateEstado(id);
    }
}
