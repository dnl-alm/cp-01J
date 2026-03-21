package br.com.fiap.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("JUNIOR")
public class FuncionarioJunior extends Funcionario {

    public FuncionarioJunior() {
    }

    public FuncionarioJunior(String nome, int horasTrabalhadas, double valorHoraTrabalhada) {
        super(nome, horasTrabalhadas, valorHoraTrabalhada);
    }


}
