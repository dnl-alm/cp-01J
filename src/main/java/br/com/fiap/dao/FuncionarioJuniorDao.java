package br.com.fiap.dao;

import br.com.fiap.entity.Funcionario;
import br.com.fiap.entity.FuncionarioJunior;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNaoEncontradoException;

import java.util.List;

public interface FuncionarioJuniorDao {

    void cadastrar(FuncionarioJunior funcionarioJunior);

    void atualizar(FuncionarioJunior funcionarioJunior) throws IdNaoEncontradoException;

    void remover(int id) throws IdNaoEncontradoException;

    FuncionarioJunior buscarPorId(int id) throws IdNaoEncontradoException;

    List<FuncionarioJunior> buscarTodos();

    void commit() throws CommitException;

}
