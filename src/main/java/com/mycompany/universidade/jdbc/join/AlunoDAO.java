/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.universidade.jdbc.join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Caio Henrique
 */
public class AlunoDAO extends DAO {
    
    public AlunoDAO() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        super();
    }

    public void insert(Aluno aluno) throws SQLException {
        String query = "INSERT INTO aluno (nome,cpf,ra)"
                + "VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, aluno.getNome());
        ps.setString(2, aluno.getCpf());
        ps.setString(3, aluno.getRa());
        ps.execute();
        conn.commit();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        aluno.setId(rs.getInt(1));
    }
    
}
