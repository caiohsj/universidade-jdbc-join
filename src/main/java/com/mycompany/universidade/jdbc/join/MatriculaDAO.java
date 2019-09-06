/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.universidade.jdbc.join;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Caio
 */
public class MatriculaDAO extends DAO {

    public MatriculaDAO() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        super();
    }
    
    public void insert(Matricula matricula) throws SQLException {
        String query = "INSERT INTO matricula(ano, semestre, idAluno, idDisciplina) "
                + "VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, matricula.getAno());
        ps.setInt(2, matricula.getSemestre());
        ps.setInt(3, matricula.getAluno().getId());
        ps.setInt(4, matricula.getDisciplina().getId());
        ps.execute();
        conn.commit();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        matricula.setId(rs.getInt(1));
    }
    
    public List<Matricula> getMatriculas() throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ");
        query.append("FROM matricula m ");
        query.append("INNER JOIN aluno a ");
        query.append("ON a.id = m.idAluno ");
        query.append("INNER JOIN disciplina d ");
        query.append("ON d.id = m.idDisciplina");
        
        PreparedStatement ps = conn.prepareStatement(query.toString());
        ResultSet rs = ps.executeQuery();
        
        HashMap<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();
        HashMap<Integer, Disciplina> disciplinas = new HashMap<Integer, Disciplina>();
        
        
        Integer idAluno;
        Integer idDisciplina;
        Aluno aluno;
        Disciplina disciplina;
        Matricula matricula;
        List<Matricula> matriculas = new ArrayList<Matricula>();
        
        while(rs.next()) {
            idAluno = rs.getInt("a.id");
            idDisciplina = rs.getInt("d.id");
            if(alunos.containsKey(idAluno)){
                aluno = alunos.get(idAluno);
            } else {
                aluno = new Aluno();
                aluno.setId(idAluno);
                aluno.setNome(rs.getString("a.nome"));
                aluno.setCpf(rs.getString("a.cpf"));
                aluno.setRa(rs.getString("a.ra"));
                alunos.put(idAluno, aluno);
            }
            
            if(disciplinas.containsKey(idDisciplina)) {
                disciplina = disciplinas.get(idDisciplina);
            } else {
                disciplina = new Disciplina();
                disciplina.setId(idDisciplina);
                disciplina.setDescricao(rs.getString("d.descricao"));
                disciplinas.put(idDisciplina, disciplina);
            }
            
            matricula = new Matricula();
            matricula.setId(rs.getInt("m.id"));
            matricula.setAno(rs.getInt("m.ano"));
            matricula.setSemestre(rs.getInt("m.semestre"));
            matricula.setAluno(aluno);
            matricula.setDisciplina(disciplina);
            
            matriculas.add(matricula);
        }
        
        return matriculas;
    }
    
}
