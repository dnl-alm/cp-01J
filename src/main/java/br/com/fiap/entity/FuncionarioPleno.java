package br.com.fiap.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PLENO")
public class FuncionarioPleno extends Funcionario {

    public FuncionarioPleno() {
    }

    public FuncionarioPleno(String nome, int horasTrabalhadas, double valorHoraTrabalhada) {
        super(nome, horasTrabalhadas, valorHoraTrabalhada);
    }
}
