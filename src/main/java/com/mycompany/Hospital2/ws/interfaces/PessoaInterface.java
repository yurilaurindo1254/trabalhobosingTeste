/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Hospital2.ws.interfaces;

import com.mycompany.Hospital2.ws.POJO.PaisPOJO;
import com.mycompany.Hospital2.ws.POJO.PessoaPOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebService
public interface PessoaInterface {
    @WebMethod
    PessoaPOJO insertPessoa(PessoaPOJO pessoa) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    List<PessoaPOJO> listAllPessoas() throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    PessoaPOJO findByIdPessoa(int id) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    PessoaPOJO updatePessoa(PessoaPOJO pessoa) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    void desactivatePessoa(int id) throws SQLException, ValidacaoException, AutorizacaoException;
}
