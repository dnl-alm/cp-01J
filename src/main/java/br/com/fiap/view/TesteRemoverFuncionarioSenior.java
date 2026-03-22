package br.com.fiap.view;

import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.util.GeradorSQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteRemoverFuncionarioSenior {

    public static void main(String[] args) {
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");

        EntityManager em = fabrica.createEntityManager();
        GeradorSQL geradorSQL = new GeradorSQL();

        FuncionarioSenior funcionarioSenior = em.find(FuncionarioSenior.class, 42);
        System.out.println("Informações do funcionário removido: ");
        funcionarioSenior.imprimirInformacoes();

        System.out.println(
                geradorSQL.gerarDelete(
                        funcionarioSenior,
                        "ID = " + funcionarioSenior.getId()
                )
        );

        em.remove(funcionarioSenior);

        em.getTransaction().begin();
        em.getTransaction().commit();

        System.out.println("Funcionario Senior removido!");
    }

}
