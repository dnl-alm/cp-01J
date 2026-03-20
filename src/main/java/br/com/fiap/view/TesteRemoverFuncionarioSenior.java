package br.com.fiap.view;

import br.com.fiap.entity.FuncionarioSenior;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteRemoverFuncionarioSenior {

    public static void main(String[] args) {
        //Criar a fabrica
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");

        //Criar o Entity Manager
        EntityManager em = fabrica.createEntityManager();

        //Pesquisar o cliente que sera removido
        FuncionarioSenior funcionarioSenior = em.find(FuncionarioSenior.class, 3);

        //Chamar o método remover
        em.remove(funcionarioSenior);

        em.getTransaction().begin();
        em.getTransaction().commit();

        System.out.println("Funcionario Senior removido!");
    }

}
