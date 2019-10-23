package negocio;

import dao.Collections;
import dao.Interface;
import dto.Cliente;
import dto.Funcionario;
import dto.Produto;

public class Negocio {
    static Interface car = Collections.getInstance();
    
    public static boolean efetuarVenda(Funcionario f, Produto p){
        return car.efetuarVenda(f, p);
    }
    
    public static boolean cadastrarFuncionario(Funcionario f){
        return car.cadastrarFuncionario(f);
    }
    
    public static boolean removerFuncionario(Funcionario f){
        return car.removerFuncionario(f);
    }
    
    public static boolean cadastrarCliente(Cliente c){
        return car.cadastrarCliente(c);
    }
    
    public static boolean cadastrarProduto(Produto p){
        return car.cadastrarProduto(p);
    }
    
    public static boolean removerProduto(Produto p){
        return car.removerProduto(p);
    }
}
