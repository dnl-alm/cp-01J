package br.com.fiap.view;

import br.com.fiap.dao.FuncionarioSeniorDao;
import br.com.fiap.dao.FuncionarioSeniorDaoImpl;
import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNaoEncontradoException;
import br.com.fiap.util.GeradorSQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteAtualizacaoFuncionarioSenior {

    public static void main(String[] args) {


        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
        EntityManager em = fabrica.createEntityManager();

        FuncionarioSeniorDao dao = new FuncionarioSeniorDaoImpl(em);
        GeradorSQL geradorSQL = new GeradorSQL();

        try {

            FuncionarioSenior funcionario = dao.buscarPorId(42);
            System.out.println(geradorSQL.gerarSelect(funcionario));

            System.out.println("ANTES DA ATUALIZAÇÃO:");
            funcionario.imprimirInformacoes();

            funcionario.setNome("Teste Tranquilo!");
            funcionario.setHorasTrabalhadas(200);

            em.getTransaction().begin();

            dao.atualizar(funcionario);

            em.getTransaction().commit();

            System.out.println("\nDEPOIS DA ATUALIZAÇÃO:");
            funcionario.imprimirInformacoes();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

}
