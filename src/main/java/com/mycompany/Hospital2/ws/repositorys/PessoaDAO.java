package com.mycompany.Hospital2.ws.repositorys;

import com.mycompany.Hospital2.ws.POJO.PessoaPOJO;
import com.mycompany.Hospital2.ws.POJO.EnderecoPOJO;
import com.mycompany.Hospital2.ws.infraestructure.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private static final String INSERT = "INSERT INTO pessoa (nome, email, numero, cpf, ativo, endereco_id) VALUES (?, ?, ?, ?, true, ?)";
    private static final String FIND_ALL = "SELECT p.id, p.nome, p.email, p.numero, p.cpf, p.ativo, p.endereco_id, e.logradouro FROM pessoa p LEFT JOIN endereco e ON p.endereco_id = e.id WHERE p.ativo = true";
    private static final String FIND_BY_ID = "SELECT p.id, p.nome, p.email, p.numero, p.cpf, p.ativo, p.endereco_id, e.logradouro FROM pessoa p LEFT JOIN endereco e ON p.endereco_id = e.id WHERE p.id = ?";
    private static final String UPDATE = "UPDATE pessoa SET nome = ?, email = ?, numero = ?, cpf = ?, endereco_id = ? WHERE id = ?";
    private static final String DEACTIVATE = "UPDATE pessoa SET ativo = false WHERE id = ?";

    public PessoaPOJO insert(PessoaPOJO pessoa) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, pessoa.getNome());
            pstmt.setString(2, pessoa.getEmail());
            pstmt.setString(3, pessoa.getNumero());
            pstmt.setString(4, pessoa.getCpf());
            pstmt.setInt(5, pessoa.getEndereco().getId());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pessoa.setId(generatedKeys.getInt(1));
                }
            }
        }
        return pessoa;
    }

    public List<PessoaPOJO> findAll() throws SQLException {
        List<PessoaPOJO> pessoas = new ArrayList<>();

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                EnderecoPOJO endereco = new EnderecoPOJO();
                endereco.setId(rs.getInt("endereco_id"));
                endereco.setLogradouro(rs.getString("logradouro"));

                PessoaPOJO pessoa = new PessoaPOJO();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setNumero(rs.getString("numero"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setAtivo(rs.getBoolean("ativo"));
                pessoa.setEndereco(endereco);

                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }

    public PessoaPOJO findById(int id) throws SQLException {
        PessoaPOJO pessoa = null;

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    EnderecoPOJO endereco = new EnderecoPOJO();
                    endereco.setId(rs.getInt("endereco_id"));
                    endereco.setLogradouro(rs.getString("logradouro"));

                    pessoa = new PessoaPOJO();
                    pessoa.setId(rs.getInt("id"));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setEmail(rs.getString("email"));
                    pessoa.setNumero(rs.getString("numero"));
                    pessoa.setCpf(rs.getString("cpf"));
                    pessoa.setAtivo(rs.getBoolean("ativo"));
                    pessoa.setEndereco(endereco);
                }
            }
        }
        return pessoa;
    }

    public void update(PessoaPOJO pessoa) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            pstmt.setString(1, pessoa.getNome());
            pstmt.setString(2, pessoa.getEmail());
            pstmt.setString(3, pessoa.getNumero());
            pstmt.setString(4, pessoa.getCpf());
            pstmt.setInt(5, pessoa.getEndereco().getId());
            pstmt.setInt(6, pessoa.getId());

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
