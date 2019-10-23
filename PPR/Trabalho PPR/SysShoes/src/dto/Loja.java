package dto;

public class Loja {
    private static Produto[] produtos = new Produto[4];
    private static Funcionario[] funcionarios = new Funcionario[2];
    private static Cliente[] clientes = new Cliente[4];
    
    private static Loja instance;
    
    private static void inicializaGerente(){
        TipoFuncionario tipoFuncionario = TipoFuncionario.GERENTE;
        Endereco endereco = new Endereco("Rua Otto Nass", "Bom Retiro", "102", "202 bloco C");
        if ( Loja.funcionarios[0] == null )
            Loja.funcionarios[0] = new Funcionario("Gerente","391-458-115.01","(47) 94328-1420", endereco, (float) 1254.99, "admin", "admin", tipoFuncionario);
    }
    
    private Loja() {
    }
    
    public static synchronized Loja getInstance()
    {
        if ( instance == null ){
            instance = new Loja();
            inicializaGerente();
        }
        return instance;
    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public void setClientes(Cliente[] clientes) {
        Loja.clientes = clientes;
    }
    
    public Produto[] getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto[] produtos) {
        Loja.produtos = produtos;
    }

    public Funcionario[] getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Funcionario[] funcionarios) {
        Loja.funcionarios = funcionarios;
    }

}
