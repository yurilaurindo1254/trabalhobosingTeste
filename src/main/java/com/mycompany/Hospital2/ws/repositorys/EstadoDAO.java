/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Hospital2.ws.repositorys;

import com.mycompany.Hospital2.ws.POJO.EstadoPOJO;
import com.mycompany.Hospital2.ws.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuriz
 */
public class EstadoDAO {
    
       
    private static final String INSERT = "INSERT INTO ESTADO(nome,sigla,ativo,pais_id)" + "VALUES(?,?,true,?)";
    
    private static final String FIND_ALL = "SELECT id, nome, sigla, pais_id, ativo FROM estado WHERE ATIVO = true";
    
    private static final String FIND_BY_ID = "SELECT id, nome, sigla, ativo , pais_id FROM estado WHERE ID = ?";
    
    private static final String UPDATE = "UPDATE ESTADO SET nome = ?, sigla = ?, pais_id = ? WHERE ID = ?";
    
    
    public List<EstadoPOJO> findAll() throws SQLException  {
        ArrayList<EstadoPOJO> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = new ConnectionFactory().getConnection();
            
            pstmt = conn.prepareStatement(FIND_ALL);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                EstadoPOJO pais = new EstadoPOJO();
                pais.setId_estado(rs.getInt("id"));
                pais.setNome(rs.getString("nome"));
                pais.setSigla(rs.getString("sigla"));
                pais.setPais(new PaisDAO().findById(rs.getInt("pais_id")));
                pais.setAtivo(rs.getBoolean("ATIVO"));
                
                retorno.add(pais);
            }
            
        } finally {
            
            if(rs != null)
            rs.close();
                
            if(pstmt != null)
            pstmt.close();
                
            if(conn != null)
                conn.close();
            
        }
        return retorno;
    }
    
    public EstadoPOJO findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        EstadoPOJO retorno = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            
            pstmt = conn.prepareStatement(FIND_BY_ID);
            
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
               retorno = new EstadoPOJO();
               retorno.setId_estado(rs.getInt("id"));
               retorno.setNome(rs.getString("nome"));
               retorno.setSigla(rs.getString("sigla"));
               retorno.setPais(new PaisDAO().findById(rs.getInt("pais_id")));
               
            }
            
        } finally {
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
        }
        return retorno;
    }
    
    public void insert(EstadoPOJO estado) throws SQLException {
        
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            
             conn = new ConnectionFactory().getConnection();
             
             pstmt = conn.prepareStatement(INSERT);
              
             //pstmt.setInt(1, estado.getId_estado());
               
             pstmt.setString(1, estado.getNome());
             
             pstmt.setString(2, estado.getSigla());
                 
             pstmt.setInt(3, estado.getPais().getId_pais());
            
             pstmt.executeUpdate();
             
        } finally {
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
        }
    }
    
       public void update(EstadoPOJO estado) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        
        try {
            
            conn = new ConnectionFactory().getConnection();
            
            pstmt = conn.prepareStatement(UPDATE);
            
            pstmt.setString(1, estado.getNome());
            
            pstmt.setString(2, estado.getSigla());
                                    
            pstmt.setInt(3, estado.getPais().getId_pais());
            
            pstmt.setInt(4, estado.getId_estado());
           
            pstmt.executeUpdate();
               
            
            
        } finally {
          
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
        }
    }
       
    public void deactivate(int id) throws SQLException {
    String sql = "UPDATE estado SET ativo = false WHERE id = ?";
    try (Connection conn = new ConnectionFactory().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}

}

