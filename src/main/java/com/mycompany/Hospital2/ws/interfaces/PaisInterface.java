/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Hospital2.ws.interfaces;

import com.mycompany.Hospital2.ws.POJO.EnderecoPOJO;
import com.mycompany.Hospital2.ws.POJO.PaisPOJO;
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
public interface PaisInterface {
   @WebMethod
    PaisPOJO insertPais(PaisPOJO pais) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    List<PaisPOJO> listAllPaises() throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    PaisPOJO findByIdPais(int id) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    PaisPOJO updatePais(PaisPOJO pais) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    void desactivatePais(int id) throws SQLException, ValidacaoException, AutorizacaoException;
}
