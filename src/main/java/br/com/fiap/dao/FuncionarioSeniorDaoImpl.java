package br.com.fiap.dao;

import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNaoEncontradoException;

import javax.persistence.EntityManager;
import java.util.List;

public class FuncionarioSeniorDaoImpl implements FuncionarioSeniorDao {

    private EntityManager em;

    public FuncionarioSeniorDaoImpl(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(FuncionarioSenior funcionarioSenior) {
        em.persist(funcionarioSenior);
    }

    public void atualizar(FuncionarioSenior funcionarioSenior) throws IdNaoEncontradoException {
        buscarPorId(funcionarioSenior.getId());
        em.merge(funcionarioSenior);
    }

    public void remover(int id) throws IdNaoEncontradoException {
        FuncionarioSenior funcionarioSenior = buscarPorId(id);
        em.remove(funcionarioSenior);
    }

    public FuncionarioSenior buscarPorId(int id) throws IdNaoEncontradoException {
        FuncionarioSenior funcionarioSenior = em.find(FuncionarioSenior.class, id);
        if (funcionarioSenior == null)
            throw new IdNaoEncontradoException("Funcionario nao encontrado");
        return funcionarioSenior;
    }

    public List<FuncionarioSenior> buscarTodos() {
        return em.createQuery("FROM FuncionarioSenior", FuncionarioSenior.class)
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
