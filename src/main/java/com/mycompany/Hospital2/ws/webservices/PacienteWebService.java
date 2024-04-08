package com.mycompany.Hospital2.ws.webservices;

import com.mycompany.Hospital2.ws.POJO.PacientePOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.interfaces.PacienteInterface;
import com.mycompany.Hospital2.ws.services.PacienteServices;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "com.mycompany.Hospital2.ws.interfaces.PacienteInterface")
public class PacienteWebService implements PacienteInterface {

    private final PacienteServices pacienteService = new PacienteServices();

    @Override
    public PacientePOJO insertPaciente(PacientePOJO paciente) throws SQLException, ValidacaoException, AutorizacaoException {
        return pacienteService.insertPaciente(paciente);
    }

    @Override
    public List<PacientePOJO> listAllPacientes() throws SQLException, ValidacaoException, AutorizacaoException {
        return pacienteService.listAllPacientes();
    }

    @Override
    public PacientePOJO findByIdPaciente(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        return pacienteService.findByIdPaciente(id);
    }

    @Override
    public PacientePOJO updatePaciente(PacientePOJO paciente) throws SQLException, ValidacaoException, AutorizacaoException {
        return pacienteService.updatePaciente(paciente);
    }

    @Override
    public void desactivatePaciente(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        pacienteService.desactivatePaciente(id);
    }
}
