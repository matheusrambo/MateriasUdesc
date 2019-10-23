import java.util.Scanner;

public class Biblioteca {
	private String nome;
	private static ItemDeBiblioteca[] itens = new ItemDeBiblioteca[50];
	private static int quantItens=0;
	private static Scanner in;
	public static void main(String args[]) {
		int op;
		String item;
		do {
			System.out.println("Menu:");
			System.out.println("1 - Catalogar livro");
			System.out.println("2 - Catalogar CD");
			System.out.println("3 - Emprestar item");
			System.out.println("4 - Devolver item");
			System.out.println("5 - Mostrar localização");
			System.out.println("6 - Mostrar detalhes do item");
			System.out.println("0 - Sair");
			op = in.nextInt();
			switch(op) {
				case 1:
					catalogarLivro();
					break;
				case 2:
					catalogarCD();
					break;
				case 3:
					System.out.println("Digite o nome do item:");
					item = in.next();
					emprestarItem(item);
					break;
				case 4:
					System.out.println("Digite o nome do item:");
					item = in.next();
					devolverItem(item);
					break;
				case 5:
					System.out.println("Digite o nome do item:");
					item = in.next();
					mostrarLocalizacao(item);
					break;
				case 6:
					System.out.println("Digite o nome do item:");
					item = in.next();
					mostrarDetalhesItem(item);
					break;
			}
		}while(op!=0);
	}
	public static void catalogarLivro() {
		Livro livro = new Livro();
		System.out.println("Digite o nome do livro:");
		livro.setTitulo(in.next());
		System.out.println("Digite o autor do livro:");
		livro.setAutor(in.next());
		System.out.println("Digite o numero de páginas do livro:");
		livro.setNumPaginas(in.nextInt());
		System.out.println("Digite o ano da edição do livro:");
		livro.setAnoEdicao(in.nextInt());
		System.out.println("Digite a localização do livro:");
		livro.setLocalizacao(in.next());
		itens[quantItens] = livro;
		quantItens++;
	}
	public static void catalogarCD() {
		CD cd = new CD();
		System.out.println("Digite o nome do CD:");
		cd.setTitulo(in.next());
		System.out.println("Digite o nome do artista do CD:");
		cd.setNomeArtista(in.next());
		System.out.println("Digite a duração do CD:");
		cd.setDuracao(in.nextInt());
		System.out.println("Digite o número de músicas do CD:");
		cd.setNumMusicas(in.nextInt());
		System.out.println("Digite o ano de gravação do CD:");
		cd.setAnoGravacao(in.nextInt());
		System.out.println("Digite a localização do CD:");
		cd.setLocalizacao(in.next());
		itens[quantItens] = cd;
		quantItens++;
		
	}
	public static void emprestarItem(String titulo) {
		for(int i=0;i<quantItens;i++) {
			if(itens[i].titulo()==titulo) {
				itens[i].empresta();
			}
		}
	}
	public static void devolverItem(String titulo) {
		for(int i=0;i<quantItens;i++) {
			if(itens[i].titulo()==titulo) {
				itens[i].devolve();
			}
		}
	}
	public static void mostrarLocalizacao(String titulo) {
		for(int i=0;i<quantItens;i++) {
			if(itens[i].titulo()==titulo) {
				itens[i].localizacao();
			}
		}
	}
	public static void mostrarDetalhesItem(String titulo) {
		for(int i=0;i<quantItens;i++) {
			if(itens[i].titulo()==titulo) {
				if(itens[i] instanceof CD) {
					CD cd = new CD();
					cd = itens[i];
				}
			}
		}
	}
}
