package com.aplicaoestagio.aplicacaoestagio.services;

import com.aplicaoestagio.aplicacaoestagio.entidades.Aluno;
import com.aplicaoestagio.aplicacaoestagio.entidades.Empresa;
import com.aplicaoestagio.aplicacaoestagio.entidades.Estagio;
import com.aplicaoestagio.aplicacaoestagio.entidades.Orientador;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class InsercaoService {

    @PersistenceContext
    private EntityManager entityManager;

    public void inserirAluno(Aluno aluno) {
        if (aluno.getOrientador() != null) {
            aluno.getOrientador().getAlunos().add(aluno);
        }
        if (aluno.getEmpresa() != null) {
            aluno.getEmpresa().getAlunos().add(aluno);
        }
        entityManager.persist(aluno);
    }

    public void inserirEmpresa(Empresa empresa) {
        entityManager.persist(empresa);
    }

    public void inserirOrientador(Orientador orientador) {
        entityManager.persist(orientador);
    }

    public void inserirEstagio(Estagio estagio) {
        if (estagio.getAluno() != null) {
            estagio.getAluno().getEstagios().add(estagio);
        }
        if (estagio.getEmpresa() != null) {
            estagio.getEmpresa().getEstagios().add(estagio);
        }
        if (estagio.getOrientador() != null) {
            estagio.getOrientador().getEstagios().add(estagio);
        }
        entityManager.persist(estagio);
    }
}
