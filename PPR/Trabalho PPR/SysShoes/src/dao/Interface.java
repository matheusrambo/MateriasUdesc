package dao;

import dto.Cliente;
import dto.Funcionario;
import dto.Produto;

public interface Interface {
    public abstract boolean efetuarVenda(Funcionario f, Produto p);
    public abstract boolean cadastrarFuncionario(Funcionario f);
    public abstract boolean removerFuncionario(Funcionario f);
    public abstract boolean cadastrarCliente(Cliente c);
    public abstract boolean cadastrarProduto(Produto p);
    public abstract boolean removerProduto(Produto p);
}
