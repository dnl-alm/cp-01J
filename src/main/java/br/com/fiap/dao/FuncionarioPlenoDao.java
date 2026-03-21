package br.com.fiap.dao;

import br.com.fiap.entity.FuncionarioPleno;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNaoEncontradoException;

import java.util.List;

public interface FuncionarioPlenoDao {

    void cadastrar(FuncionarioPleno funcionarioPleno);

    void atualizar(FuncionarioPleno funcionarioPleno) throws IdNaoEncontradoException;

    void remover(int id) throws IdNaoEncontradoException;

    FuncionarioPleno buscarPorId(int id) throws IdNaoEncontradoException;

    List<FuncionarioPleno> buscarTodos();

    void commit() throws CommitException;

}
