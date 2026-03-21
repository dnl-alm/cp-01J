package br.com.fiap.dao;

import br.com.fiap.entity.Funcionario;
import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNaoEncontradoException;

import javax.persistence.EntityManager;
import java.util.List;

public class FuncionarioDaoImpl implements FuncionarioDao {

    private EntityManager em;

    public FuncionarioDaoImpl(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Funcionario funcionario) {
        em.persist(funcionario);
    }

    public void atualizar(Funcionario funcionario) throws IdNaoEncontradoException {
        buscarPorId(funcionario.getId());
        em.merge(funcionario);
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

    public List<Funcionario> buscarTodos() {
        return em.createQuery("FROM Funcionario", Funcionario.class)
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
