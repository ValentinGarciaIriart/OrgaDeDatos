package prueba;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) throws FileNotFoundException {
		char[][] mat = generaMatVigenere();
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese mensaje a encriptar");
		String mensaje = scan.nextLine();
		System.out.println("Ingrese clave");
		String clave = scan.nextLine();
		muestraMatriz(mat);
		String encriptado=encriptarVigenereMayus(clave,mensaje,mat);
		desencriptarVigenereMayus(encriptado,mat,clave);
		

	}
	
	public static void muestraMatriz(char [][]mat) {
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat.length;j++) {
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static String encriptarVigenereMayus (String clave,String mensaje,char mat[][]) {
		int i,l=0,m=0,cont=0;
		int helpEnieMayus=41,helpEnieMinus=14;
		String encriptado="";
		String claveMapeada="";
		for(i=0;i<mensaje.length();i++) {
			if(cont==clave.length())
				cont=0;
			char letra = caracterTilde(mensaje.charAt(i));
			boolean caracterEspecial = verificaNoLetra(mensaje.charAt(i));
			if(caracterEspecial) {
				encriptado+=letra;
				claveMapeada+=letra;
			}
			else {
				if((letra>64 && letra<91) || letra=='�') {
				if(letra=='�') 
					m=helpEnieMayus;
				else if(letra <=78) 
					m=letra-65;
				else if(letra>78)
					m=letra-64;
				if(clave.charAt(cont)=='�')
					l=helpEnieMayus;
				else if (clave.charAt(cont)<=78)
					l=clave.charAt(cont)-65;
				else if (clave.charAt(cont)>78)
					l=clave.charAt(cont)-64;
				letra=mat[l][m];
				}
				else {
					System.out.println("xd");
					if(letra=='�')
						m=helpEnieMinus;
					else if (letra<=110)
						m=letra-97;
					else if (letra>110)
						m=letra-96;
					if(clave.charAt(cont)=='�')
						l=helpEnieMayus;
					else if (clave.charAt(cont)<=110)
						l=clave.charAt(cont)-97;
					else if (clave.charAt(cont)>110)
						l=clave.charAt(cont)-96;
					letra=mat[l][m];
				}
				if(cont<clave.length())
					claveMapeada+=clave.charAt(cont);	
					cont++;
					encriptado+=letra;
			}
		}
		System.out.println("Encriptado Vigenere:"+encriptado);
		System.out.println("Clave Mapeada:      "+claveMapeada);
		//System.out.println("Mensaje:            "+mensaje);
		return encriptado;
		
	}
	
	public static void desencriptarVigenereMayus(String encriptado,char [][] mat,String clave) {
		int i,j,cont=0;
		int helpEnieMinus=14,helpEnieMayus=41;
		String desencriptado="";
		for(i=0;i<encriptado.length();i++) {
			if(cont==clave.length())
				cont=0;
			char letra = caracterTilde(encriptado.charAt(i));
			boolean caracterEspecial = verificaNoLetra(encriptado.charAt(i));
			if(caracterEspecial) {
				desencriptado+=letra;
			}
			else {
				if(clave.charAt(cont)=='�' || (clave.charAt(cont)>65 && clave.charAt(cont)<91)) {	
				if (clave.charAt(cont)=='�') {
						char auxClave = (char)helpEnieMayus;
						int auxLetra= buscaLetra (auxClave,letra,mat);
						letra=mat[0][auxLetra];
						
					}
					else if(clave.charAt(cont)>78){
						char auxClave= (char) (clave.charAt(cont)-37);
						int auxLetra= buscaLetra (auxClave,letra,mat);
						letra=mat[0][auxLetra];
					}
					else {
						char auxClave= (char) (clave.charAt(cont)-38);
						int auxLetra= buscaLetra (auxClave,letra,mat);
						letra=mat[0][auxLetra];
					}
						cont++;
					desencriptado+=letra;
			  }
				else {
					if (clave.charAt(cont)=='�') {
						char auxClave = (char)helpEnieMinus;
						int auxLetra= buscaLetra (auxClave,letra,mat);
						letra=mat[0][auxLetra];
						
					}
					else if(clave.charAt(cont)>110){
						char auxClave= (char) (clave.charAt(cont)-96);
						int auxLetra= buscaLetra (auxClave,letra,mat);
						letra=mat[0][auxLetra];
					}
					else {
						char auxClave= (char) (clave.charAt(cont)-97);
						int auxLetra= buscaLetra (auxClave,letra,mat);
						letra=mat[0][auxLetra];
					}
						cont++;
					desencriptado+=letra;
				}
			}
		}
		System.out.println("Desencriptado Vigenere:"+desencriptado);
		
	}
	public static int buscaLetra(char fila,char columna,char[][] mat) {
		int i=(int)fila;
		int j=0;
		while(j<mat.length && columna!=mat[i][j])
			j++;
		return j;
	}
	
	public static char caracterTilde(char letra) {
		switch (letra) {
		case '�':
			return 'a';
		case '�':
			return 'e';

		case '�':
			return 'i';

		case '�':
			return 'o';

		case '�':
			return 'u';

		case '�':
			return 'A';

		case '�':
			return 'E';

		case '�':
			return 'I';

		case '�':
			return 'O';

		case '�':
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
	
	public static char[][] generaMatVigenere() throws FileNotFoundException{
		char[][] mat= new char [54][54];
		int i=0,j,m=0,n=0;
		String aux,aux2;
		Scanner scan = new Scanner(new File("VigenereEnie.txt"));
		Scanner scan2= new Scanner(new File("VigenereEnie.txt"));
		while(scan.hasNextLine()) {
			aux=scan.nextLine();
			aux2=scan2.nextLine();
			for(j=0;j<mat.length/2;j++) {
				if(m<aux.length()) {
				//mat[i][j]=aux.charAt(m);
				mat[i][j]=Character.toLowerCase(aux.charAt(m));
				m+=2;
				}
			}
			m=0;
			for(j=mat.length/2;j<mat.length;j++) {
				if(n<aux2.length()) {
					//mat[i][j]=aux.charAt(m);
					mat[i][j]=aux2.charAt(n);
					n+=2;
					}
			}
			n=0;
			i++;
		}
		scan.close();
		scan2.close();
		scan = new Scanner(new File("VigenereEnie.txt"));
		scan2= new Scanner(new File("VigenereEnie.txt"));
		while(scan.hasNextLine()) {
			aux=scan.nextLine();
			aux2=scan2.nextLine();
			for(j=0;j<mat.length/2;j++) {
				if(n<aux2.length()) {
					//mat[i][j]=aux.charAt(m);
					mat[i][j]=aux2.charAt(n);
					n+=2;
					}
			}
			n=0;
			for(j=mat.length/2;j<mat.length;j++) {
				if(m<aux.length()) {
				//mat[i][j]=aux.charAt(m);
				mat[i][j]=Character.toLowerCase(aux.charAt(m));
				m+=2;
				}
			}
			m=0;
			i++;
		
		}
		
		return mat;
		

	}

}
