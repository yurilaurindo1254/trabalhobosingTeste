package com.mycompany.Hospital2.ws.webservices;

import com.mycompany.Hospital2.ws.POJO.EspecialidadePOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.interfaces.EspecialidadeInterface;
import com.mycompany.Hospital2.ws.services.EspecialidadeServices;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "com.mycompany.Hospital2.ws.interfaces.EspecialidadeInterface")
public class EspecialidadeWebService implements EspecialidadeInterface {

    private final EspecialidadeServices especialidadeService = new EspecialidadeServices();

    @Override
    public EspecialidadePOJO insertEspecialidade(EspecialidadePOJO especialidade) throws SQLException, ValidacaoException, AutorizacaoException {
        return especialidadeService.insertEspecialidade(especialidade);
    }

    @Override
    public List<EspecialidadePOJO> listAllEspecialidades() throws SQLException, ValidacaoException, AutorizacaoException {
        return especialidadeService.listAllEspecialidades();
    }

    @Override
    public EspecialidadePOJO findByIdEspecialidade(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        return especialidadeService.findByIdEspecialidade(id);
    }

    @Override
    public EspecialidadePOJO updateEspecialidade(EspecialidadePOJO especialidade) throws SQLException, ValidacaoException, AutorizacaoException {
        return especialidadeService.updateEspecialidade(especialidade);
    }

    @Override
    public void desactivateEspecialidade(int id) throws SQLException, ValidacaoException, AutorizacaoException {
        especialidadeService.desactivateEspecialidade(id);
    }
}
