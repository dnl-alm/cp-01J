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

        List<FuncionarioSenior> fsList = funcionarioSeniorDao.buscarTodos();

        for (FuncionarioSenior f : fsList) {
            System.out.println(f.getNome());
        }

        try {
            FuncionarioSenior funcId2 = funcionarioSeniorDao.buscarPorId(24);
            GeradorSQL.gerarSelect(funcId2);
            System.out.println("Buscando pelo Id: " + funcId2.getId());
            funcId2.imprimirInformacoes();
        } catch (IdNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Funcionario Sênior pesquisado!");
    }

}
