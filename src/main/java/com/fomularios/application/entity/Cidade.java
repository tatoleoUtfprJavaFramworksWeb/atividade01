package com.fomularios.application.entity;

import java.util.ArrayList;
import java.util.List;

public class Cidade {

    private Integer id;
    private String nome;
    private String estado;

    public Cidade() {
    }

    public Cidade(Integer id, String nome, String estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public static List<Cidade> lista() {
        List<Cidade> lista = new ArrayList<>();
        lista.add(new Cidade(1, "Cidade 1", "PR"));
        lista.add(new Cidade(2, "Cidade 2", "PA"));
        lista.add(new Cidade(3, "Cidade 3", "RR"));
        lista.add(new Cidade(4, "Cidade 4", "SP"));
        return lista;
    }

}
