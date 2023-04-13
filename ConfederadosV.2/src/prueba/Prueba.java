package prueba;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese mensaje a encriptar");
		String mensaje = scan.nextLine();
		System.out.println("Ingrese clave");
		String claveAuxiliar = scan.nextLine();
		char[] clave = pasajeArray(claveAuxiliar);
		
		String abecedarioCompleto="abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; //ABECEDARIO COMPLETO
		String abecedarioIncompleto="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String encriptado=encriptacionConfederados(mensaje,clave,abecedarioCompleto);
		desencriptacion(encriptado,clave,abecedarioCompleto);

	}

	public static void desencriptacion(String encriptado,char clave[],String abecedario) {
		String desencriptado="";
		int i,cont=0,auxEncriptacion,auxClave;
		for(i=0;i<encriptado.length();i++) {
			char letra = caracterTilde(encriptado.charAt(i));
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
	public static String encriptacionConfederados(String mensaje,char[] clave, String abecedario) {
		String encriptado="",claveMapeada="";
		int i,cont=0,auxMensaje,auxClave;
		for(i=0;i<mensaje.length();i++) {
			char letra = caracterTilde(mensaje.charAt(i));
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


}
