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
        entityManager.persist(aluno);
    }

    public void inserirEmpresa(Empresa empresa) {
        entityManager.persist(empresa);
    }

    public void inserirOrientador(Orientador orientador) {
        entityManager.persist(orientador);
    }

    public void inserirEstagio(Estagio estagio) {
        entityManager.persist(estagio);
    }
}
