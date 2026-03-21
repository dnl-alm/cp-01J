package br.com.fiap.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SENIOR")
public class FuncionarioSenior extends Funcionario {

    Double valorBonus = 100.0;

    public FuncionarioSenior() {
    }

    public FuncionarioSenior(String nome, int horasTrabalhadas, double valorHoraTrabalhada) {
        super(nome, horasTrabalhadas, valorHoraTrabalhada);
    }

    @Override
    public Double calcularSalario() {

        Double salarioBase = super.calcularSalario();

        int quantidadeBonus = getHorasTrabalhadas() / 15;

        Double totalBonus = quantidadeBonus * valorBonus;

        return totalBonus + salarioBase;
    }

    @Override
    public void imprimirInformacoes() {
        System.out.println("Id: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Horas trabalhadas: " + getHorasTrabalhadas());
        System.out.println("Valor por hora: R$ " + getValorHoraTrabalhada());
        System.out.println("Salário final: R$ " + calcularSalario());
        System.out.println("Valor do bônus a cada 15 horas trabalhadas: " + this.valorBonus + "\n");
    }
}
