package br.com.fiap.util;

import br.com.fiap.annotation.Descricao;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;

public class GeradorSQL {

    public static void gerarSelect(Object obj) {

        Class<?> classe = obj.getClass().getSuperclass();

        Field[] atributos = classe.getDeclaredFields();
        System.out.println("\nAtributos:");

        StringBuilder sql = new StringBuilder("SELECT ");

        for (Field f : atributos) {

            System.out.println(f.getName() + " " + f.getType());

            if (f.isAnnotationPresent(Column.class)) {

                Column col = f.getAnnotation(Column.class);

                System.out.println("Coluna: " + col.name());

                sql.append(col.name()).append(", ");
            }

            if (f.isAnnotationPresent(Descricao.class)) {
                Descricao desc = f.getAnnotation(Descricao.class);
                System.out.println("Descrição: " + desc.descricao());
            }
        }

        sql.setLength(sql.length() - 2);

        if (classe.isAnnotationPresent(Table.class)) {

            Table tabela = classe.getAnnotation(Table.class);

            sql.append(" FROM ").append(tabela.name());

            System.out.println("\n--- SQL GERADO ---");
            System.out.println(sql);
        }

        if (classe.isAnnotationPresent(Descricao.class)) {
            Descricao desc = classe.getAnnotation(Descricao.class);
            System.out.println("Descrição da tabela: " + desc.descricao());
        }
    }

}
