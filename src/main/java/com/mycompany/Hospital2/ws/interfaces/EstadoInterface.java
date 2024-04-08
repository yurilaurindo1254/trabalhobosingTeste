/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Hospital2.ws.interfaces;

import com.mycompany.Hospital2.ws.POJO.EnderecoPOJO;
import com.mycompany.Hospital2.ws.POJO.EstadoPOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuriz
 */
@WebService
public interface EstadoInterface {
    
    @WebMethod
    EstadoPOJO insertEstado(EstadoPOJO estado) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    List<EstadoPOJO> listAllEstados() throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    EstadoPOJO findByIdEstado(int id) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    EstadoPOJO updateEstado(EstadoPOJO estado) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    void desactivateEstado(int id) throws SQLException, ValidacaoException, AutorizacaoException;
}
