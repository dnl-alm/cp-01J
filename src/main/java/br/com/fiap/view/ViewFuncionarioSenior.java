package br.com.fiap.view;

import br.com.fiap.dao.FuncionarioSeniorDao;
import br.com.fiap.dao.FuncionarioSeniorDaoImpl;
import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.exception.IdNaoEncontradoException;
import br.com.fiap.util.GeradorSQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ViewFuncionarioSenior {

    public static void main(String[] args) {

        FuncionarioSenior funcionarioSenior = new FuncionarioSenior("ViewFuncionarioSenior", 110, 500);

        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");

        EntityManager em = fabrica.createEntityManager();

        FuncionarioSeniorDao dao = new FuncionarioSeniorDaoImpl(em);

        GeradorSQL geradorSQL = new GeradorSQL();

        try {

            geradorSQL.mostrarDescricoes(funcionarioSenior);

            //CREATE
            System.out.println("=== CREATE ===");

            System.out.println(geradorSQL.gerarInsert(funcionarioSenior));

            em.getTransaction().begin();
            dao.cadastrar(funcionarioSenior);
            em.getTransaction().commit();

            System.out.println("Funcionario Sênior cadastrado!\n");
            funcionarioSenior.imprimirInformacoes();

            //READ
            System.out.println("\n=== READ ===");

            System.out.println(geradorSQL.gerarSelect(funcionarioSenior));

            FuncionarioSenior f = dao.buscarPorId(funcionarioSenior.getId());
            f.imprimirInformacoes();

            //UPDATE
            System.out.println("\n=== UPDATE ===");

            System.out.println("ANTES DA ATUALIZAÇÃO:");
            funcionarioSenior.imprimirInformacoes();

            funcionarioSenior.setNome("Silva");
            funcionarioSenior.setHorasTrabalhadas(200);

            System.out.println(
                    geradorSQL.gerarUpdate(
                            funcionarioSenior,
                            "ID = " + funcionarioSenior.getId()
                    )
            );

            em.getTransaction().begin();
            dao.atualizar(funcionarioSenior);
            em.getTransaction().commit();

            System.out.println("\nDEPOIS DA ATUALIZAÇÃO:");
            funcionarioSenior.imprimirInformacoes();

            //DELETE
            System.out.println("\n=== DELETE ===");

            System.out.println(
                    geradorSQL.gerarDelete(
                            funcionarioSenior,
                            "ID = " + funcionarioSenior.getId()
                    )
            );

            System.out.println("\nRemovendo funcionário: ");
            funcionarioSenior.imprimirInformacoes();

            em.getTransaction().begin();
            dao.remover(funcionarioSenior.getId());
            em.getTransaction().commit();

            System.out.println("Funcionario Senior removido!\n");

        } catch (IdNaoEncontradoException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            fabrica.close();
        }

    }

}
