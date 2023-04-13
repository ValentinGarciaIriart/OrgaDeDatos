package preuba;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {

		int[] rotacion = generaRotacion();

		Scanner scan = new Scanner(System.in);

		String mensaje = "Nos vemos el lunes 24 a las 18hs en Plaza Mitre";

		String encriptado = transposicion(mensaje, rotacion);
		System.out.println("Mensaje encriptado:" + encriptado);

		encriptado = menu(encriptado);
		System.out.println(encriptado);

		rotacion = generaRotacion();
		encriptado = transposicion(encriptado, rotacion);
		encriptado = menu(encriptado);
	}

	public static String menu(String mensaje) {
		Scanner scan = new Scanner(System.in);
		String encriptado = "";
		System.out.println("Ingrese opcion: '1':cesar con salto / '2': cesar con salto y clave / '3': confederados");
		int opcion = scan.nextInt();
		switch (opcion) {
		case 1:
			encriptado = cesarSalto(mensaje);
			break;
		case 2:
			encriptado = cesarSaltoClave(mensaje);
			break;
		case 3:
			encriptado = confederados(mensaje);
			break;
		default:
			System.out.println("Ingrese una opcion correcta");
		}
		return encriptado;
	}

	public static String cesarSalto(String mensaje) {
		String encriptado = "";
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese alfabeto completo ( [A..Z] [a..z] ) o incompleto ([a..z])");
		char alfabeto = scan.nextLine().charAt(0);
		System.out.println("Ingrese desplazamiento a derecha o izquierda");
		char desplazamiento = scan.nextLine().charAt(0);
		System.out.println("Ingrese salto");
		int saltoOrig = scan.nextInt();
		String abecedario = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String abecedario2 = "abcdefghijklmnopqrstuvwxyz";
		if (alfabeto == 'i' || alfabeto == 'I') {
			encriptado = encriptacionCesarSalto(mensaje, desplazamiento, saltoOrig, abecedario2);
			desencriptadoCesarSalto(encriptado, abecedario2, desplazamiento, saltoOrig);
		} else {
			encriptado = encriptacionCesarSalto(mensaje, desplazamiento, saltoOrig, abecedario);
			desencriptadoCesarSalto(encriptado, abecedario, desplazamiento, saltoOrig);
		}
		return encriptado;
	}

	public static String encriptacionCesarSalto(String mensaje, char desplazamiento, int salto, String abecedario) {
		String encriptado = "";
		int j;
		for (int i = 0; i < mensaje.length(); i++) {
			char letra = caracterConTilde(mensaje.charAt(i));
			if (verificaNoLetra(letra))
				encriptado += letra;
			else {
				j = 0;
				while (j < abecedario.length() && letra != abecedario.charAt(j))
					j++;
				if (desplazamiento == 'i' || desplazamiento == 'I')
					letra = buscaLetra(j, -salto, abecedario);
				else
					letra = buscaLetra(j, salto, abecedario);
				encriptado += letra;

			}
		}
		// System.out.println("Encriptado Cesar: "+encriptado);
		return encriptado;
	}

	public static String cesarSaltoClave(String mensaje) {
		String encriptado = "";
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese clave");
		String clave = scan.nextLine();
		System.out.println("Ingrese desplazamiento: i:izquierda | d:derecha");
		char desplazamiento = scan.nextLine().charAt(0);
		System.out.println("Ingrese salto");
		int salto = scan.nextInt();

		String abecedario = "abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String abecedarioCesar = generaAbecedarioCesarSaltoClave(clave, salto, desplazamiento, abecedario);

		// ENCRIPTADO
		for (int i = 0; i < mensaje.length(); i++) {
			char letra = caracterConTilde(mensaje.charAt(i));
			boolean caracterEspecial = verificaNoLetra(mensaje.charAt(i));
			if (caracterEspecial) {
				encriptado += letra;
			} else {
				int j = 0;
				while (letra != abecedario.charAt(j))
					j++;
				encriptado += abecedarioCesar.charAt(j);
			}
		}
		System.out.println("Encriptado con Cesar Modificado:" + encriptado);

		desencriptadoCesarSaltoClave(encriptado, abecedarioCesar, abecedario);
		return encriptado;
	}

	public static String confederados(String mensaje) {
		Scanner scan = new Scanner(System.in);
	
		System.out.println("Ingrese clave");
		String claveAuxiliar = scan.nextLine();
		char[] clave = pasajeArray(claveAuxiliar);
		
		String abecedarioCompleto="abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; //ABECEDARIO COMPLETO
		String abecedarioIncompleto="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String encriptado=encriptacionConfederados(mensaje,clave,abecedarioCompleto);
		desencriptacionConfederados(encriptado,clave,abecedarioCompleto);

		return encriptado;
	}
	public static String encriptacionConfederados(String mensaje,char[] clave, String abecedario) {
		String encriptado="",claveMapeada="";
		int i,cont=0,auxMensaje,auxClave;
		for(i=0;i<mensaje.length();i++) {
			char letra = caracterConTilde(mensaje.charAt(i));
			boolean caracterEspecial = verificaNoLetra(mensaje.charAt(i));
			if (caracterEspecial) {
				encriptado += letra;
				claveMapeada+=letra;
			}else {
				auxMensaje=buscaPos(letra,abecedario);
				auxClave=buscaPos(clave[cont],abecedario);
				letra=getLetraEncriptada(auxMensaje,auxClave,abecedario);
				encriptado+=letra;
				claveMapeada+=clave[cont];
				cont++;
				
			}
			if (cont == clave.length)
				cont = 0;
		}
		System.out.println("Encriptado Confederados sin Ñ:" + encriptado);
		System.out.println("Clave Mapeada:                "+claveMapeada);
		System.out.println("Mensaje:                      "+mensaje);
		return encriptado;
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

	public static char buscaLetra(int posLetra, int salto, String abecedario) {
		int auxLetra = posLetra + salto;
		if (auxLetra < 0) // si se va para atras por ser desplazamiento izquierda
			auxLetra += abecedario.length();
		else if (auxLetra >= abecedario.length()) // si se pasa por ser desplazamiento derecha
			auxLetra -= abecedario.length();
		return abecedario.charAt(auxLetra);

	}

	public static char caracterConTilde(char letra) {

		switch (letra) {
		case 'á':
			return 'a';
		case 'é':
			return 'e';

		case 'í':
			return 'i';

		case 'ó':
			return 'o';

		case 'ú':
			return 'u';

		case 'Á':
			return 'A';

		case 'É':
			return 'E';

		case 'Í':
			return 'I';

		case 'Ó':
			return 'O';

		case 'Ú':
			return 'U';

		default:
			return letra;

		}

	}

	public static boolean verificaNoLetra(char letra) {
		boolean rta;
		int auxLetra = (int) letra;
		switch (auxLetra) {
		case 32: {
			rta = true;
			break;
		}
		case 46: {
			rta = true;
			break;
		}
		case 59: {
			rta = true;
			break;
		}
		case 44: {
			rta = true;
			break;
		}
		default:
			rta = false;
		}
		return rta;
	}

	public static void desencriptadoCesarSalto(String encriptado, String abecedario, char desplazamiento, int salto) {
		String desencriptado = "";
		// System.out.println("Para que veas que no mande el mensaje directamente, esto
		// es lo encriptado: " + encriptado);
		int j;
		for (int i = 0; i < encriptado.length(); i++) {
			char letra = caracterConTilde(encriptado.charAt(i));
			if (verificaNoLetra(letra))
				desencriptado += letra;
			else {
				j = 0;
				while (j < abecedario.length() && letra != abecedario.charAt(j))
					j++;
				if (desplazamiento == 'i' || desplazamiento == 'I')
					letra = buscaLetra(j, salto, abecedario);
				else
					letra = buscaLetra(j, -salto, abecedario);
				desencriptado += letra;

			}
		}
		// System.out.println("Desencriptado: "+ desencriptado);

	}

	public static String generaAbecedarioCesarSaltoClave(String clave, int salto, char desplazamiento,
			String abecedario) {
		String abecedarioAux = abecedario;
		String aux = "";
		boolean bool;
		int i;
		String rta = "";
		if (String.valueOf(desplazamiento).equalsIgnoreCase("d")) {
			aux += clave;
			aux += abecedarioAux;
			abecedarioAux = "";
			for (i = salto; i > 0; i--) {
				abecedarioAux += abecedario.charAt(abecedario.length() - i);
			}
			abecedarioAux += aux;
			for (i = 0; i < abecedarioAux.length(); i++) {
				if (noEsta(abecedarioAux.charAt(i), rta) == true)
					rta += abecedarioAux.charAt(i);
			}
		} else {
			for (i = salto; i < abecedario.length(); i++) {
				aux += abecedario.charAt(i);
			}
			for (i = clave.length() - 1; i >= 0; i--) {

				aux += clave.charAt(i);
			}
			for (i = 0; i < salto; i++) {
				aux += abecedario.charAt(i);
			}
			for (i = 0; i < aux.length(); i++) {
				if (noEsta(aux.charAt(i), rta) == true)
					rta += aux.charAt(i);
			}
		}
		return rta;

	}
	
	public static boolean noEsta(char letra, String abecedarioAux) {
		int i=0;
		if(abecedarioAux.isBlank()) 
			return true;
		else {
		while(i<abecedarioAux.length() && letra!=abecedarioAux.charAt(i))
			i++;
		if(i>=abecedarioAux.length())
			return true;
		else
			return false;
		}
	}
	public static void desencriptadoCesarSaltoClave(String encriptado, String abecedarioCesar, String abecedario) {
		String desencriptado = "";
		for (int i = 0; i < encriptado.length(); i++) {
			char letra = caracterConTilde(encriptado.charAt(i));
			boolean caracterEspecial = verificaNoLetra(encriptado.charAt(i));
			if (caracterEspecial) {
				desencriptado += letra;
			} else {
				int j = 0;
				while (letra != abecedarioCesar.charAt(j))
					j++;
				desencriptado += abecedario.charAt(j);
			}
		}
		System.out.println("Desencriptado con Cesar Modificado:" + desencriptado);

	}
	public static void desencriptacionConfederados(String encriptado,char clave[],String abecedario) {
		String desencriptado="";
		int i,cont=0,auxEncriptacion,auxClave;
		for(i=0;i<encriptado.length();i++) {
			char letra = caracterConTilde(encriptado.charAt(i));
			boolean caracterEspecial = verificaNoLetra(encriptado.charAt(i));
			if (caracterEspecial) 
				desencriptado += letra;
			else {
				auxEncriptacion=buscaPos(letra,abecedario);
				auxClave=buscaPos(clave[cont],abecedario);
				letra=getLetraDesencriptada(auxEncriptacion,auxClave,abecedario);
				desencriptado+=letra;
				cont++;
				
			}
			if (cont == clave.length)
				cont = 0;
		}
		System.out.println("Desencriptacion:              "+desencriptado);
		
	}
	public static char getLetraDesencriptada(int auxEncriptacion,int auxClave,String abecedario) {
		int auxLetra=auxEncriptacion-auxClave-1;
		if(auxLetra<abecedario.length() && auxLetra>=0) // nunca va a ser mas grande
			return abecedario.charAt(auxLetra);
		else {
			auxLetra+=(abecedario.length());
			return abecedario.charAt(auxLetra);
			
		}
	}

	public static char getLetraEncriptada(int auxMensaje,int auxClave,String abecedario) {
		int auxLetra=auxMensaje+auxClave+1;
		if(auxLetra<abecedario.length())
			return abecedario.charAt(auxLetra);
		else {
			auxLetra-=(abecedario.length());
			return abecedario.charAt(auxLetra);
			
		}
	}
	
	public static int buscaPos(char caracter,String abecedario) {
		int i=0;
		while(caracter!=abecedario.charAt(i))
			i++;
		return i;
		
	}
	public static char[] pasajeArray(String clave) {
		char[] aux = new char[clave.length()];
		for (int i = 0; i < clave.length(); i++) {
			aux[i] = clave.charAt(i);
		}
		return aux;

	}
}
