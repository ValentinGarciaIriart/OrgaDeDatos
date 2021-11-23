package prueba;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		System.out.println("Ingrese mensaje a encriptar");
		String mensaje = scan.nextLine();
		String encriptado="";
		char[] aux = new char[5];
		int j=0;
		for (int i=0; i< mensaje.length();i++) {
			if(j<5) {
				aux[j]=mensaje.charAt(i);
				j++;
			}
			else {
				encriptado+=aux[2];
				encriptado+=aux[1];
				encriptado+=aux[4];
				encriptado+=aux[0];
				encriptado+=aux[3];
				aux[0]=aux[1]=aux[2]=aux[3]=aux[4]=' ';
				aux[0]=mensaje.charAt(i);
				j=1;
			}
		}
		System.out.println("Encriptado por transposicion:"+encriptado);

	}

}
