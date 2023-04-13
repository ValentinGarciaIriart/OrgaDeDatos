package prueba;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {

        Scanner scan = new Scanner (System.in);
		System.out.println("Ingrese alfabeto completo ( [A..Z] [a..z] ) o incompleto ([a..z])");
		char alfabeto = scan.nextLine().charAt(0);
		System.out.println("Ingrese desplazamiento a derecha o izquierda");
		char desplazamiento = scan.nextLine().charAt(0);
		System.out.println("Ingrese salto");
		int saltoOrig= scan.nextInt();
		String mensaje = "Nos vemos el lunes 24 a las 18hs en Plaza Mitre";
		String abecedario="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String abecedario2="abcdefghijklmnopqrstuvwxyz";
		String encriptado="";

		if (alfabeto == 'i' || alfabeto =='I') {
			encriptado=encriptacionCesar(mensaje,desplazamiento,saltoOrig,abecedario2);
			//encriptado="ufwf xtgwjananw js jxyj rzsit ijgt zxfw qnszc"; // solo para la consigna esa de NIKON
			desencriptado(encriptado,abecedario2,desplazamiento,saltoOrig);
		}
		else {
			encriptado=encriptacionCesar(mensaje,desplazamiento,saltoOrig,abecedario);
			desencriptado(encriptado,abecedario,desplazamiento,saltoOrig);
		}
		
		


	}

	
	public static void desencriptado(String encriptado,String abecedario,char desplazamiento,int salto) {
		String desencriptado="";
		System.out.println("Para que veas que no mande el mensaje directamente, esto es lo encriptado: " + encriptado);
		int j;
		for(int i=0;i<encriptado.length();i++) {
			char letra=caracterConTilde(encriptado.charAt(i));
			if(verificaNoLetra(letra)) 
				desencriptado+=letra;
			else { 
				j=0;
				while(j<abecedario.length() && letra!=abecedario.charAt(j))
					j++;
				if(desplazamiento == 'i' || desplazamiento =='I')
					letra=buscaLetra(j,salto,abecedario);
				else
					letra=buscaLetra(j,-salto,abecedario);
				desencriptado+=letra;
				
			}
			}
		System.out.println("Desencriptado: "+ desencriptado);
		
	}
	
	public static String encriptacionCesar(String mensaje,char desplazamiento,int salto,String abecedario) {
		String encriptado="";
		int j;
		for(int i=0;i<mensaje.length();i++) {
			char letra=caracterConTilde(mensaje.charAt(i));
			if(verificaNoLetra(letra)) 
				encriptado+=letra;
			else {
				j=0;
				while(j<abecedario.length() && letra!=abecedario.charAt(j))
					j++;
				if(desplazamiento == 'i' || desplazamiento =='I')
					letra=buscaLetra(j,-salto,abecedario);
				else
					letra=buscaLetra(j,salto,abecedario);
				encriptado+=letra;
				
		}
	}
		System.out.println("Encriptado Cesar: "+encriptado);
		return encriptado;
}
	
	public static char buscaLetra(int posLetra,int salto,String abecedario) {
		int auxLetra=posLetra+salto;
		if(auxLetra<0) //si se va para atras por ser desplazamiento izquierda
			auxLetra+=abecedario.length();
		else if (auxLetra>=abecedario.length()) //si se pasa por ser desplazamiento derecha
			auxLetra-=abecedario.length();
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

		default: return letra;
			
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
