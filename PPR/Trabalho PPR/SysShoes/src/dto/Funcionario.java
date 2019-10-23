package dto;

import java.util.Arrays;


public class Funcionario {
    private String nome,cpf,telefone;
    private Endereco endereco;
    private float salario;
    private String login;
    private String senha;
    private TipoFuncionario tipoFuncionario;
    private Venda[] vendas = new Venda[10];

    public Funcionario(String nome, String cpf, String telefone, Endereco endereco, float salario, String login, String senha, TipoFuncionario tipoFuncionario) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.salario = salario;
        this.login = login;
        this.senha = senha;
        this.tipoFuncionario = tipoFuncionario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public Venda[] getVendas() {
        return vendas;
    }

    public void setVendas(Venda[] vendas) {
        this.vendas = vendas;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Telefone: " + telefone + endereco + ", Salario: " + salario + ", Login: " + login + ", Senha: " + senha + ", tipoFuncionario: " + tipoFuncionario + ", Vendas: " + Arrays.toString(vendas) ;
    }
    
    
    
}
