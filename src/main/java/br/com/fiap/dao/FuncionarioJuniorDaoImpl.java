package br.com.fiap.dao;

import br.com.fiap.entity.FuncionarioJunior;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNaoEncontradoException;

import javax.persistence.EntityManager;
import java.util.List;

public class FuncionarioJuniorDaoImpl implements FuncionarioJuniorDao {

    private EntityManager em;

    public FuncionarioJuniorDaoImpl(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(FuncionarioJunior funcionarioJunior) {
        em.persist(funcionarioJunior);
    }

    public void atualizar(FuncionarioJunior funcionarioJunior) throws IdNaoEncontradoException {
        buscarPorId(funcionarioJunior.getId());
        em.merge(funcionarioJunior);
    }

    public void remover(int id) throws IdNaoEncontradoException {
        FuncionarioJunior funcionarioJunior = buscarPorId(id);
        em.remove(funcionarioJunior);
    }

    public FuncionarioJunior buscarPorId(int id) throws IdNaoEncontradoException {
        FuncionarioJunior funcionarioJunior = em.find(FuncionarioJunior.class, id);
        if (funcionarioJunior == null)
            throw new IdNaoEncontradoException("Funcionario nao encontrado");
        return funcionarioJunior;
    }

    public List<FuncionarioJunior> buscarTodos() {
        return em.createQuery("FROM FuncionarioJunior", FuncionarioJunior.class)
                .getResultList();
    }

    public void commit() throws CommitException {
        try {
            em.getTransaction().begin();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new CommitException();
        }
    }

}
