package br.com.fiap.view;


import br.com.fiap.dao.FuncionarioPlenoDao;
import br.com.fiap.dao.FuncionarioPlenoDaoImpl;
import br.com.fiap.entity.FuncionarioPleno;
import br.com.fiap.exception.IdNaoEncontradoException;
import br.com.fiap.util.GeradorSQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ViewFuncionarioPleno {
    public static void main(String[] args) {

        FuncionarioPleno funcionarioPleno = new FuncionarioPleno("Abigail", 160, 500);

        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
        EntityManager em = fabrica.createEntityManager();

        FuncionarioPlenoDao dao = new FuncionarioPlenoDaoImpl(em);
        GeradorSQL geradorSQL = new GeradorSQL();

        try {

            geradorSQL.mostrarDescricoes(funcionarioPleno);

            // CREATE
            System.out.println("=== CREATE ===");
            System.out.println(geradorSQL.gerarInsert(funcionarioPleno));

            em.getTransaction().begin();
            dao.cadastrar(funcionarioPleno);
            em.getTransaction().commit();

            System.out.println("Funcionario Pleno cadastrado!\n");
            funcionarioPleno.imprimirInformacoes();

            // READ
            System.out.println("\n=== READ ===");
            System.out.println(geradorSQL.gerarSelect(funcionarioPleno));

            FuncionarioPleno f = dao.buscarPorId(funcionarioPleno.getId());
            f.imprimirInformacoes();

            // UPDATE
            System.out.println("\n=== UPDATE ===");

            System.out.println("ANTES DA ATUALIZAÇÃO:");
            funcionarioPleno.imprimirInformacoes();

            funcionarioPleno.setNome("Silva");
            funcionarioPleno.setHorasTrabalhadas(200);

            System.out.println(
                    geradorSQL.gerarUpdate(
                            funcionarioPleno,
                            "ID = " + funcionarioPleno.getId()
                    )
            );

            em.getTransaction().begin();
            dao.atualizar(funcionarioPleno);
            em.getTransaction().commit();

            System.out.println("\nDEPOIS DA ATUALIZAÇÃO:");
            funcionarioPleno.imprimirInformacoes();

            // DELETE
            System.out.println("\n=== DELETE ===");

            System.out.println(
                    geradorSQL.gerarDelete(
                            funcionarioPleno,
                            "ID = " + funcionarioPleno.getId()
                    )
            );

            System.out.println("\nRemovendo funcionário:");
            funcionarioPleno.imprimirInformacoes();

            em.getTransaction().begin();
            dao.remover(funcionarioPleno.getId());
            em.getTransaction().commit();

            System.out.println("Funcionario Pleno removido!\n");

        } catch (IdNaoEncontradoException e) {
            System.out.println("Erro: ID não encontrado");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            fabrica.close();
        }
    }

}

