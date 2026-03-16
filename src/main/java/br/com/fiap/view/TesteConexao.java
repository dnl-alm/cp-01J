package br.com.fiap.view;

import javax.persistence.Persistence;

public class TesteConexao {
    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
    }
}