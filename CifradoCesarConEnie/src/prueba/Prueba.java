package prueba;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//String abecedario= "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		String abecedario= "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyzáéíóú ,.";
		//String abecedario3= "abcdefghijklmnñopqrstuvwxyz";
		System.out.println("Ingrese mensaje a encriptar");
		String mensaje= scan.nextLine();
		System.out.println("Ingrese salto");
		int salto = scan.nextInt();
		String encriptado=encriptaCesar(abecedario,mensaje,salto);
		desencriptaCesar(encriptado,abecedario,salto);

	}

	
	public static String encriptaCesar(String abecedario,String mensaje,int salto) {
		String encriptado="";
		int i,auxMensaje;
		for(i=0;i<mensaje.length();i++) {
			char letra = caracterTilde(mensaje.charAt(i));
			boolean caracterEspecial = verificaNoLetra(mensaje.charAt(i));
			if (caracterEspecial) 
				encriptado += letra;
			else {
				auxMensaje=buscaPos(letra,abecedario);
				letra=getLetraEncriptada(auxMensaje,salto,abecedario);
				encriptado+=letra;
			
			}
	
		}
		System.out.println("Encriptado Cesar con ñ:   " + encriptado);
		//System.out.println("Mensaje:               "+mensaje);
		return encriptado;
	}
	
	public static void desencriptaCesar (String encriptado,String abecedario,int salto) {
		String desencriptado="";
		int i,auxEncriptado;
		for(i=0;i<encriptado.length();i++) {
			char letra = caracterTilde(encriptado.charAt(i));
			boolean caracterEspecial = verificaNoLetra(encriptado.charAt(i));
			if (caracterEspecial) 
				desencriptado += letra;
			else {
				auxEncriptado=buscaPos(letra,abecedario);
				letra=getLetraDesencriptada(auxEncriptado,salto,abecedario);
				desencriptado+=letra;
			
			}
	
		}
		System.out.println("Desencriptado Cesar con ñ:" + desencriptado);

	}
	
	public static char getLetraDesencriptada(int auxEncriptado,int salto,String abecedario) {
		int auxLetra=auxEncriptado-salto;
		if(auxLetra<abecedario.length() && auxLetra>=0) // nunca va a ser mas grande
			return abecedario.charAt(auxLetra);
		else {
			auxLetra+=abecedario.length();
			return abecedario.charAt(auxLetra);
		}
	}
	
	public static char getLetraEncriptada(int auxMensaje,int salto,String abecedario) {
		int auxLetra=auxMensaje+salto;
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


	public static char caracterTilde(char letra) {
		switch (letra) {
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
		case 59: {
			rta = true;
			break;
		}
		default:
			rta = false;
		}
		return rta;
	}
}
