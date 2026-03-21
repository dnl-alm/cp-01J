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

    public String gerarInsert(Object obj) {

        Class<?> clazz = obj.getClass();

        Table tabela = getTabela(clazz);

        StringBuilder colunas = new StringBuilder();
        StringBuilder valores = new StringBuilder();

        List<Field> campos = getTodosCampos(clazz);

        for (Field campo : campos) {

            if (campo.isAnnotationPresent(Column.class)) {

                Column col = campo.getAnnotation(Column.class);

                colunas.append(col.name()).append(", ");

                valores.append("?").append(", ");
            }
        }

        colunas.delete(colunas.length() - 2, colunas.length());
        valores.delete(valores.length() - 2, valores.length());

        return "INSERT INTO " + tabela.name() +
                " (" + colunas + ") VALUES (" + valores + ")";
    }

    public String gerarUpdate(Object obj, String where) {

        Class<?> clazz = obj.getClass();

        Table tabela = getTabela(clazz);

        StringBuilder sql = new StringBuilder("UPDATE ");

        sql.append(tabela.name()).append(" SET ");

        List<Field> campos = getTodosCampos(clazz);

        for (Field campo : campos) {

            if (campo.isAnnotationPresent(Column.class)) {

                Column col = campo.getAnnotation(Column.class);

                sql.append(col.name()).append(" = ?, ");
            }
        }

        sql.delete(sql.length() - 2, sql.length());

        sql.append(" WHERE ").append(where);

        return sql.toString();
    }

    public String gerarDelete(Object obj, String where) {

        Class<?> clazz = obj.getClass();

        Table tabela = getTabela(clazz);

        return "DELETE FROM " + tabela.name() +
                " WHERE " + where;
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

}
