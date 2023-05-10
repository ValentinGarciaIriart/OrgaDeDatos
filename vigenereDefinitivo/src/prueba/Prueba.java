package prueba;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) throws FileNotFoundException {
		char[][] matCompletaSinEnieConNum = generaMatVigenere();
		char[][] matCompletaSinEnie = generaVigenere2(); //[a..z][A..Z] y sin numeros
		char[][] matMinusSinEnie = generaVigenere3(); //[a..z]
		char[][] matMayusSinEnie = generaVigenere4(); //[A..Z]
		char [][] matCompletaConEnie = generaVigenere5();//[a..z][A..Z] con ñ y Ñ sin numeros
		char[][] matMinusConEnie = generaVigenere8(); //[a..z] con ñ
		char [][] matMayusConEnie = generaVigenere6();//[A..Z] con  Ñ
		char[][] matCompletaConEnieConNum = generaMatVigenere7();
		//muestraMat(mat);
		Scanner scan = new Scanner(System.in);
		//System.out.println("Ingrese mensaje a encriptar");
		//String mensaje = scan.nextLine();
		String mensaje= "Nos vemos el lunes 24 a las 18hs en Plaza Mitre";
		System.out.println("Ingrese clave");
		String clave = scan.nextLine();
		encriptarVigenereMayus(clave,mensaje,matCompletaSinEnieConNum);
	
		

	}
	
	public static void muestraMat(char [][] mat) {
		for (int i = 0; i< mat.length;i++) {
			for (int j = 0; j< mat.length;j++) {
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void encriptarVigenereMayus (String clave,String mensaje,char mat[][]) {
		int i,l=0,m=0,cont=0;
		//int helpEnieMayus=41,helpEnieMinus=14;
		String encriptado="";
		String claveMapeada="";
		for(i=0;i<mensaje.length();i++) {
			if(cont==clave.length())
				cont=0;
			char letra = caracterTilde(mensaje.charAt(i));
			boolean caracterEspecial = verificaNoLetra(mensaje.charAt(i));
			/*if(caracterEspecial) {
				encriptado+=letra;
				claveMapeada+=letra;
			}*/
			if (!caracterEspecial) {
				l = buscaFila(mat,clave.charAt(cont));
				m = buscaColumna(mat,letra);
				letra = mat[l][m];
				}
			if(cont<clave.length()) {
					if(caracterEspecial)
						claveMapeada+=letra;
					else
						claveMapeada+=clave.charAt(cont);	
					
					cont++;
					encriptado+=letra;
			
			}
		}
		System.out.println("Encriptado Vigenere: "+encriptado);
		System.out.println("Clave Mapeada: "+claveMapeada);
		System.out.println("Mensaje: "+mensaje);
		desencriptarVigenereMayus(encriptado,mat,claveMapeada);
		
	}
	
	public static int buscaFila(char [][]mat, char letra) {
		int i=0;
		while (i<mat.length && mat[i][0]!=letra) {
			i++;
		}
		return i;
	}
	
	public static int buscaColumna(char [][]mat, char letra ) {
		int j=0;
		while (j<mat.length && mat[0][j]!=letra) {
			j++;
		}
		return j;
	}
	
	public static int buscaColumnaDesencriptar(char [][]mat, char letraFila, char letraColumna) {
		int i=0,j=0;
		while (i<mat.length && mat[i][0]!= letraFila) {
			i++;
		}
		int fila = i;
		while (j<mat.length && mat[fila][j]!= letraColumna) {
			j++;
		}
		return j;
	}
	
	public static void desencriptarVigenereMayus(String encriptado,char [][] mat,String clave) {
		int i,j,cont=0;
		int l=0, m=0;
		
		String desencriptado="";
		for(i=0;i<encriptado.length();i++) {
			if(cont==clave.length())
				cont=0;
			char letra = caracterTilde(encriptado.charAt(i));
			boolean caracterEspecial = verificaNoLetra(encriptado.charAt(i));
		
			if (!caracterEspecial) {
				m = buscaColumnaDesencriptar(mat,clave.charAt(cont),letra);
				letra = mat[0][m];
				}
			if(cont<clave.length()) {
					
					cont++;
					desencriptado+=letra;
			
			}
		}
		
		System.out.println("Desencriptado Vigenere: "+desencriptado);
		
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
	
	public static char[][] generaMatVigenere() throws FileNotFoundException{
		char[][] mat= new char [62][62];
		int i=0,j,m=0,n=0;
		String aux;
		Scanner scan = new Scanner(new File("VigenereCompleto09sinEnie.txt"));
		while(scan.hasNextLine()) {
			aux=scan.nextLine();
			for(j=0;j<mat.length;j++) {
				if(m<aux.length()) {
				//mat[i][j]=aux.charAt(m);
				mat[i][j]=aux.charAt(m);
				m+=2;
				}
			}
			i++;
			m=0;
		}
		scan.close();
		
		
		return mat;
		

	}
	public static char[][] generaMatVigenere7() throws FileNotFoundException{
		char[][] mat= new char [64][64];
		int i=0,j,m=0,n=0;
		String aux;
		Scanner scan = new Scanner(new File("VigenereCompleto09conEnie.txt"));
		while(scan.hasNextLine()) {
			aux=scan.nextLine();
			for(j=0;j<mat.length;j++) {
				if(m<aux.length()) {
				//mat[i][j]=aux.charAt(m);
				mat[i][j]=aux.charAt(m);
				m+=2;
				}
			}
			i++;
			m=0;
		}
		scan.close();
		
		
		return mat;
		

	}
	public static char [][] generaVigenere3() throws FileNotFoundException{
		char[][] mat = new char[26][26];
		Scanner scan = new Scanner (new File("vigenereaz.txt"));
		String aux;
		int i=0;
		while(scan.hasNext()) {
			aux=scan.nextLine();
			for(int j=0;j<aux.length();j++) {
				mat[i][j]=aux.charAt(j);
			}
			i++;
			}	
		scan.close();
		return mat;
	}
	public static char [][] generaVigenere8() throws FileNotFoundException{
		char[][] mat = new char[27][27];
		Scanner scan = new Scanner (new File("vigenereazEnie.txt"));
		String aux;
		int i=0;
		while(scan.hasNext()) {
			aux=scan.nextLine();
			for(int j=0;j<aux.length();j++) {
				mat[i][j]=aux.charAt(j);
			}
			i++;
			}	
		scan.close();
		return mat;
	}
	public static char [][] generaVigenere4() throws FileNotFoundException{
		char[][] mat = new char[26][26];
		Scanner scan = new Scanner (new File("vigenereaz.txt"));
		String aux;
		int i=0;
		while(scan.hasNext()) {
			aux=scan.nextLine().toUpperCase();
			for(int j=0;j<aux.length();j++) {
				mat[i][j]=aux.charAt(j);
			}
			i++;
			}	
		scan.close();
		return mat;
	}

	public static char[][] generaVigenere2(){
		char mat[][]=
			{{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'},
				    {'b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a'},
				    {'c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b'},
				    {'d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c'},
				    {'e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d'},
				    {'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e'},
				    {'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f'},
				    {'h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g'},
				    {'i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h'},
				    {'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i'},
				    {'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j'},
				    {'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k'},
				    {'m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l'},
				    {'n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m'},
				    {'o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n'},
				    {'p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o'},
				    {'q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p'},
				    {'r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q'},
				    {'s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r'},
				    {'t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s'},
				    {'u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t'},
				    {'v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u'},
				    {'w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v'},
				    {'x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w'},
				    {'y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x'},
				    {'z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y'},
				    {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'},
				    {'B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A'},
				    {'C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B'},
				    {'D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C'},
				    {'E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D'},
				    {'F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E'},
				    {'G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F'},
				    {'H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G'},
				    {'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H'},
				    {'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I'},
				    {'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J'},
				    {'L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K'},
				    {'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L'},
				    {'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M'},
				    {'O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N'},
				    {'P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'},
				    {'Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P'},
				    {'R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q'},
				    {'S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R'},
				    {'T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S'},
				    {'U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T'},
				    {'V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U'},
				    {'W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V'},
				    {'X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W'},
				    {'Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X',},
				    {'Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y'}};
		return mat;
	}
	public static char[][] generaVigenere5() throws FileNotFoundException{
		char[][] mat= new char [54][54];
		int i=0,j,m=0,n=0;
		String aux;
		Scanner scan = new Scanner(new File("VigenereEnie.txt"));
		while(scan.hasNextLine()) {
			aux=scan.nextLine();
			for(j=0;j<mat.length;j++) {
				if(m<aux.length()) {
				//mat[i][j]=aux.charAt(m);
				mat[i][j]=aux.charAt(m);
				m+=2;
				}
			}
			i++;
			m=0;
		}
		scan.close();
		
		
		return mat;
		

	}
	public static char[][] generaVigenere6() throws FileNotFoundException{
		char[][] mat= new char [27][27];
		int i=0,j,m=0,n=0;
		String aux;
		Scanner scan = new Scanner(new File("VigenereEnie.txt"));
		while(scan.hasNextLine()) {
			aux=scan.nextLine();
			for(j=0;j<mat.length;j++) {
				if(m<aux.length()) {
				//mat[i][j]=aux.charAt(m);
				mat[i][j]=aux.charAt(m);
				m+=2;
				}
			}
			i++;
			m=0;
		}
		scan.close();
		
		
		return mat;
		

	}
}

