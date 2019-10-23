
public class Estudante {
	public String nome;
	public float notas[];
	public float media;
	public Estudante(String nome, float[] notas2) {
		this.nome = nome;
		this.notas = notas2;
		this.calcMedia();
	}
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
		return this.media;
	}
	public String getNome() {
		return this.nome;
	}
}