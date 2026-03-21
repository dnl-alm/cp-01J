package br.com.fiap.util;

import br.com.fiap.annotation.Descricao;
import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.dao.FuncionarioSeniorDao;
import br.com.fiap.dao.FuncionarioSeniorDaoImpl;
import br.com.fiap.entity.Funcionario;
import br.com.fiap.entity.FuncionarioSenior;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GeradorSQL {


    private FuncionarioDao dao;

    public GeradorSQL(FuncionarioDao dao) {
        this.dao = dao;
    }

    private Table getTabela(Class<?> clazz) {

        Class<?> classeAtual = clazz;

        while (classeAtual != null && !classeAtual.isAnnotationPresent(Table.class)) {
            classeAtual = classeAtual.getSuperclass();
        }

        if (classeAtual == null) {
            throw new RuntimeException("Nenhuma classe com @Table encontrada");
        }

        return classeAtual.getAnnotation(Table.class);
    }

    private List<Field> getTodosCampos(Class<?> clazz) {

        List<Field> campos = new ArrayList<>();

        while (clazz != null) {
            for (Field f : clazz.getDeclaredFields()) {
                campos.add(f);
            }
            clazz = clazz.getSuperclass();
        }

        return campos;
    }

    public String gerarSelect(Object obj) {

        Class<?> clazz = obj.getClass();

        Table tabela = getTabela(clazz);

        StringBuilder sql = new StringBuilder("SELECT ");

        List<Field> campos = getTodosCampos(clazz);

        for (Field campo : campos) {
            if (campo.isAnnotationPresent(Column.class)) {
                Column col = campo.getAnnotation(Column.class);
                sql.append(col.name()).append(", ");
            }
        }

        sql.delete(sql.length() - 2, sql.length());
        sql.append(" FROM ").append(tabela.name());

        return sql.toString();
    }

    public void mostrarDescricoes(Object obj) {

        Class<?> clazz = obj.getClass();

        System.out.println("\n=== DESCRIÇÕES ===");

        if (clazz.isAnnotationPresent(Descricao.class)) {
            Descricao d = clazz.getAnnotation(Descricao.class);
            System.out.println("Tabela: " + d.descricao());
        }

        List<Field> campos = getTodosCampos(clazz);

        for (Field campo : campos) {
            if (campo.isAnnotationPresent(Descricao.class)) {
                Descricao d = campo.getAnnotation(Descricao.class);
                System.out.println(campo.getName() + ": " + d.descricao());
            }
        }
    }

    public void mostrarDados() {

        List<Funcionario> lista = dao.buscarTodos();

        if (lista.isEmpty()) {
            System.out.println("\nNenhum dado encontrado.");
            return;
        }

        System.out.println("\n=== TABELA FUNCIONARIO ===");

        List<Field> campos = getTodosCampos(lista.get(0).getClass());

        for (Funcionario f : lista) {
            for (Field campo : campos) {
                try {
                    if (campo.isAnnotationPresent(Column.class)) {
                        campo.setAccessible(true);
                        System.out.print(campo.get(f) + " | ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }

    public void executarTudo() {

        Funcionario funcionarios = new Funcionario();

        String sql = gerarSelect(funcionarios);

        System.out.println("=== SQL GERADO ===");
        System.out.println(sql);

        mostrarDescricoes(funcionarios);

        mostrarDados();
    }

}
