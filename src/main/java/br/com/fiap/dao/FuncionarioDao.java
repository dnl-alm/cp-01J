package br.com.fiap.dao;

import br.com.fiap.entity.Funcionario;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNaoEncontradoException;

import java.util.List;

public interface FuncionarioDao {

    void cadastrar(Funcionario funcionario);

    void atualizar(Funcionario funcionario) throws IdNaoEncontradoException;

    void remover(int id) throws IdNaoEncontradoException;

    Funcionario buscarPorId(int id) throws IdNaoEncontradoException;

    List<Funcionario> buscarTodos();

    void commit() throws CommitException;

}
