/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Hospital2.ws.webservices;

import com.mycompany.Hospital2.ws.POJO.MedicoPOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.interfaces.MedicoInterface;
import com.mycompany.Hospital2.ws.services.MedicoServices;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "com.mycompany.Hospital2.ws.interfaces.MedicoInterface")
public class MedicoWebService implements MedicoInterface {

    private final MedicoServices medicoService = new MedicoServices();

    @Override
    public MedicoPOJO insertMedico(MedicoPOJO medico) throws SQLException, ValidacaoException, AutorizacaoException {
        return medicoService.insertMedico(medico);
    }

    @Override
    public List<MedicoPOJO> listAllMedicos() throws SQLException, ValidacaoException, AutorizacaoException {
        return medicoService.listAllMedicos();
    }

    @Override
    public MedicoPOJO findByIdMedico(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        return medicoService.findByIdMedico(id);
    }

    @Override
    public MedicoPOJO updateMedico(MedicoPOJO medico) throws SQLException, ValidacaoException, AutorizacaoException {
        return medicoService.updateMedico(medico);
    }

    @Override
    public void desactivateMedico(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        medicoService.desactivateMedico(id);
    }
}
