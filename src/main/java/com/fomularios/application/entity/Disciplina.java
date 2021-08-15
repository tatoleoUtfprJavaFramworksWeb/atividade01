package com.fomularios.application.entity;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

    private Integer id;
    private String nome;

    public Disciplina() {
    }

    public Disciplina(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static List<Disciplina> lista() {
        List<Disciplina> lista = new ArrayList<>();
        lista.add(new Disciplina(1, "Biologia"));
        lista.add(new Disciplina(2, "História"));
        lista.add(new Disciplina(3, "Português"));
        lista.add(new Disciplina(5, "Matemática"));
        lista.add(new Disciplina(6, "Química"));
        lista.add(new Disciplina(7, "Física"));
        lista.add(new Disciplina(8, "Sociologia"));
        return lista;
    }

}
