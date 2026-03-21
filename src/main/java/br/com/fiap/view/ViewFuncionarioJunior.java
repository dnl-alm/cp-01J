package br.com.fiap.view;

import br.com.fiap.dao.FuncionarioJuniorDao;
import br.com.fiap.dao.FuncionarioJuniorDaoImpl;
import br.com.fiap.entity.FuncionarioJunior;
import br.com.fiap.exception.IdNaoEncontradoException;
import br.com.fiap.util.GeradorSQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ViewFuncionarioJunior {

    public static void main(String[] args) {

        FuncionarioJunior funcionarioJunior = new FuncionarioJunior("junior", 160, 500);

        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");

        EntityManager em = fabrica.createEntityManager();

        FuncionarioJuniorDao dao = new FuncionarioJuniorDaoImpl(em);

        GeradorSQL geradorSQL = new GeradorSQL();

        try {

            geradorSQL.mostrarDescricoes(funcionarioJunior);

            // CREATE
            System.out.println("=== CREATE ===");

            System.out.println(geradorSQL.gerarInsert(funcionarioJunior));

            em.getTransaction().begin();
            dao.cadastrar(funcionarioJunior);
            em.getTransaction().commit();

            System.out.println("Funcionario Junior cadastrado!\n");
            funcionarioJunior.imprimirInformacoes();

            // READ
            System.out.println("\n=== READ ===");

            System.out.println(geradorSQL.gerarSelect(funcionarioJunior));

            FuncionarioJunior f = dao.buscarPorId(funcionarioJunior.getId());
            f.imprimirInformacoes();

            // UPDATE
            System.out.println("\n=== UPDATE ===");

            System.out.println("ANTES DA ATUALIZAÇÃO:");
            funcionarioJunior.imprimirInformacoes();

            funcionarioJunior.setNome("Junior Silva");
            funcionarioJunior.setHorasTrabalhadas(200);

            System.out.println(
                    geradorSQL.gerarUpdate(
                            funcionarioJunior,
                            "ID = " + funcionarioJunior.getId()
                    )
            );

            em.getTransaction().begin();
            dao.atualizar(funcionarioJunior);
            em.getTransaction().commit();

            System.out.println("\nDEPOIS DA ATUALIZAÇÃO:");
            funcionarioJunior.imprimirInformacoes();

            // DELETE
            System.out.println("\n=== DELETE ===");

            System.out.println(
                    geradorSQL.gerarDelete(
                            funcionarioJunior,
                            "ID = " + funcionarioJunior.getId()
                    )
            );

            System.out.println("\nRemovendo funcionário: ");
            funcionarioJunior.imprimirInformacoes();

            em.getTransaction().begin();
            dao.remover(funcionarioJunior.getId());
            em.getTransaction().commit();

            System.out.println("Funcionario Junior removido!\n");

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