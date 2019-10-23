package dao;

import dto.Cliente;
import dto.Funcionario;
import dto.Loja;
import dto.Produto;
import dto.Venda;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Collections implements Interface{

    private static Collections instance;
    
    private Collections()
    {
    }
    
    public static synchronized Collections getInstance() 
    {
        if ( instance == null )
            instance = new Collections();
        return instance;
    }
    
    @Override
    public boolean efetuarVenda(Funcionario f, Produto p) {

        Venda[] vendas = f.getVendas();
        
        for ( int i = 0 ; i < vendas.length ; i++ )
        {
            if ( vendas[i] == null ){
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();

                Venda vendido = new Venda(dateFormat.format(date),p);
                
                vendas[i] = vendido;
                
                f.setSalario((float) (f.getSalario() + (p.getPreco() * 0.1)));
                
                p.setEstoque(p.getEstoque() - 1);
                
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean cadastrarFuncionario(Funcionario f) {
        Funcionario[] funcionarios = Loja.getInstance().getFuncionarios();
        
        for ( int i = 0 ; i < funcionarios.length ; i++ )
        {
            if ( funcionarios[i] == f )
                return false;
        }
        
        for ( int i = 0 ; i < funcionarios.length ; i++ )
        {
            if ( funcionarios[i] == null )
            {
                funcionarios[i] = f;
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean removerFuncionario(Funcionario f) {
        Funcionario[] funcionarios = Loja.getInstance().getFuncionarios();
        
        for ( int i = 0 ; i < funcionarios.length ; i++ )
        {
            if ( funcionarios[i] == f )
            {
                funcionarios[i] = null;
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean cadastrarCliente(Cliente c) {
        Cliente[] clientes = Loja.getInstance().getClientes();
        
        for ( int i = 0 ; i < clientes.length ; i ++ )
        {
            if ( clientes[i] == c )
                return false;
        }
        
        for ( int i = 0 ; i < clientes.length ; i ++ )
        {
            if ( clientes[i] == null )
            {
                clientes[i] = c;
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean removerProduto(Produto p) {
        Produto[] produtos = Loja.getInstance().getProdutos();
        
        for ( int i = 0 ; i < produtos.length ; i++ )
        {
            if ( produtos[i] == p )
            {
                produtos[i] = null;
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean cadastrarProduto(Produto p) {
        Produto[] produtos = Loja.getInstance().getProdutos();
        
        for ( int i = 0 ; i < produtos.length ; i++ )
        {
            if ( produtos[i] == p )
                return false;
        }
        
        for ( int i = 0 ; i < produtos.length ; i++ )
        {
            if ( produtos[i] == null ){
                produtos[i] = p;
                return true;
            }
        }
        
        return false;
    }
    
}
