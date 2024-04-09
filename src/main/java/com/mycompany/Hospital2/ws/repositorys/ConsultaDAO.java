package com.mycompany.Hospital2.ws.repositorys;

import com.mycompany.Hospital2.ws.POJO.ConsultaPOJO;
import com.mycompany.Hospital2.ws.POJO.MedicoPOJO;
import com.mycompany.Hospital2.ws.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ConsultaDAO {

    private static final String INSERT_SQL = "INSERT INTO consulta (paciente_id, medico_id, data_hora, ativo) VALUES (?, ?, ?, ?) RETURNING id;";

    public ConsultaPOJO insert(ConsultaPOJO consulta) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, consulta.getPaciente().getId());
            pstmt.setInt(2, consulta.getMedico().getId());
            pstmt.setTimestamp(3, new Timestamp(consulta.getDataHora().getTime()));
            pstmt.setBoolean(4, consulta.isAtivo());
            
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    consulta.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating consulta failed, no ID obtained.");
                }
            }
        }
        return consulta;
    }

    public List<ConsultaPOJO> findAll() throws SQLException {
        List<ConsultaPOJO> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consulta WHERE ativo = true";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ConsultaPOJO consulta = new ConsultaPOJO();
                consulta.setId(rs.getInt("id"));
                consulta.setDataHora(new Date(rs.getTimestamp("data_hora").getTime()));
                consulta.setAtivo(rs.getBoolean("ativo"));
                // Assumindo que já existem métodos para setar Paciente e Medico baseado em seus IDs
                consultas.add(consulta);
            }
        }
        return consultas;
    }

    public ConsultaPOJO findById(int id) throws SQLException {
        ConsultaPOJO consulta = null;
        String sql = "SELECT * FROM consulta WHERE id = ? AND ativo = true";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    consulta = new ConsultaPOJO();
                    consulta.setId(rs.getInt("id"));
                    consulta.setDataHora(new Date(rs.getTimestamp("data_hora").getTime()));
                    consulta.setAtivo(rs.getBoolean("ativo"));
                }
            }
        }
        return consulta;
    }

    public void update(ConsultaPOJO consulta) throws SQLException {
        String sql = "UPDATE consulta SET paciente_id = ?, medico_id = ?, data_hora = ?, ativo = ? WHERE id = ?";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, consulta.getPaciente().getId());
            pstmt.setInt(2, consulta.getMedico().getId());
            pstmt.setTimestamp(3, new Timestamp(consulta.getDataHora().getTime()));
            pstmt.setBoolean(4, consulta.isAtivo());
            pstmt.setInt(5, consulta.getId());

            pstmt.executeUpdate();
        }
    }

    public void deactivate(int id) throws SQLException {
        String sql = "UPDATE consulta SET ativo = false WHERE id = ?";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    public void cancelarConsulta(int id, String motivoCancelamento) throws SQLException {
    String sql = "UPDATE consulta SET ativo = false, motivo_cancelamento = ? WHERE id = ?";

    try (Connection conn = new ConnectionFactory().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, motivoCancelamento);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
}

    
    public boolean existsConsultaNoDia(int pacienteId, Date date) throws SQLException {
        String sql = "SELECT COUNT(*) FROM consulta WHERE paciente_id = ? AND CAST(data_hora AS DATE) = ? AND ativo = true";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pacienteId);
            pstmt.setDate(2, new java.sql.Date(date.getTime()));

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    

    public boolean existsConsultaNoHorario(int medicoId, Date dateTime) throws SQLException {
        String sql = "SELECT COUNT(*) FROM consulta WHERE medico_id = ? AND data_hora = ? AND ativo = true";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, medicoId);
            pstmt.setTimestamp(2, new Timestamp(dateTime.getTime()));

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}