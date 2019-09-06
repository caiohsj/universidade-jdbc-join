/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.universidade.jdbc.join;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caio Henrique
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Aluno a = new Aluno();
        a.setNome("Caio");
        a.setCpf("0608848514");
        a.setRa("87645");
        
        Disciplina d = new Disciplina();
        d.setDescricao("Linguagem de Programação 3");
        
        Matricula m = new Matricula();
        m.setAno(2019);
        m.setSemestre(4);
        m.setAluno(a);
        m.setDisciplina(d);
        
        
        AlunoDAO daoAluno = new AlunoDAO();
        //daoAluno.insert(a);
        
        DisciplinaDAO daoDisciplina = new DisciplinaDAO();
        //daoDisciplina.insert(d);
        
        MatriculaDAO daoMatricula = new MatriculaDAO();
        //daoMatricula.insert(m);
        
        List<Matricula> matriculas = new ArrayList<Matricula>();
        matriculas = daoMatricula.getMatriculas();
        
        for(Matricula matricula : matriculas){
            System.out.println("ID: "+matricula.getId());
            System.out.println("Ano: "+matricula.getAno());
            System.out.println("Semestre: "+matricula.getSemestre());
            System.out.println("ID Aluno: "+matricula.getAluno().getId());
            System.out.println("Nome do Aluno: "+matricula.getAluno().getNome());
            System.out.println("CPF do Aluno: "+matricula.getAluno().getCpf());
            System.out.println("RA do Aluno: "+matricula.getAluno().getRa());
            System.out.println("ID Disciplina: "+matricula.getDisciplina().getId());
            System.out.println("Descrição da Disciplina: "+matricula.getDisciplina().getDescricao());
            System.out.println("------------------------------------------------------------------");
        }
        
    }
}
