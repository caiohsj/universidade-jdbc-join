/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.universidade.jdbc.join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Caio
 */
public abstract class DAO {
    private static final String BD_URL = "jdbc:mysql://localhost/universidade";
    private static final String BD_USUARIO = "root";
    private static final String BD_SENHA = "";
    private static final String USE_TIME_ZONE = "true";
    private static final String SERVER_TIME_ZONE = "UTC";
    protected Connection conn;
    
    public DAO() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Properties props = new Properties();
        props.setProperty("user", BD_USUARIO);
        props.setProperty("password", BD_SENHA);
        props.setProperty("useTimezone", USE_TIME_ZONE);
        props.setProperty("serverTimezone", SERVER_TIME_ZONE);
        conn = DriverManager.getConnection(BD_URL, props);
        conn.setAutoCommit(false);
    }
    
}
