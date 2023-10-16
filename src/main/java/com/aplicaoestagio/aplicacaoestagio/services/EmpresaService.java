package com.aplicaoestagio.aplicacaoestagio.services;

import com.aplicaoestagio.aplicacaoestagio.entidades.Empresa;
import com.aplicaoestagio.aplicacaoestagio.entidades.Aluno;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import javax.persistence.PersistenceException;

@Service
@Transactional
public class EmpresaService {

    @PersistenceContext
    private EntityManager entityManager;

 public void inserirEmpresa(Empresa empresa) {
    try {
        entityManager.getTransaction().begin();
        entityManager.persist(empresa);
        entityManager.getTransaction().commit();
    } catch (PersistenceException e) {
        System.err.println("Erro ao persistir empresa: " + e.getMessage());
        entityManager.getTransaction().rollback();
    }
}

  public void atualizarEmpresa(Empresa empresa) {
    try {
        entityManager.getTransaction().begin();
        entityManager.merge(empresa);
        entityManager.getTransaction().commit();
    } catch (PersistenceException e) {
        System.err.println("Erro ao atualizar empresa: " + e.getMessage());
        entityManager.getTransaction().rollback();
    }
}

   public void removerEmpresa(Long empresaId) {
    try {
        entityManager.getTransaction().begin();
        Empresa empresa = entityManager.find(Empresa.class, empresaId);
        if (empresa != null) {
            entityManager.remove(empresa);
        }
        entityManager.getTransaction().commit();
    } catch (PersistenceException e) {
        System.err.println("Erro ao remover empresa: " + e.getMessage());
        entityManager.getTransaction().rollback();
    }
}

    public List<Empresa> listarEmpresas() {
        return entityManager.createQuery("SELECT e FROM Empresa e", Empresa.class)
                .getResultList();
    }

    public Empresa buscarEmpresaPorId(Long empresaId) {
        return entityManager.find(Empresa.class, empresaId);
    }
    
    public List<Aluno> listarAlunosPorEmpresa(Empresa empresa) {
        return entityManager.createQuery("SELECT a FROM Aluno a WHERE a.empresa = :empresa", Aluno.class)
                .setParameter("empresa", empresa)
                .getResultList();
    }

    


    
}
