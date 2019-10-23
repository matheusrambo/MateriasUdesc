package dto;

public class Produto {
    private TipoProduto tipoProduto;
    private int tamanho,estoque;
    private String nome,marca;
    private float preco;
    private static int contadorProdutos = 0;
    private final int codigo;

    public Produto(TipoProduto tipoProduto, int tamanho, int estoque, String nome, String marca, float preco) {
        this.tipoProduto = tipoProduto;
        this.tamanho = tamanho;
        this.estoque = estoque;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        contadorProdutos++;
        this.codigo = contadorProdutos;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return   " Código: " + codigo + ", Nome: " + nome + ", Marca: " + marca + ", Tamanho: " + tamanho + ", Preço: " + preco + ", Estoque: " + estoque + ", tipoProduto: " + tipoProduto;
    }

    
    
}
