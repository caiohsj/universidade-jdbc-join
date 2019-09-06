/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.universidade.jdbc.join;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Caio
 */
public class DisciplinaDAO extends DAO {
    
    public DisciplinaDAO() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        super();
    }
    
    public void insert(Disciplina disciplina) throws SQLException {
        String query = "INSERT INTO disciplina (descricao)"
                + "VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, disciplina.getDescricao());
        ps.execute();
        conn.commit();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        disciplina.setId(rs.getInt(1));
    }
}
