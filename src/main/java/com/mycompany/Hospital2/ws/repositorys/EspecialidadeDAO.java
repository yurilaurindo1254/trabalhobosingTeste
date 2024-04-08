package com.mycompany.Hospital2.ws.repositorys;

import com.mycompany.Hospital2.ws.POJO.EspecialidadePOJO;
import com.mycompany.Hospital2.ws.exceptions.AutorizacaoException;
import com.mycompany.Hospital2.ws.exceptions.ValidacaoException;
import com.mycompany.Hospital2.ws.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDAO {

    private static final String INSERT = "INSERT INTO especialidade(id, descricao, ativo) VALUES(?, ?, ?)";
    private static final String FIND_ALL = "SELECT id, descricao, ativo FROM especialidade";
    private static final String FIND_BY_ID = "SELECT id, descricao, ativo FROM especialidade WHERE id = ?";
    private static final String UPDATE = "UPDATE especialidade SET descricao = ?, ativo = ? WHERE id = ?";

    public List<EspecialidadePOJO> findAll() throws SQLException {
        List<EspecialidadePOJO> especialidades = new ArrayList<>();
        
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                EspecialidadePOJO especialidade = new EspecialidadePOJO(
                    rs.getInt("id"),
                    rs.getString("descricao"),
                    rs.getBoolean("ativo")
                );
                especialidades.add(especialidade);
            }
        }
        return especialidades;
    }

    public EspecialidadePOJO findById(int id) throws SQLException {
        EspecialidadePOJO especialidade = null;
        
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    especialidade = new EspecialidadePOJO(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getBoolean("ativo")
                    );
                }
            }
        }
        return especialidade;
    }

    public EspecialidadePOJO insert(EspecialidadePOJO especialidade) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT)) {
            
            pstmt.setInt(1, especialidade.getId());
            pstmt.setString(2, especialidade.getDescricao());
            pstmt.setBoolean(3, especialidade.isAtivo());
            pstmt.executeUpdate();
        }
        return especialidade;
    }

    public void update(EspecialidadePOJO especialidade) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {
            
            pstmt.setString(1, especialidade.getDescricao());
            pstmt.setBoolean(2, especialidade.isAtivo());
            pstmt.setInt(3, especialidade.getId());
            pstmt.executeUpdate();
        }
    }

    public void deactivate(int id) throws SQLException {
    String sql = "UPDATE especialidade SET ativo = false WHERE id = ?";
    try (Connection conn = new ConnectionFactory().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}

}
