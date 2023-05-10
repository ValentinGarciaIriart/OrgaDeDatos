package prueba;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {
		//String abecedario="abcdefghijklmnopqrstuvwxyz ";
		//String abecedario="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ ";
		//String abecedario="abcdefghijklmnñopqrstuvwxyz ";
		//String abecedario="abcdefghijklmnopqrstuvwxyz ";
		String abecedario;
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese mensaje a encriptar");
		String mensaje= scan.nextLine();
		System.out.println("Ingrese una clave");
		String claveAuxiliar = scan.nextLine();
		char[] clave;
		String encriptado="";
		System.out.println("Ingrese tipo en encriptado:");
		System.out.println("1(Abecedario minuscula sin ñ) | 2 (Abecedario mayuscula sin ñ)");
		System.out.println("3(Abecedario minuscula con ñ) | 4 (Abecedario mayuscula con ñ)");
		
		int op = scan.nextInt();
		switch (op) {
		case 1:{
			abecedario="abcdefghijklmnopqrstuvwxyz ";
			clave = pasajeArray(claveAuxiliar.toLowerCase());
			System.out.println(clave);
			encriptado=encriptacionConfederados(mensaje.toLowerCase(),clave,abecedario);
			desencriptacion(encriptado,clave,abecedario);
			break;
		}
		case 2:{
			abecedario="ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
			clave = pasajeArray(claveAuxiliar.toUpperCase());
			encriptado=encriptacionConfederados(mensaje.toUpperCase(),clave,abecedario);
			desencriptacion(encriptado,clave,abecedario);
			break;
		}
		case 3:{
			abecedario="abcdefghijklmnñopqrstuvwxyz ";
			clave = pasajeArray(claveAuxiliar.toLowerCase());
			encriptado=encriptacionConfederados(mensaje.toLowerCase(),clave,abecedario);
			desencriptacion(encriptado,clave,abecedario);
			break;
		}
		case 4:{
			abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ ";
			clave = pasajeArray(claveAuxiliar.toUpperCase());
			encriptado=encriptacionConfederados(mensaje.toUpperCase(),clave,abecedario);
			desencriptacion(encriptado,clave,abecedario);
			break;
		}
		default: System.out.println("INGRESE UN TIPO VALIDO");
		
		}
	
	
	}
	
	public static void desencriptacion(String encriptado,char clave[],String abecedario) {
		String desencriptado="";
		int i,cont=0,auxEncriptacion,auxClave;
		for(i=0;i<encriptado.length();i++) {
			char letra = caracterTilde(encriptado.charAt(i));
			boolean caracterEspecial = verificaNoLetra(encriptado.charAt(i));
			if (caracterEspecial && letra!=' ') 
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
		System.out.println("Desencriptacion: "+desencriptado);
		
	}
	public static String encriptacionConfederados(String mensaje,char[] clave, String abecedario) {
		String encriptado="",claveMapeada="";
		int i,cont=0,auxMensaje,auxClave;
		for(i=0;i<mensaje.length();i++) {
			char letra = caracterTilde(mensaje.charAt(i));
			boolean caracterEspecial = verificaNoLetra(mensaje.charAt(i));
			if (caracterEspecial && letra!=' ') {
				encriptado += letra;
				claveMapeada+=" ";
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
		System.out.println("Encriptado Confederados:" + encriptado);
		System.out.println("Clave Mapeada:          "+claveMapeada);
		System.out.println("Mensaje:                "+mensaje);
		return encriptado;
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

	public static char caracterTilde(char letra) {
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
		case 32: { // espacio
			rta = true;
			break;
		}
		case 46: { // .
			rta = true;
			break;
		}
		case 59: {// ;
			rta = true;
			break;
		}
		case 44: { // ,
			rta = true;
			break;
		}
		default:
			rta = false;
		}
		return rta;
	}
}
