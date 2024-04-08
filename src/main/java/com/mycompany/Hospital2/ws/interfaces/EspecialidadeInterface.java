/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Hospital2.ws.interfaces;

import com.mycompany.Hospital2.ws.POJO.EspecialidadePOJO;
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
public interface EspecialidadeInterface {
    
     @WebMethod
    EspecialidadePOJO insertEspecialidade(EspecialidadePOJO especialidade) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    List<EspecialidadePOJO> listAllEspecialidades() throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    EspecialidadePOJO findByIdEspecialidade(int id) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    EspecialidadePOJO updateEspecialidade(EspecialidadePOJO especialidade) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    void desactivateEspecialidade(int id) throws SQLException, ValidacaoException, AutorizacaoException;
}
