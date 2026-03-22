# Sistema de Funcionários com JPA, Reflection e CRUD

Projeto desenvolvido em Java com o objetivo de representar diferentes tipos de funcionários utilizando orientação a objetos, herança e persistência de dados com JPA (Hibernate).

O sistema também utiliza Reflection para geração automática de comandos SQL e realiza operações completas de CRUD (Create, Read, Update, Delete) com exibição dos comandos executados.

## Objetivo

O projeto tem como objetivo:

- Representar funcionários com diferentes perfis (Júnior, Pleno e Sênior)
- Aplicar herança e sobrescrita de métodos
- Utilizar anotações personalizadas para mapear entidades
- Gerar comandos SQL automaticamente via Reflection
- Realizar operações CRUD com banco de dados Oracle
- Exibir os comandos SQL executados em cada operação

## Estrutura do Projeto

O sistema é dividido nas seguintes camadas:

- **Entity**: Classes que representam os funcionários
- **DAO**: Responsável pelas operações no banco de dados
- **View**: Classes de execução/teste do sistema
- **Util**: Classe GeradorSQL responsável pela geração dinâmica de SQL
- **Annotations**: Anotações personalizadas (@Descricao, @Coluna, etc.)

## Modelagem

### Funcionário
- Nome
- Horas trabalhadas
- Valor por hora
- Método de cálculo de salário

### Funcionário Júnior
- Herda de Funcionário

### Funcionário Pleno
- Herda de Funcionário

### Funcionário Sênior
- Recebe bônus a cada 15 horas trabalhadas
- Sobrescreve:
  - calcularSalario()
  - imprimirInformacoes()

## Uso de Reflection

A classe `GeradorSQL` utiliza Reflection para:

- Ler anotações das classes
- Identificar nome da tabela
- Identificar colunas automaticamente
- Gerar comandos SQL dinâmicos:

Exemplos:
- SELECT
- INSERT
- UPDATE
- DELETE

## Banco de Dados

- Banco utilizado: Oracle SQL Developer
- ORM: Hibernate (JPA)
- Configuração via Persistence Unit

O sistema realiza integração completa com o banco, permitindo persistência real dos dados.

## Operações CRUD

As seguintes operações foram implementadas:

### CREATE
- Inserção de funcionários no banco
- Exibição do SQL gerado

### READ
- Consulta por ID
- Listagem completa (tabela)

### UPDATE
- Atualização de dados do funcionário
- Exibição do SQL gerado

### DELETE
- Remoção de registros
- Exibição do SQL gerado

## Testes

### Funcionário Sênior
Foram realizados testes completos de CRUD utilizando a classe:

- ViewFuncionarioSenior

Incluindo:
- CREATE
- READ
- UPDATE
- DELETE

Com exibição dos comandos SQL e alterações no banco.

### Funcionários Júnior e Pleno
Foram realizados testes de CRUD dentro de suas respectivas classes:

- ViewFuncionarioJunior
- ViewFuncionarioPleno

### Tabela de Funcionários
- ViewTabelaFuncionario realiza:
  - SELECT geral
  - Exibição formatada em tabela

 ## Evidências de Execução

 ### CREATE


 ### READ


 ### UPDATE


 ### DELETE


## Como Executar

1. Configurar o banco Oracle
2. Ajustar o persistence.xml
3. Executar as classes:

- ViewFuncionarioSenior
- ViewFuncionarioJunior
- ViewFuncionarioPleno
- ViewTabelaFuncionario