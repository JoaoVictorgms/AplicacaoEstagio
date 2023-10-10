package com.aplicaoestagio.aplicacaoestagio.services;

import com.aplicaoestagio.aplicacaoestagio.entidades.Aluno;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlunoService {

    private EntityManager entityManager;

    public AlunoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void inserirAluno(Aluno aluno) {
        entityManager.getTransaction().begin();
        entityManager.persist(aluno);
        entityManager.getTransaction().commit();
    }

    public void atualizarAluno(Aluno aluno) {
        entityManager.getTransaction().begin();
        entityManager.merge(aluno);
        entityManager.getTransaction().commit();
    }

    public void removerAluno(Long alunoId) {
        entityManager.getTransaction().begin();
        Aluno aluno = entityManager.find(Aluno.class, alunoId);
        if (aluno != null) {
            entityManager.remove(aluno);
        }
        entityManager.getTransaction().commit();
    }

    public List<Aluno> listarAlunos() {
        TypedQuery<Aluno> query = entityManager.createQuery("SELECT a FROM Aluno a", Aluno.class);
        return query.getResultList();
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        TypedQuery<Aluno> query = entityManager.createQuery("SELECT a FROM Aluno a WHERE a.matricula = :matricula", Aluno.class);
        query.setParameter("matricula", matricula);
        List<Aluno> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
