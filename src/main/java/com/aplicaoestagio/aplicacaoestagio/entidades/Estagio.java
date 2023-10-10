package com.aplicaoestagio.aplicacaoestagio.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import javax.persistence.ManyToOne;

@Entity
public class Estagio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate inicioEstagio;
    private LocalDate fimEstagio;
    private int cargaHoraria;
    private String status;
    
    
    @ManyToOne
    private Aluno aluno; 

    @ManyToOne
    private Empresa empresa; 

    @ManyToOne
    private Orientador orientador; 

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInicioEstagio() {
        return inicioEstagio;
    }

    public void setInicioEstagio(LocalDate inicioEstagio) {
        this.inicioEstagio = inicioEstagio;
    }

    public LocalDate getFimEstagio() {
        return fimEstagio;
    }

    public void setFimEstagio(LocalDate fimEstagio) {
        this.fimEstagio = fimEstagio;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  
    public Estagio() {
    }

    public Estagio(LocalDate inicioEstagio, LocalDate fimEstagio, int cargaHoraria, String status) {
        this.inicioEstagio = inicioEstagio;
        this.fimEstagio = fimEstagio;
        this.cargaHoraria = cargaHoraria;
        this.status = status;
    }
}
