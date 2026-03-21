package br.com.fiap.view;

import br.com.fiap.dao.FuncionarioSeniorDao;
import br.com.fiap.dao.FuncionarioSeniorDaoImpl;
import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.exception.IdNaoEncontradoException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteCadastroFuncionarioSenior {

    public static void main(String[] args) {
        FuncionarioSenior funcionarioSenior = new FuncionarioSenior("Warner", 160, 500);

        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");

        EntityManager em = fabrica.createEntityManager();

        FuncionarioSeniorDao dao = new FuncionarioSeniorDaoImpl(em);

        em.persist(funcionarioSenior);

        em.getTransaction().begin();
        em.getTransaction().commit();

        System.out.println("Funcionario Sênior cadastrado!");

        try {
            FuncionarioSenior f = dao.buscarPorId(funcionarioSenior.getId());
            f.imprimirInformacoes();
        } catch (IdNaoEncontradoException e) {
            throw new RuntimeException(e);
        }
    }

}
