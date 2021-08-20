package com.fomularios.application.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Nota implements Serializable {

    private static final long serialVersionUID = 3815873784709209943L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer anoLetivo;
    private String bimestre;
    private Disciplina disciplina;
    private String professor;
    private String atividade;
    private BigDecimal nota;
    private Boolean rascunho;

    public Nota() {
    }

    public Nota(Integer anoLetivo, String bimestre, Disciplina disciplina, String professor, String atividade, BigDecimal nota, Boolean rascunho) {
        this.anoLetivo = anoLetivo;
        this.bimestre = bimestre;
        this.disciplina = disciplina;
        this.professor = professor;
        this.atividade = atividade;
        this.nota = nota;
        this.rascunho = rascunho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isPersisted() {
        return id != null;
    }

    public Integer getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Integer anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public String getBimestre() {
        return bimestre;
    }

    public void setBimestre(String bimestre) {
        this.bimestre = bimestre;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public Boolean getRascunho() {
        return rascunho;
    }

    public void setRascunho(Boolean rascunho) {
        this.rascunho = rascunho;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "anoLetivo=" + anoLetivo +
                ", bimestre='" + bimestre + '\'' +
                ", disciplina=" + disciplina +
                ", professor='" + professor + '\'' +
                ", atividade='" + atividade + '\'' +
                ", nota=" + nota +
                ", rascunho=" + rascunho +
                '}';
    }
}
