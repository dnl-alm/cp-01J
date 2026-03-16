package br.com.fiap.entity;

import br.com.fiap.annotation.Descricao;

import javax.persistence.*;

@Entity
@Table(name="TDS_TB_FUNCIONARIO")
@SequenceGenerator(name="funcionario", sequenceName = "SQ_TDS_TB_FUNCIONARIO", allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_FUNC")
@Descricao(descricao = "Tabela de Funcionários")
public abstract class Funcionario {

    @Id
    @Column(name="id_funcionario")
    private Integer id;

    @Column(name="nm_funcionario", nullable = false, length = 100)
    private String nome;

    @Column(name = "qt_horas", nullable = false)
    private Integer horasTrabalhadas;

    @Column(name = "vl_hora", nullable = false)
    private Double valorHoraTrabalhada;

    public Funcionario() {
    }

    public Funcionario(int id, String nome, int horasTrabalhadas, double valorHoraTrabalhada) {
        this.id = id;
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHoraTrabalhada = valorHoraTrabalhada;
    }

    public Double calcularSalario(){
        return this.horasTrabalhadas * this.valorHoraTrabalhada;
    };

    public void imprimirInformacoes () {
        System.out.println("Nome: " + nome);
        System.out.println("Horas trabalhadas: " + horasTrabalhadas);
        System.out.println("Valor por hora: R$ " + valorHoraTrabalhada);
        System.out.println("Salário final: R$ " + calcularSalario());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(Integer horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Double getValorHoraTrabalhada() {
        return valorHoraTrabalhada;
    }

    public void setValorHoraTrabalhada(Double valorHoraTrabalhada) {
        this.valorHoraTrabalhada = valorHoraTrabalhada;
    }
}
