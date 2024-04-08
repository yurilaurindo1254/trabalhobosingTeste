package com.mycompany.Hospital2.ws.interfaces;

import com.mycompany.Hospital2.ws.POJO.ConsultaPOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService
public interface ConsultaInterface {
    @WebMethod
    ConsultaPOJO insertConsulta(ConsultaPOJO consulta) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    List<ConsultaPOJO> listAllConsultas() throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    ConsultaPOJO findByIdConsulta(int id) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    void updateConsulta(ConsultaPOJO consulta) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    void desactivateConsulta(int id) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    void cancelarConsulta(int consultaId, String motivoCancelamento) throws SQLException, ValidacaoException, AutorizacaoException;
}
