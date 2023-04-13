package prueba;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {
		int[] rotacion = generaRotacion();

		String mensaje = "Nos vemos el lunes 24 a las 18hs en Plaza Mitre";
		
		String encriptado = transposicion(mensaje, rotacion);
		System.out.println("Mensaje encriptado:" + encriptado);

}

	public static String transposicion(String mensaje, int[] rotacion) {
		String encriptado = "";
		char[] aux = new char[rotacion.length];
		int i, j = 0, k;

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