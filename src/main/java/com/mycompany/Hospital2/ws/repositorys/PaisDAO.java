package com.mycompany.Hospital2.ws.repositorys;

import com.mycompany.Hospital2.ws.POJO.PaisPOJO;
import com.mycompany.Hospital2.ws.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO {

    private static final String INSERT = "INSERT INTO pais (nome, sigla, ativo) VALUES (?, ?, true)";
    private static final String FIND_ALL = "SELECT id, nome, sigla FROM pais WHERE ativo = true";
    private static final String FIND_BY_ID = "SELECT id, nome, sigla FROM pais WHERE id = ? AND ativo = true";
    private static final String UPDATE = "UPDATE pais SET nome = ?, sigla = ? WHERE id = ?";
    private static final String DEACTIVATE = "UPDATE pais SET ativo = false WHERE id = ?";

    public PaisPOJO insert(PaisPOJO pais) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, pais.getNome());
            pstmt.setString(2, pais.getSigla());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pais.setId_pais(generatedKeys.getInt(1));
                }
            }
        }
        return pais;
    }

    public List<PaisPOJO> findAll() throws SQLException {
        List<PaisPOJO> paises = new ArrayList<>();

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PaisPOJO pais = new PaisPOJO();
                pais.setId_pais(rs.getInt("id"));
                pais.setNome(rs.getString("nome"));
                pais.setSigla(rs.getString("sigla"));
                // Assume que PaisPOJO tem um setter para ativo, se necessário
                paises.add(pais);
            }
        }
        return paises;
    }

    public PaisPOJO findById(int id) throws SQLException {
        PaisPOJO pais = null;

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pais = new PaisPOJO();
                    pais.setId_pais(rs.getInt("id"));
                    pais.setNome(rs.getString("nome"));
                    pais.setSigla(rs.getString("sigla"));
                }
            }
        }
        return pais;
    }

    public void update(PaisPOJO pais) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            pstmt.setString(1, pais.getNome());
            pstmt.setString(2, pais.getSigla());
            pstmt.setInt(3, pais.getId_pais());

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
