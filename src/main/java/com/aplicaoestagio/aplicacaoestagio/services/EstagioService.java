package com.aplicaoestagio.aplicacaoestagio.services;

import com.aplicaoestagio.aplicacaoestagio.entidades.Aluno;
import com.aplicaoestagio.aplicacaoestagio.entidades.Empresa;
import com.aplicaoestagio.aplicacaoestagio.entidades.Orientador;
import com.aplicaoestagio.aplicacaoestagio.entidades.Estagio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.transaction.Transactional;

@Transactional
public class EstagioService {

    private EntityManager entityManager;

    public EstagioService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void inserirEstagio(Estagio estagio) {
        entityManager.getTransaction().begin();
        entityManager.persist(estagio);
        entityManager.getTransaction().commit();
    }

    public void atualizarEstagio(Estagio estagio) {
        entityManager.getTransaction().begin();
        entityManager.merge(estagio);
        entityManager.getTransaction().commit();
    }

    public void removerEstagio(Long estagioId) {
        entityManager.getTransaction().begin();
        Estagio estagio = entityManager.find(Estagio.class, estagioId);
        if (estagio != null) {
            entityManager.remove(estagio);
        }
        entityManager.getTransaction().commit();
    }

    public List<Estagio> listarEstagios() {
        TypedQuery<Estagio> query = entityManager.createQuery("SELECT e FROM Estagio e", Estagio.class);
        return query.getResultList();
    }

    public Estagio selecionarEstagioPorAlunoMatricula(String matricula) {
        TypedQuery<Estagio> query = entityManager.createQuery("SELECT e FROM Estagio e WHERE e.aluno.matricula = :matricula", Estagio.class);
        query.setParameter("matricula", matricula);
        List<Estagio> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    public Estagio selecionarEstagioPorId(Long estagioId) {
        return entityManager.find(Estagio.class, estagioId);
    }

    public List<Estagio> listarEstagiosPorEmpresa(Empresa empresa) {
        TypedQuery<Estagio> query = entityManager.createQuery("SELECT e FROM Estagio e WHERE e.empresa = :empresa", Estagio.class);
        query.setParameter("empresa", empresa);
        return query.getResultList();
    }

    public List<Estagio> listarEstagiosPorOrientador(Orientador orientador) {
        TypedQuery<Estagio> query = entityManager.createQuery("SELECT e FROM Estagio e WHERE e.orientador = :orientador", Estagio.class);
        query.setParameter("orientador", orientador);
        return query.getResultList();
    }
}
