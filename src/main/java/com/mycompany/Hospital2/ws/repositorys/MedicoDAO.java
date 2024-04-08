package com.mycompany.Hospital2.ws.repositorys;

import com.mycompany.Hospital2.ws.POJO.MedicoPOJO;
import com.mycompany.Hospital2.ws.POJO.PessoaPOJO;
import com.mycompany.Hospital2.ws.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    private static final String FIND_ALL = "SELECT m.id, m.crm, m.ativo, p.id as pessoa_id, p.nome, p.email, p.numero, p.cpf FROM medico m JOIN pessoa p ON m.pessoa_id = p.id";
    private static final String FIND_BY_ID = "SELECT m.id, m.crm, m.ativo, p.id as pessoa_id, p.nome, p.email, p.numero, p.cpf FROM medico m JOIN pessoa p ON m.pessoa_id = p.id WHERE m.id = ?";
    private static final String UPDATE = "UPDATE medico SET crm = ?, ativo = ? WHERE id = ?";

    public MedicoPOJO insert(MedicoPOJO medico) throws SQLException {
    String INSERT = "INSERT INTO medico(pessoa_id, crm, ativo) VALUES(?, ?, ?) RETURNING id";
    try (Connection conn = new ConnectionFactory().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

        pstmt.setInt(1, medico.getPessoa().getId());
        pstmt.setString(2, medico.getCrm());
        pstmt.setBoolean(3, medico.isAtivo());
        int affectedRows = pstmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating medico failed, no rows affected.");
        }

        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                medico.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating medico failed, no ID obtained.");
            }
        }
    }
    return medico;
}

    public List<MedicoPOJO> findAll() throws SQLException {
        List<MedicoPOJO> medicos = new ArrayList<>();

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PessoaPOJO pessoa = new PessoaPOJO(rs.getInt("pessoa_id"), rs.getString("nome"), rs.getString("email"), rs.getString("numero"), rs.getString("cpf"), null, rs.getBoolean("ativo"));
                MedicoPOJO medico = new MedicoPOJO(rs.getInt("id"), pessoa, rs.getString("crm"), rs.getBoolean("ativo"));
                medicos.add(medico);
            }
        }
        return medicos;
    }

    public MedicoPOJO findById(int id) throws SQLException {
        MedicoPOJO medico = null;

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    PessoaPOJO pessoa = new PessoaPOJO(rs.getInt("pessoa_id"), rs.getString("nome"), rs.getString("email"), rs.getString("numero"), rs.getString("cpf"), null, rs.getBoolean("ativo"));
                    medico = new MedicoPOJO(rs.getInt("id"), pessoa, rs.getString("crm"), rs.getBoolean("ativo"));
                }
            }
        }
        return medico;
    }

    public void update(MedicoPOJO medico) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            pstmt.setString(1, medico.getCrm());
            pstmt.setBoolean(2, medico.isAtivo());
            pstmt.setInt(3, medico.getId());
            pstmt.executeUpdate();
        }
    }

    public void deactivate(int id) throws SQLException {
    String sql = "UPDATE medico SET ativo = false WHERE id = ?";
    try (Connection conn = new ConnectionFactory().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}

}
