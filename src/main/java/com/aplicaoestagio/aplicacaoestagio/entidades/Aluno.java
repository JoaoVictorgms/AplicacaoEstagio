package com.aplicaoestagio.aplicacaoestagio.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String matricula;

    @ManyToOne
    private Orientador orientador; 

    @ManyToOne
    private Empresa empresa;

    @OneToMany(mappedBy = "aluno")
    private List<Estagio> estagios;
 

    public Aluno(String nome, String matricula, Empresa empresa, List<Estagio> estagios) {
        this.nome = nome;
        this.matricula = matricula;
        this.estagios = estagios;
        this.empresa = empresa;
    }

    public Aluno(String nome, String matricula, Empresa empresa, Estagio estagios) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Estagio> getEstagios() {
        return estagios;
    }

    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }
}

