package br.com.fiap.view;

import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.dao.FuncionarioDaoImpl;
import br.com.fiap.dao.FuncionarioSeniorDao;
import br.com.fiap.dao.FuncionarioSeniorDaoImpl;
import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.exception.IdNaoEncontradoException;
import br.com.fiap.util.GeradorSQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VeiwFuncionarioSenior {

    public static void main(String[] args) {
        FuncionarioSenior funcionarioSenior = new FuncionarioSenior("abigail", 160, 500);

        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");

        EntityManager em = fabrica.createEntityManager();

        FuncionarioSeniorDao dao = new FuncionarioSeniorDaoImpl(em);
        FuncionarioDao funcDao = new FuncionarioDaoImpl(em);
        GeradorSQL geradorSQL = new GeradorSQL(funcDao);

        try {
            //CREATE
            em.persist(funcionarioSenior);

            em.getTransaction().begin();
            em.getTransaction().commit();

            System.out.println("Funcionario Sênior cadastrado!\n");
            geradorSQL.mostrarDados();

            //READ
            FuncionarioSenior f = dao.buscarPorId(funcionarioSenior.getId());
            f.imprimirInformacoes();

            //UPDATE
            System.out.println("ANTES DA ATUALIZAÇÃO:");
            funcionarioSenior.imprimirInformacoes();

            funcionarioSenior.setNome("Silva");
            funcionarioSenior.setHorasTrabalhadas(200);

            em.getTransaction().begin();

            dao.atualizar(funcionarioSenior);

            em.getTransaction().commit();

            System.out.println("\nDEPOIS DA ATUALIZAÇÃO:");
            funcionarioSenior.imprimirInformacoes();

            geradorSQL.mostrarDados();

            //DELETE
            System.out.println("\nRemovendo funcionário: ");
            funcionarioSenior.imprimirInformacoes();
            em.remove(funcionarioSenior);

            em.getTransaction().begin();
            em.getTransaction().commit();

            System.out.println("Funcionario Senior removido!\n");

            geradorSQL.mostrarDados();

        } catch (IdNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

    }

}
