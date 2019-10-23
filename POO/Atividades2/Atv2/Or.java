
public class Or {
	private boolean resultado;
	
	public void or(boolean arrayBool[]) {
		int cont = 0;
		for(int i=0;i<arrayBool.length;i++) {
			if(arrayBool[i] == true) {
				cont++;
			}
		}
		if(cont>0) {
			resultado = true;
		}else {
			resultado = false;
		}
	}
	public void or(int arrayInt[]) {
		int cont = 0;
		for(int i=0;i<arrayInt.length;i++) {
			if(arrayInt[i] == 1) {
				cont++;
			}
		}
		if(cont>0) {
			resultado = true;
		}else {
			resultado = false;
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
