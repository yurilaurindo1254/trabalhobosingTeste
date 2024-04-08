/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Hospital2.ws.interfaces;

import com.mycompany.Hospital2.ws.POJO.MedicoPOJO;
import com.mycompany.Hospital2.ws.POJO.PacientePOJO;
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
public interface PacienteInterface {
     
    @WebMethod
    PacientePOJO insertPaciente(PacientePOJO paciente) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    List<PacientePOJO> listAllPacientes() throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    PacientePOJO findByIdPaciente(int id) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    PacientePOJO updatePaciente(PacientePOJO paciente) throws SQLException, ValidacaoException, AutorizacaoException;

    @WebMethod
    void desactivatePaciente(int id) throws SQLException, ValidacaoException, AutorizacaoException;
}

