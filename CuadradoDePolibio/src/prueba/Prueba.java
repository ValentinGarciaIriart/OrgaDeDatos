package prueba;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {
		//String cuadradoPolibio[][] = generaMatPolibio();
		String cuadradoPolibio[][] = generaMatPolibioNum();
		
		//muestraMat(cuadradoPolibio);
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese mensaje a encriptar");
		String mensaje = scan.nextLine();
		String encriptado = "";
		boolean encontrado,caracterEspecial;
		for (int m = 0; m < mensaje.length(); m++) {
			int i, j;
			encontrado = false;
			char letra = caracterTilde(mensaje.charAt(m));
			caracterEspecial = verificaNoLetra(mensaje.charAt(m));
			for (i = 0; i < cuadradoPolibio.length; i++) {
				for (j = 0; j < cuadradoPolibio.length; j++) {
					if (encontrado == false) {
						if (String.valueOf(letra).equalsIgnoreCase(cuadradoPolibio[i][j])) {
							int auxI = i + 1;
							int auxJ = j + 1;
							encriptado += auxI;
							encriptado += auxJ;
							encontrado = true;
						} else if (caracterEspecial==true) {
							encriptado += letra;
							encontrado = true;
							
						} /*else if (String.valueOf(letra).equalsIgnoreCase("j")
								|| String.valueOf(letra).equalsIgnoreCase("i")) {
							encriptado += "24";
							encontrado = true;
						}*/ //ESTO ES PARA CUANDO LA I Y LA J ESTAN JUNTAS
						
					} else {
						i = 6;
						j = 6;
					}
				}

			}
		}
		System.out.println("Mensaje: " + mensaje);
		System.out.println("Encriptaci�n por Polibio:" + encriptado);
		//desencriptado(encriptado,cuadradoPolibio);

	}
	
	public static void muestraMat (String [][] cuadradoPolibio) {
		for(int i=0;i<7;i++) {
			for(int j=0;j<5;j++) {
				System.out.print(cuadradoPolibio [i][j]);
			}
			System.out.println();
		}
	}
	public static void desencriptado (String encriptado, String [][]cuadradoPolibio) {
		String desencriptado="";
		int auxLetra1=0,auxLetra2=0;
		boolean caracterEspecial;
		for (int m = 0; m < encriptado.length(); m++) {
			int i, j;
			caracterEspecial = verificaNoLetra(encriptado.charAt(m));
			if(caracterEspecial) 
				desencriptado+=encriptado.charAt(m);	
			else {
			auxLetra1= Integer.parseInt(String.valueOf(encriptado.charAt(m)));
			auxLetra2= Integer.parseInt(String.valueOf(encriptado.charAt(m+1)));
			m++;
			auxLetra1--;auxLetra2--;
			if(auxLetra1==1 && auxLetra2==3)
				desencriptado+="I"; //arbitrario, hay que ver si queda mejor una i o la j est� bien;
			else
				desencriptado+=cuadradoPolibio[auxLetra1][auxLetra2];
		}
		/*	for (i = 0; i < cuadradoPolibio.length; i++) {
				for (j = 0; j < cuadradoPolibio.length; j++) {
					if (encontrado == false) {
						if (String.valueOf(letra).equalsIgnoreCase(cuadradoPolibio[i][j])) {
							int auxI = i + 1;
							int auxJ = j + 1;
							encriptado += auxI;
							encriptado += auxJ;
							encontrado = true;
						} else if (String.valueOf(letra).equalsIgnoreCase("j")
								|| String.valueOf(letra).equalsIgnoreCase("i")) {
							encriptado += "24";
							encontrado = true;
						}
						else if((int) letra >= 48 && letra <= 57) {
							desencriptado += letra;
							encontrado= true;
						}
					} else {
						i = cuadradoPolibio.length;
						j = cuadradoPolibio.length;
					}
				}

			}*/
		}
		System.out.println("Desencriptado: "+desencriptado);
		System.out.println("NOTA: FIJARSE BIEN LAS IJ QUE PUEDEN ESTAR MAL O BIEN PUESTAS");
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
	public static String[][] generaMatPolibio() {
		String matPolib[][] = new String[5][5];
		int ASCII = 65;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == 1 && j == 3) {
					matPolib[i][j] = "IJ";
					ASCII += 2;
				} else {
					matPolib[i][j] = String.valueOf((char) ASCII);
					ASCII++;
				}
			}
		}
		return matPolib;

	}

	public static String[][] generaMatPolibioNum() {
		String matPolib[][] = new String[6][6];
		int ASCII = 65;
		for (int i = 0; i < matPolib.length; i++) {
			for (int j = 0; j < matPolib.length; j++) {
				if (ASCII == 90) {
					matPolib[i][j] = "Z";
					ASCII = 48;
				} else {
					matPolib[i][j] = String.valueOf((char) ASCII);
					ASCII++;
				}
			}
		}

		return matPolib;

	}
}
