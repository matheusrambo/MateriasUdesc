
public class And {
	private boolean resultado;
	
	public void and(boolean arrayBool[]) {
		int cont = 0;
		for(int i=0;i<arrayBool.length;i++) {
			if(arrayBool[i] == false) {
				cont++;
			}
		}
		if(cont>0) {
			resultado = false;
		}else {
			resultado = true;
		}
	}
	public void and(int arrayInt[]) {
		int cont = 0;
		for(int i=0;i<arrayInt.length;i++) {
			if(arrayInt[i] == 0) {
				cont++;
			}
		}
		if(cont>0) {
			resultado = false;
		}else {
			resultado = true;
		}
	}
	public boolean bool() {
		return resultado;
	}
	public int num() {
		if(resultado==true) {
			return 1;
		}else {
			return 0;
		}
	}
}
