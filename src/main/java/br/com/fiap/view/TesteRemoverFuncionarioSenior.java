package br.com.fiap.view;

import br.com.fiap.entity.FuncionarioSenior;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteRemoverFuncionarioSenior {

    public static void main(String[] args) {
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");

        EntityManager em = fabrica.createEntityManager();

        FuncionarioSenior funcionarioSenior = em.find(FuncionarioSenior.class, 2);
        System.out.println("Informações do funcionário removido: ");
        funcionarioSenior.imprimirInformacoes();

        em.remove(funcionarioSenior);

        em.getTransaction().begin();
        em.getTransaction().commit();

        System.out.println("Funcionario Senior removido!");
    }

}
