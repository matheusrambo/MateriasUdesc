public class Estudante {
	public String nome;
	public float notas[] = new float[4];
	public float media;
	public void calcMedia() {
		float soma = 0;
		for(int i=0;i<4;i++) {
			soma += this.notas[i];
		}
		this.media = soma/4;
	}
	public void setNota(int pos,float nota) {
		notas[pos] = nota;
	}
	public float getMedia() {
		calcMedia();
		return this.media;
	}
	public String getNome() {
		return this.nome;
	}
	public float[] getNotas() {
		return notas;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setMedia(float media) {
		this.media = media;
	}
}
