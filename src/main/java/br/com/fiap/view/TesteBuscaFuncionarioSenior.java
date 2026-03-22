package br.com.fiap.view;

import br.com.fiap.dao.FuncionarioSeniorDao;
import br.com.fiap.dao.FuncionarioSeniorDaoImpl;
import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.exception.IdNaoEncontradoException;
import br.com.fiap.util.GeradorSQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TesteBuscaFuncionarioSenior {

    public static void main(String[] args) {
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");

        EntityManager em = fabrica.createEntityManager();

        FuncionarioSeniorDao funcionarioSeniorDao = new FuncionarioSeniorDaoImpl(em);
        GeradorSQL geradorSQL = new GeradorSQL();

        try {
            FuncionarioSenior funcId42 = funcionarioSeniorDao.buscarPorId(42);
            System.out.println(geradorSQL.gerarSelect(funcId42));
            System.out.println("Buscando pelo Id: " + funcId42.getId());
            funcId42.imprimirInformacoes();
        } catch (IdNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Funcionario Sênior pesquisado!");
    }

}
