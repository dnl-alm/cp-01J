package br.com.fiap.dao;

import br.com.fiap.entity.FuncionarioPleno;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNaoEncontradoException;

import javax.persistence.EntityManager;
import java.util.List;

public class FuncionarioPlenoDaoImpl implements FuncionarioPlenoDao{

    private EntityManager em;

    public FuncionarioPlenoDaoImpl(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(FuncionarioPleno funcionarioPleno) {
        em.persist(funcionarioPleno);
    }

    public void atualizar(FuncionarioPleno funcionarioPleno) throws IdNaoEncontradoException {
        buscarPorId(funcionarioPleno.getId());
        em.merge(funcionarioPleno);
    }

    public void remover(int id) throws IdNaoEncontradoException {
        FuncionarioPleno funcionarioPleno = buscarPorId(id);
        em.remove(funcionarioPleno);
    }

    public FuncionarioPleno buscarPorId(int id) throws IdNaoEncontradoException {
        FuncionarioPleno funcionarioPleno = em.find(FuncionarioPleno.class, id);
        if (funcionarioPleno == null)
            throw new IdNaoEncontradoException("Funcionario nao encontrado");
        return funcionarioPleno;
    }

    public List<FuncionarioPleno> buscarTodos() {
        return em.createQuery("FROM FuncionarioPleno", FuncionarioPleno.class)
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
