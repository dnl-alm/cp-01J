package br.com.fiap.dao;

import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNaoEncontradoException;

import java.util.List;

public interface FuncionarioSeniorDao {

    void cadastrar(FuncionarioSenior funcionarioSenior);

    void atualizar(FuncionarioSenior funcionarioSenior) throws IdNaoEncontradoException;

    void remover(int id) throws IdNaoEncontradoException;

    FuncionarioSenior buscarPorId(int id) throws IdNaoEncontradoException;

    List<FuncionarioSenior> buscarTodos();

    void commit() throws CommitException;

}
