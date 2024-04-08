package com.mycompany.Hospital2.ws.webservices;

import com.mycompany.Hospital2.ws.POJO.ConsultaPOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.interfaces.ConsultaInterface;
import com.mycompany.Hospital2.ws.services.ConsultaServices;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "com.mycompany.Hospital2.ws.interfaces.ConsultaInterface")
public class ConsultaWebService implements ConsultaInterface {
    
    private final ConsultaServices consultaService = new ConsultaServices();

    @Override
    public ConsultaPOJO insertConsulta(ConsultaPOJO consulta) throws SQLException, ValidacaoException, AutorizacaoException {
        return consultaService.insertConsulta(consulta);
    }

    @Override
    public List<ConsultaPOJO> listAllConsultas() throws SQLException, ValidacaoException, AutorizacaoException {
        return consultaService.listAllConsultas();
    }

    @Override
    public ConsultaPOJO findByIdConsulta(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        return consultaService.findByIdConsulta(id);
    }

    @Override
    public void updateConsulta(ConsultaPOJO consulta) throws SQLException, ValidacaoException, AutorizacaoException {
        consultaService.updateConsulta(consulta);
    }

    @Override
    public void desactivateConsulta(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        consultaService.desactivateConsulta(id);
    }

    @Override
    public void cancelarConsulta(int consultaId, String motivoCancelamento) throws SQLException, ValidacaoException, AutorizacaoException {
        consultaService.cancelarConsulta(consultaId, motivoCancelamento);
    }
}
