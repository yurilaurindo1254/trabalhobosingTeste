/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Hospital2.ws.interfaces;

import com.mycompany.Hospital2.ws.POJO.CidadePOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yuriz
 */
@WebService
public interface CidadeInterface {
    
   @WebMethod
    CidadePOJO insertCidade(CidadePOJO cidade) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    List<CidadePOJO> listAllCidades() throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    CidadePOJO findByIdCidade(int id) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    CidadePOJO updateCidade(CidadePOJO cidade) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    void desactivateCidade(int id) throws SQLException, ValidacaoException, AutorizacaoException;
}
