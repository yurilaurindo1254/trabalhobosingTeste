package com.mycompany.Hospital2.ws.repositorys;

import com.mycompany.Hospital2.ws.POJO.EnderecoPOJO;
import com.mycompany.Hospital2.ws.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    
    private static final String INSERT = "INSERT INTO endereco(logradouro, numero, bairro, cep, complemento, cidade_id, ativo) VALUES(?, ?, ?, ?, ?, ?, true)";
    private static final String FIND_ALL = "SELECT id, logradouro, numero, bairro, cep, complemento, cidade_id FROM endereco WHERE ativo = true";
    private static final String FIND_BY_ID = "SELECT id, logradouro, numero, bairro, cep, complemento, cidade_id FROM endereco WHERE id = ? AND ativo = true";
    private static final String UPDATE = "UPDATE endereco SET logradouro = ?, numero = ?, bairro = ?, cep = ?, complemento = ?, cidade_id = ? WHERE id = ?";
    private static final String DEACTIVATE = "UPDATE endereco SET ativo = false WHERE id = ?";

    public List<EnderecoPOJO> findAll() throws SQLException {
        List<EnderecoPOJO> enderecos = new ArrayList<>();

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                EnderecoPOJO endereco = new EnderecoPOJO();
                endereco.setId(rs.getInt("id"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setComplemento(rs.getString("complemento"));
                // Supondo que o método findById da CidadeDAO esteja implementado corretamente
                endereco.setCidade(new CidadeDAO().findById(rs.getInt("cidade_id")));
                enderecos.add(endereco);
            }
        }
        return enderecos;
    }

    public EnderecoPOJO findById(int id) throws SQLException {
        EnderecoPOJO endereco = null;

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    endereco = new EnderecoPOJO();
                    endereco.setId(rs.getInt("id"));
                    endereco.setLogradouro(rs.getString("logradouro"));
                    endereco.setNumero(rs.getInt("numero"));
                    endereco.setBairro(rs.getString("bairro"));
                    endereco.setCep(rs.getString("cep"));
                    endereco.setComplemento(rs.getString("complemento"));
                    endereco.setCidade(new CidadeDAO().findById(rs.getInt("cidade_id")));
                }
            }
        }
        return endereco;
    }

    public EnderecoPOJO insert(EnderecoPOJO endereco) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, endereco.getLogradouro());
            pstmt.setInt(2, endereco.getNumero());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setString(4, endereco.getCep());
            pstmt.setString(5, endereco.getComplemento());
            pstmt.setInt(6, endereco.getCidade().getId_cidade());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    endereco.setId(generatedKeys.getInt(1));
                }
            }
        }
        return endereco;
    }

    public void update(EnderecoPOJO endereco) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            pstmt.setString(1, endereco.getLogradouro());
            pstmt.setInt(2, endereco.getNumero());
            pstmt.setString(3, endereco.getBairro());
            pstmt.setString(4, endereco.getCep());
            pstmt.setString(5, endereco.getComplemento());
            pstmt.setInt(6, endereco.getCidade().getId_cidade());
            pstmt.setInt(7, endereco.getId());

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
