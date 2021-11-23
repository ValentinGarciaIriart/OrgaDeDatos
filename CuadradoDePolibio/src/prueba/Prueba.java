package prueba;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {
		String cuadradoPolibio[][] = generaMatPolibio();
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
						} else if (String.valueOf(letra).equalsIgnoreCase("j")
								|| String.valueOf(letra).equalsIgnoreCase("i")) {
							encriptado += "24";
							encontrado = true;
						}
						else if((int) letra >= 48 && (int)  letra <= 57) {
							encriptado += letra;
							encontrado= true;
						}
					} else {
						i = 5;
						j = 5;
					}
				}

			}
		}
		System.out.println("Mensaje: " + mensaje);
		System.out.println("Encriptación por Polibio:" + encriptado);
		//desencriptado(encriptado,cuadradoPolibio);

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
				desencriptado+="I"; //arbitrario, hay que ver si queda mejor una i o la j está bien;
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
}
