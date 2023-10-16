package com.aplicaoestagio.aplicacaoestagio.services;

import com.aplicaoestagio.aplicacaoestagio.entidades.Aluno;
import com.aplicaoestagio.aplicacaoestagio.entidades.Empresa;
import com.aplicaoestagio.aplicacaoestagio.entidades.Orientador;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@Transactional

public class AlunoService {

    private EntityManager entityManager;

    public AlunoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

   public void inserirAluno(Aluno aluno) {
    try {
        entityManager.getTransaction().begin();
        entityManager.persist(aluno);
        entityManager.getTransaction().commit();
    } catch (PersistenceException e) {
        System.err.println("Erro ao persistir aluno: " + e.getMessage());
        entityManager.getTransaction().rollback();
    }
}

     public void atualizarAluno(Aluno aluno) {
    try {
        entityManager.getTransaction().begin();
        entityManager.merge(aluno);
        entityManager.getTransaction().commit();
    } catch (PersistenceException e) {
        System.err.println("Erro ao atualizar aluno: " + e.getMessage());
        entityManager.getTransaction().rollback();
    }
}

     public void removerAluno(Long alunoId) {
    try {
        entityManager.getTransaction().begin();
        Aluno aluno = entityManager.find(Aluno.class, alunoId);
        if (aluno != null) {
            entityManager.remove(aluno);
        }
        entityManager.getTransaction().commit();
    } catch (PersistenceException e) {
        System.err.println("Erro ao remover aluno: " + e.getMessage());
        entityManager.getTransaction().rollback();
    }
}

    public List<Aluno> listarAlunos() {
        TypedQuery<Aluno> query = entityManager.createQuery("SELECT a FROM Aluno a", Aluno.class);
        return query.getResultList();
    }

    public Aluno buscarAlunoPorId(Long alunoId) {
        return entityManager.find(Aluno.class, alunoId);
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        TypedQuery<Aluno> query = entityManager.createQuery("SELECT a FROM Aluno a WHERE a.matricula = :matricula", Aluno.class);
        query.setParameter("matricula", matricula);
        List<Aluno> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Aluno> listarAlunosPorOrientador(Orientador orientador) {
        TypedQuery<Aluno> query = entityManager.createQuery("SELECT a FROM Aluno a WHERE a.orientador = :orientador", Aluno.class);
        query.setParameter("orientador", orientador);
        return query.getResultList();
    }

    public List<Aluno> listarAlunosPorEmpresa(Empresa empresa) {
        TypedQuery<Aluno> query = entityManager.createQuery("SELECT a FROM Aluno a WHERE a.empresa = :empresa", Aluno.class);
        query.setParameter("empresa", empresa);
        return query.getResultList();
    }
}
