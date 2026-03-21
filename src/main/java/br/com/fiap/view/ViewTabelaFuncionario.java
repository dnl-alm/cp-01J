package br.com.fiap.view;

import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.dao.FuncionarioDaoImpl;
import br.com.fiap.util.GeradorSQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ViewTabelaFuncionario {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
        EntityManager em = emf.createEntityManager();

        FuncionarioDao dao = new FuncionarioDaoImpl(em);

        GeradorSQL gerador = new GeradorSQL(dao);

        gerador.executarTudo();

    }

}
