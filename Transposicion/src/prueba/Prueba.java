package prueba;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {
		int[] rotacion = generaRotacion();

		String mensaje = "En general, los hombres juzgan más por los ojos que por la inteligencia, pues todos pueden ver, pero pocos comprenden lo que ven.";
		
		String mensaje2= "En general, los hombres.";
		String encriptado = transposicion(mensaje2, rotacion);
		System.out.println("Mensaje encriptado:" + encriptado);

}

	public static String transposicion(String mensaje, int[] rotacion) {
		String encriptado = "";
		char[] aux = new char[rotacion.length];
		int i, j = 0, k;
		System.out.println(rotacion.length);
		boolean validaCero = buscaCero(rotacion);
		if(!validaCero) {
		for (i = 0; i < mensaje.length(); i++) { // corta de leer cuando no hay mas mensaje
			if (j < rotacion.length) { // asi calculo las filas
				aux[j] = mensaje.charAt(i);
				j++;
			} else {
				k = 0;
				while (k < rotacion.length) {
					encriptado += aux[rotacion[k] - 1];
					aux[rotacion[k] - 1] = ' ';
					k++;
				}
				j = 0;
				aux[j] = mensaje.charAt(i);
				j = 1;
				// System.out.println(encriptado);
			}
		}
		// Corto el ciclo pero quedaron caracteres sin asignar (va desde 1 a n-1)
		k = 0;
		while (k < rotacion.length) {
			encriptado += aux[rotacion[k] - 1];
			aux[rotacion[k] - 1] = ' ';
			k++;
		}

		return encriptado;
		}
		else {
			System.out.println("HAY UN 0");
			for (i = 0; i < mensaje.length(); i++) { // corta de leer cuando no hay mas mensaje
				if (j < rotacion.length) { // asi calculo las filas
					aux[j] = mensaje.charAt(i);
					j++;
				} else {
					k = 0;
					while (k < rotacion.length) {
						encriptado += aux[rotacion[k]];
						aux[rotacion[k]] = ' ';
						k++;
					}
					j = 0;
					aux[j] = mensaje.charAt(i);
					j = 1;				
				}
			}
			// Corto el ciclo pero quedaron caracteres sin asignar (va desde 1 a n-1)
			k = 0;
			while (k < rotacion.length) {
				encriptado += aux[rotacion[k]];
				aux[rotacion[k]] = ' ';
				k++;
			}
			return encriptado;
		}
	}

	public static boolean buscaCero(int [] rotacion) {
	int i=0;
		if (rotacion.length==10)
			return true;
		while (i<rotacion.length && rotacion[i]!=0) {
			i++;
		}
		if (rotacion[i]==0)
			return true;
		else
			return false;
	}
	
	
	public static int[] generaRotacion() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese cantidad de columnas (menor o igual a 10)");
		int i = 0, ASCII = 48;
		int cantRotacion = scan.nextInt();
		int[] rotacion = new int[cantRotacion];
		System.out.println("Ingrese Rotacion");
		String auxRotacion = scan.next();
		while (i < cantRotacion) {
			rotacion[i] = auxRotacion.charAt(i) - ASCII;
			i++;
		}
		return rotacion;
	}
}