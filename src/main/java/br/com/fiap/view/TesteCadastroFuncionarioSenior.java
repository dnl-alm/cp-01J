package br.com.fiap.view;

import br.com.fiap.entity.FuncionarioSenior;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteCadastroFuncionarioSenior {

    public static void main(String[] args) {
        FuncionarioSenior funcionarioSenior = new FuncionarioSenior("Daniel", 160, 500);

        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");

        EntityManager em = fabrica.createEntityManager();

        em.persist(funcionarioSenior);

        em.getTransaction().begin();
        em.getTransaction().commit();

        System.out.println("Funcionario Sênior cadastrado!");
    }

}
