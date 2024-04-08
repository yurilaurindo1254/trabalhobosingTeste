package com.mycompany.Hospital2.ws.repositorys;

import com.mycompany.Hospital2.ws.POJO.PacientePOJO;
import com.mycompany.Hospital2.ws.POJO.PessoaPOJO;
import com.mycompany.Hospital2.ws.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    private static final String FIND_ALL = "SELECT p.id, p.nome, p.email, p.numero, p.cpf, pa.id AS paciente_id, pa.ativo FROM paciente pa JOIN pessoa p ON pa.pessoa_id = p.id";
    private static final String FIND_BY_ID = "SELECT p.id, p.nome, p.email, p.numero, p.cpf, pa.id AS paciente_id, pa.ativo FROM paciente pa JOIN pessoa p ON pa.pessoa_id = p.id WHERE pa.id = ?";
    private static final String UPDATE = "UPDATE paciente SET pessoa_id = ?, ativo = ? WHERE id = ?";
        
    public PacientePOJO insert(PacientePOJO paciente) throws SQLException {
    String sql = "INSERT INTO paciente(pessoa_id, ativo) VALUES(?, ?)";
    try (Connection conn = new ConnectionFactory().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

        pstmt.setInt(1, paciente.getPessoa().getId());
        pstmt.setBoolean(2, paciente.isAtivo());
        pstmt.executeUpdate();

        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                paciente.setId(generatedKeys.getInt(1));
            }
        }
    }
    return paciente;
}


    public List<PacientePOJO> findAll() throws SQLException {
        List<PacientePOJO> pacientes = new ArrayList<>();

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PessoaPOJO pessoa = new PessoaPOJO(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("numero"), rs.getString("cpf"), null, rs.getBoolean("ativo"));
                PacientePOJO paciente = new PacientePOJO(rs.getInt("paciente_id"), pessoa, rs.getBoolean("ativo"));
                pacientes.add(paciente);
            }
        }
        return pacientes;
    }

    public PacientePOJO findById(int id) throws SQLException {
        PacientePOJO paciente = null;

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    PessoaPOJO pessoa = new PessoaPOJO(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("numero"), rs.getString("cpf"), null, rs.getBoolean("ativo"));
                    paciente = new PacientePOJO(rs.getInt("paciente_id"), pessoa, rs.getBoolean("ativo"));
                }
            }
        }
        return paciente;
    }

    public void update(PacientePOJO paciente) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            pstmt.setInt(1, paciente.getPessoa().getId());
            pstmt.setBoolean(2, paciente.isAtivo());
            pstmt.setInt(3, paciente.getId());
            pstmt.executeUpdate();
        }
    }

    public void deactivate(int id) throws SQLException {
    String sql = "UPDATE paciente SET ativo = false WHERE id = ?";
    try (Connection conn = new ConnectionFactory().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}

}
