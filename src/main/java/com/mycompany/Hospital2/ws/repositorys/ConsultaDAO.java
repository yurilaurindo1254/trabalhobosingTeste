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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    private static final String INSERT_SQL = "INSERT INTO consulta (paciente_id, medico_id, data_hora, ativo) VALUES (?, ?, ?, ?) RETURNING id;";

     public ConsultaPOJO insert(ConsultaPOJO consulta) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            
            LocalDateTime localDateTime = consulta.getDataHora();
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            
            pstmt.setInt(1, consulta.getPaciente().getId());
            pstmt.setInt(2, consulta.getMedico().getId());
            pstmt.setTimestamp(3, timestamp);
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
                consulta.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                consulta.setAtivo(rs.getBoolean("ativo"));
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
                    consulta.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
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
        pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(consulta.getDataHora()));
        pstmt.setBoolean(4, consulta.isAtivo());
        pstmt.setInt(5, consulta.getId());

        int affectedRows = pstmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Atualização da consulta falhou, nenhuma linha afetada.");
        }
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
    
    public List<MedicoPOJO> findMedicosDisponiveis(LocalDate date) throws SQLException {
        List<MedicoPOJO> medicosDisponiveis = new ArrayList<>();
        // Este SQL é hipotético, adaptar conforme o esquema de banco de dados
        String sql = "SELECT * FROM medico WHERE id NOT IN (SELECT medico_id FROM consulta WHERE data_hora BETWEEN ? AND ? AND ativo = true)";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, java.sql.Date.valueOf(date));
            pstmt.setDate(2, java.sql.Date.valueOf(date.plusDays(1)));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    MedicoPOJO medico = new MedicoPOJO();
                    // Atribuir propriedades ao objeto medico a partir do ResultSet
                    medicosDisponiveis.add(medico);
                }
            }
        }
        return medicosDisponiveis;
    }
    
     public boolean existsConsultaNoDia(int pacienteId, LocalDate date) throws SQLException {
        String sql = "SELECT COUNT(*) FROM consulta WHERE paciente_id = ? AND CAST(data_hora AS DATE) = ? AND ativo = true";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pacienteId);
            pstmt.setDate(2, java.sql.Date.valueOf(date));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean existsConsultaNoHorario(int medicoId, LocalDateTime dateTime) throws SQLException {
        String sql = "SELECT COUNT(*) FROM consulta WHERE medico_id = ? AND data_hora = ? AND ativo = true";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, medicoId);
            pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(dateTime));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}