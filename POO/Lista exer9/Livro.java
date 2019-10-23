
public class Livro implements ItemDeBiblioteca{
	private String titulo;
	private String autor;
	private int numPaginas;
	private int anoEdicao;
	private String localizacao;
	private boolean estaEmprestado;
	public Livro() {
		super();
		estaEmprestado = false;
	}
	
	public boolean estaEmprestado() {
		return estaEmprestado;
	}
	public boolean empresta() {
		if(this.estaEmprestado==true) {
			return false;
		}else {
			this.estaEmprestado = true;
			return true;
		}
	}
	public void devolve() {
		this.estaEmprestado = false;
	}
	public String localizacao() {
		return localizacao;
	}
	public String titulo() {
		return titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public int getAnoEdicao() {
		return anoEdicao;
	}

	public void setAnoEdicao(int anoEdicao) {
		this.anoEdicao = anoEdicao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public boolean isEstaEmprestado() {
		return estaEmprestado;
	}

	public void setEstaEmprestado(boolean estaEmprestado) {
		this.estaEmprestado = estaEmprestado;
	}
	
}