package dto;

public class Venda {
    private String data;
    private Produto produto;

    public Venda(String data, Produto produto) {
        this.data = data;
        this.produto = produto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Data: " + data + ", Produto: " + produto ;
    }
    
    
}
