package prueba;

import java.util.Scanner;

public class Prueba {

	//ESTO ES PARA ABECEDARIO COMPLETO CON Ñ
	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		System.out.println("Ingrese mensaje a encriptar");
		String mensaje = scan.nextLine();
		System.out.println("Ingrese clave");
		String clave = scan.nextLine();
		System.out.println("Ingrese desplazamiento: i:izquierda | d:derecha");
		char desplazamiento = scan.nextLine().charAt(0);
		System.out.println("Ingrese salto");
		int salto= scan.nextInt();
		
		
		//String abecedario="abcdefghijklmnñopqrstuvwyxzABCDEFGHIJKLMNÑOPQRSTUVWXYZ";  //PARA CUANDO TENGA EL ABECEDARIO COMPLETO
		String abecedario="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; //ABECEDARIO MAYUSCULAS
		
		
		
		System.out.println(abecedario);
		String abecedarioCesar = generaAbecedarioCesar(clave,salto,desplazamiento,abecedario);
		String encriptado="";
		System.out.println(abecedarioCesar);
		for(int i=0;i<mensaje.length();i++) {
			char letra = caracterConTilde(mensaje.charAt(i));
			boolean caracterEspecial = verificaNoLetra(mensaje.charAt(i));
			if(caracterEspecial) {
				encriptado+=letra;
			}
			else {
					int j=0;
					while(letra!=abecedario.charAt(j))
						j++;
					encriptado+=abecedarioCesar.charAt(j);
			}
		}
		System.out.println("Encriptado con Cesar Modificado:"+encriptado);
		desencriptado(encriptado,abecedario,abecedarioCesar);

	}
	
	public static void desencriptado (String encriptado,String abecedario,String abecedarioCesar) {
		String desencriptado="";
		for(int i=0;i<encriptado.length();i++) {
			char letra = caracterConTilde(encriptado.charAt(i));
			boolean caracterEspecial = verificaNoLetra(encriptado.charAt(i));
			if(caracterEspecial) {
				desencriptado+=letra;
			}
			else {
				int j=0;
				while(letra!=abecedarioCesar.charAt(j))
					j++;
				desencriptado+=abecedario.charAt(j);
			}
		}
		System.out.println("Desencriptado con Cesar Modificado:"+desencriptado);
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
	
	public static boolean buscaLetraClave(String clave, char letra) {
		int i=0;
		while(i<clave.length() && clave.charAt(i)!=letra) {
			i++;
		}
		if(i<clave.length())
			return true;
		else 
			return false;
		
		
	}
	public static String generaAbecedarioCesar(String clave,int salto,char desplazamiento,String abecedario) {
		String abecedarioAux=abecedario;
		String aux="";
		boolean bool,estaClave;
		int i;
		String rta="";
		if(String.valueOf(desplazamiento).equalsIgnoreCase("d")) {
			aux+=clave;
			aux+=abecedarioAux;
			abecedarioAux="";
			for(i=salto;i>0;i--) {
				estaClave=buscaLetraClave(clave,abecedario.charAt(abecedario.length()-i));
				if(estaClave == false)
				abecedarioAux+=abecedario.charAt(abecedario.length()-i);
			}
			abecedarioAux+=aux;
			for(i=0;i<abecedarioAux.length();i++) {
				if(noEsta(abecedarioAux.charAt(i),rta)==true)
					rta+=abecedarioAux.charAt(i);	
			}
		}
		else {
			for(i=salto;i<abecedario.length();i++) {
				aux+=abecedario.charAt(i);
			}
			for(i=clave.length()-1;i>=0;i--) {
				
				aux+=clave.charAt(i);
			}
			for(i=0;i<salto;i++) {
				aux+=abecedario.charAt(i);	
			}
			for(i=0;i<aux.length();i++) {
				if(noEsta(aux.charAt(i),rta)==true)
					rta+=aux.charAt(i);	
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
}
