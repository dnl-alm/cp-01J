package br.com.fiap.view;

import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.dao.FuncionarioDaoImpl;
import br.com.fiap.entity.Funcionario;
import br.com.fiap.util.GeradorSQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ViewTabelaFuncionario {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
        EntityManager em = emf.createEntityManager();

        FuncionarioDao dao = new FuncionarioDaoImpl(em);

        GeradorSQL geradorSQL = new GeradorSQL();

        try {

            Funcionario funcionarioTemp = new Funcionario();

            //Descrição
            System.out.println("\n=== DESCRIÇÃO DA TABELA ===");
            geradorSQL.mostrarDescricoes(funcionarioTemp);

            //SQL gerado
            System.out.println("\n=== SQL GERADO ===");
            System.out.println(geradorSQL.gerarSelect(funcionarioTemp));

            //SELECT
            System.out.println("\n=== DADOS DA TABELA ===");

            List<Funcionario> lista = dao.buscarTodos();

            if (lista.isEmpty()) {
                System.out.println("Nenhum registro encontrado.");
            } else {

                System.out.println("+----+----------------------+--------+--------+");
                System.out.println("| ID | Nome                 | Horas  | Valor  |");
                System.out.println("+----+----------------------+--------+--------+");

                for (Funcionario f : lista) {
                    System.out.printf(
                            "| %-2d | %-20s | %-6d | %-6.2f |\n",
                            f.getId(),
                            f.getNome(),
                            f.getHorasTrabalhadas(),
                            f.getValorHoraTrabalhada()
                    );
                }

                System.out.println("+----+----------------------+--------+--------+");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

    }

}
