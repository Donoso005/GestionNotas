import java.util.Scanner;

public class PrincipalApp {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String repetir = "SI";
		String [][] alumnos;
		int numAlumnos, option;
		
		System.out.print("----- Bienvenido al programa de examenes de alumnos ------\n¿Cuántos alumnos son?:");
		numAlumnos = sc.nextInt();
		alumnos = new String [numAlumnos][11];
		
		rellenarAlumnos(alumnos);
		
		do {
			if(repetir.equalsIgnoreCase("SI")) {
				option = menu();
					switch(option) {
						case 1:
							getAlumnos(alumnos);
							break;
						case 2:
							obtenerNota(alumnos);
							break;
						case 3:
							obtenerFallos(alumnos);
							break;
						case 4:
							obtenerAciertos(alumnos);
							break;
						case 5:
							notaBaja(alumnos);
							break;
						case 6:
							notaAlta(alumnos);
							break;
					}
				} else {
					System.out.println("[ERROR] Opción incorrecta...");
				}
			System.out.print("¿Desea volver a hacer otra consulta? (SI/NO): ");
			repetir = sc.next();
			
		} while((repetir.equalsIgnoreCase("SI") && !repetir.equalsIgnoreCase("NO")) || !repetir.equalsIgnoreCase("NO"));
		

	}//fin class
	
	public static int menu() {
		int option;
		
		do {
			System.out.println("----- Menú Principal -----");
			System.out.println("1. Obtener todos los alumnos y notas");
			System.out.println("2. Obtener la nota media de clase");
			System.out.println("3. Obtener la pregunta con más fallos");
			System.out.println("4. Obtener la pregunta con más aciertos");
			System.out.println("5. Obtener el alumno con nota más baja");
			System.out.println("6. Obtener el alumno con nota más alta");
			option = sc.nextInt();
			if(option < 1 || option > 6) {
				System.out.println("[ERROR] Opción incorrecta...");
			}
		}while(option < 1 || option > 6);
		
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
		nombre = sc.next();
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
	
	public static void modificarAlumno (String alumnos [][]) {
		String  nomAlumno;
		System.out.println("¿Qué alumno quieres modificar?");
		
	}
	
	public static void notaBaja (String alumnos [][]) {
		int fila=0, acumulador = 0, aux = 90;
		for(int i=0;i<alumnos.length;i++) {
			for (int j=1;j<alumnos[i].length;j++) {
				acumulador = acumulador + Integer.parseInt(alumnos[i][j]);
			}
			if (acumulador<aux) {
				aux = acumulador;
				fila = i;
			}
			acumulador = 0;
		}
		System.out.println("El alumno con la nota más BAJA, un " + aux + ", es " + alumnos[fila][0]);
	}
	
	public static void notaAlta (String alumnos [][]) {
		int fila=0, acumulador = 0, aux = 0;
		for(int i=0;i<alumnos.length;i++) {
			for (int j=1;j<alumnos[i].length;j++) {
				acumulador = acumulador + Integer.parseInt(alumnos[i][j]);
			}
			if (acumulador>aux) {
				aux = acumulador;
				fila = i;
			}
			acumulador = 0;
		}
		System.out.println("El alumno con la nota más ALTA, un " + aux + ", es " + alumnos[fila][0]);
	}
	
	public static void corregirRespuestas (String alumnos [][]) {
		String  nomAlumno;
		System.out.println("¿Qué alumno quieres modificar?");
		nomAlumno = sc.next();
		for(int i=0;i<alumnos.length;i++) {
			if (nomAlumno.equalsIgnoreCase(alumnos[i][0])){
				for(int j=1;j<alumnos[i].length;j++) {
					System.out.println("Introduce el nuevo valor de la " + j + "º pregunta");
					alumnos[i][j] = sc.next();
				}
			}
		}
	}
}//fin main