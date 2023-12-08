import java.util.Scanner;

public class PrincipalApp {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String [][] alumnos;
		int numAlumnos, option;
		
		System.out.print("----- Bienvenido al programa de examenes de alumnos ------\n¿Cuántos alumnos son?:");
		numAlumnos = sc.nextInt();
		alumnos = new String [numAlumnos][11];
		
		rellenarAlumnos(alumnos);
		
		option = menu();
		switch(option) {
			case 1:
				getAlumnos(alumnos);
				break;
		}
		

	}//fin class
	
	public static int menu() {
		int option;
		
		do {
			System.out.println("----- Menú Principal -----");
			System.out.println("1. Obtener todos los alumnos y notas");
			option = sc.nextInt();
			if(option < 1 || option > 1) {
				System.out.println("[ERROR] Opción incorrecta...");
			}
		}while(option < 1 || option > 1);
		
		return option;
	}
	public static void getAlumnos(String [][] alumnos) {
		for(int i = 0; i < alumnos.length; i++) {
			System.out.print("[");
			for(int k = 0; k < alumnos[0].length; k++) {
				System.out.print(alumnos[i][k]);
				if(!(k == (alumnos[0].length - 1))) {
					System.out.print(" - ");
				}
			}
			System.out.print("]\n");
		}
	}
	
	public static void rellenarAlumnos(String [][] alumnos) {
		String alumno, valor;
		for(int i = 0; i < alumnos.length; i++) {
			System.out.print("Introduzca el nombre del " + (i + 1)  + "º alumno:");
			alumno = sc.next();
			alumnos[i][0] = alumno;
			for(int k = 1; k < alumnos[0].length; k++) {
				valor = String.valueOf((int) (0 + Math.random() * 2));
				alumnos[i][k] = valor;
			}
		}
		System.out.println("Alumnos guardados correctamente...");
		System.out.println("Valores generados correctamente...");
	}
	
	public static void obtenerNota (String alumnos [][]) {
		String nombre;
		int nota=0;
		System.out.println("Introduce el nombre del alumno para saber su nota: ");
		nombre = sc.nextLine();
		for (int i=0;i<alumnos.length;i++) {
			if (nombre.equalsIgnoreCase(alumnos[i][0])) {
				for (int j=1;j<alumnos[0].length;j++) {
					nota = nota + Integer.parseInt(alumnos[i][j]);
				}
			}
		}
		System.out.println("Nota de " + nombre + ": " + nota);
	}
	
	
	public static void obtenerMedia (String alumnos[][], int numAlumnos) {
		int totalNotas=0;
		for (int i=0;i<alumnos.length;i++) {
			for (int j=1;j<alumnos[0].length;j++) {
				totalNotas = totalNotas + Integer.parseInt(alumnos[i][j]);
			}
		}
		System.out.println("Nota MEDIA de alumnos: " + totalNotas/numAlumnos);
	}
	
	
	public static void obtenerFallos (String alumnos [][]) {
		int cont0=0, aux=0, col=0;
		for(int i=1;i<alumnos[0].length;i++) {
			if (cont0>aux) {
				aux = cont0;
				col = i;
			}
			for(int j=1;j<alumnos.length;j++) {
				if(alumnos[j][i].equals("0")){
					cont0++;
				}
			}
			
		}
		System.out.println("El Ejercicio con más FALLOS es el " + col);
	}
	
	
	public static void obtenerAciertos (String alumnos[][]) {
		int cont1=0, aux=0, col=0;
		for(int i=1;i<alumnos[0].length;i++) {
			if (cont1>aux) {
				aux = cont1;
				col = i;
			}
			for(int j=1;j<alumnos.length;j++) {
				if(alumnos[j][i].equals("1")){
					cont1++;
				}
			}
			
		}
		System.out.println("El Ejercicio con más FALLOS es el " + col);
	}
	
}//fin main