package com.mycompany.Hospital2.ws.repositorys;

import com.mycompany.Hospital2.ws.POJO.CidadePOJO;
import com.mycompany.Hospital2.ws.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {

    private static final String INSERT = "INSERT INTO cidade (nome, estado_id, ativo) VALUES (?, ?, ?)";
    private static final String FIND_ALL = "SELECT id, nome, estado_id, ativo FROM cidade WHERE ativo = true";
    private static final String FIND_BY_ID = "SELECT id, nome, estado_id, ativo FROM cidade WHERE id = ? AND ativo = true";
    private static final String UPDATE = "UPDATE cidade SET nome = ?, estado_id = ? WHERE id = ?";
    private static final String DEACTIVATE = "UPDATE cidade SET ativo = false WHERE id = ?";

    public List<CidadePOJO> findAll() throws SQLException {
        List<CidadePOJO> cidades = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                CidadePOJO cidade = new CidadePOJO();
                cidade.setId_cidade(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
                cidade.setEstado(new EstadoDAO().findById(rs.getInt("estado_id")));
                cidade.setAtivo(rs.getBoolean("ativo"));
                cidades.add(cidade);
            }
        }
        return cidades;
    }

    public CidadePOJO findById(int id) throws SQLException {
        CidadePOJO cidade = null;
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cidade = new CidadePOJO();
                    cidade.setId_cidade(rs.getInt("id"));
                    cidade.setNome(rs.getString("nome"));
                    cidade.setEstado(new EstadoDAO().findById(rs.getInt("estado_id")));
                    cidade.setAtivo(rs.getBoolean("ativo"));
                }
            }
        }
        return cidade;
    }

    public void insert(CidadePOJO cidade) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT)) {
            pstmt.setString(1, cidade.getNome());
            pstmt.setInt(2, cidade.getEstado().getId_estado());
            pstmt.setBoolean(3, true);
            pstmt.executeUpdate();
        }
    }

    public void update(CidadePOJO cidade) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {
            pstmt.setString(1, cidade.getNome());
            pstmt.setInt(2, cidade.getEstado().getId_estado());
            pstmt.setInt(3, cidade.getId_cidade());
            pstmt.executeUpdate();
        }
    }

    public void deactivate(int id) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DEACTIVATE)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

}
