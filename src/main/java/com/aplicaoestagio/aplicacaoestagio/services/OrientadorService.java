package com.aplicaoestagio.aplicacaoestagio.services;

import com.aplicaoestagio.aplicacaoestagio.entidades.Orientador;
import com.aplicaoestagio.aplicacaoestagio.entidades.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.transaction.Transactional;
@Transactional
public class OrientadorService {

    private EntityManager entityManager;

    public OrientadorService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void inserirOrientador(Orientador orientador) {
        entityManager.getTransaction().begin();
        entityManager.persist(orientador);
        entityManager.getTransaction().commit();
    }

    public void atualizarOrientador(Orientador orientador) {
        entityManager.getTransaction().begin();
        entityManager.merge(orientador);
        entityManager.getTransaction().commit();
    }

    public void removerOrientador(Long orientadorId) {
        entityManager.getTransaction().begin();
        Orientador orientador = entityManager.find(Orientador.class, orientadorId);
        if (orientador != null) {
            entityManager.remove(orientador);
        }
        entityManager.getTransaction().commit();
    }

    public List<Orientador> listarOrientadores() {
        TypedQuery<Orientador> query = entityManager.createQuery("SELECT o FROM Orientador o", Orientador.class);
        return query.getResultList();
    }

    public Orientador buscarOrientadorPorId(Long orientadorId) {
        return entityManager.find(Orientador.class, orientadorId);
    }

    public Orientador buscarOrientadorPorNome(String nome) {
        TypedQuery<Orientador> query = entityManager.createQuery("SELECT o FROM Orientador o WHERE o.nome = :nome", Orientador.class);
        query.setParameter("nome", nome);
        List<Orientador> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Aluno> listarAlunosPorOrientador(Orientador orientador) {
        TypedQuery<Aluno> query = entityManager.createQuery("SELECT a FROM Aluno a WHERE a.orientador = :orientador", Aluno.class);
        query.setParameter("orientador", orientador);
        return query.getResultList();
    }
}

