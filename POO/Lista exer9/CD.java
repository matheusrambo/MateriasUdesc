
public class CD implements ItemDeBiblioteca {
	private String titulo;
	private String nomeArtista;
	private int duracao;
	private int numMusicas;
	private int anoGravacao;
	private String localizacao;
	private boolean estaEmprestado;
	public CD() {
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

	public String getNomeArtista() {
		return nomeArtista;
	}

	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getNumMusicas() {
		return numMusicas;
	}

	public void setNumMusicas(int numMusicas) {
		this.numMusicas = numMusicas;
	}

	public int getAnoGravacao() {
		return anoGravacao;
	}

	public void setAnoGravacao(int anoGravacao) {
		this.anoGravacao = anoGravacao;
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
