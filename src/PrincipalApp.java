import java.util.Scanner;

public class PrincipalApp {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String repetir = "SI";
		String[][] alumnos;
		int numAlumnos, option;

		System.out.print(
				"----- Bienvenido al programa de examenes de alumnos ------\n¿Cuántos alumnos son?:");
		numAlumnos = sc.nextInt();
		alumnos = new String[numAlumnos][11];

		rellenarAlumnos(alumnos);

		do {
			if (repetir.equalsIgnoreCase("SI")) {
				option = menu();
				switch (option) {
					case 1:
						getAlumnos(alumnos);
						break;
					case 2:
						obtenerNota(alumnos);
						break;
					case 3:
						obtenerMedia(alumnos);
						break;
					case 4:
						obtenerFallos(alumnos);
						break;
					case 5:
						obtenerAciertos(alumnos);
						break;
					case 6:
						notaBaja(alumnos);
						break;
					case 7:
						notaAlta(alumnos);
						break;
					case 8:
						corregirRespuestas(alumnos);
						break;
				}
			} else {
				System.out.println("[ERROR] Opción incorrecta...");
			}
			System.out.print("¿Desea volver a hacer otra consulta? (SI/NO): ");
			repetir = sc.next();
			System.out.println("\n");
		} while ((repetir.equalsIgnoreCase("SI") && !repetir.equalsIgnoreCase("NO")) ||
				!repetir.equalsIgnoreCase("NO"));
	} // fin class

	public static int menu() {
		int option;

		do {
			System.out.println("----- Menú Principal -----");
			System.out.println("1. Obtener todos los alumnos y notas");
			System.out.println("2. Obtener nota de un alumno");
			System.out.println("3. Obtener la nota media de clase");
			System.out.println("4. Obtener la pregunta con más fallos");
			System.out.println("5. Obtener la pregunta con más aciertos");
			System.out.println("6. Obtener el alumno con nota más baja");
			System.out.println("7. Obtener el alumno con nota más alta");
			System.out.println("8. Corregir respuesta de alumno");
			System.out.print("Introduzca el número de la opcion: ");
			option = sc.nextInt();
			System.out.print("\n");
			if (option < 1 || option > 8) {
				System.out.println("[ERROR] Opción incorrecta...");
			}
		} while (option < 1 || option > 8);

		return option;
	}

	public static void getAlumnos(String[][] alumnos) {
		for (int i = 0; i < alumnos.length; i++) {
			System.out.print("[");
			for (int k = 0; k < alumnos[0].length; k++) {
				System.out.print(alumnos[i][k]);
				if (!(k == (alumnos[0].length - 1))) {
					System.out.print(" - ");
				}
			}
			System.out.print("]\n");
		}
	}

	public static void rellenarAlumnos(String[][] alumnos) {
		String alumno, valor;
		for (int i = 0; i < alumnos.length; i++) {
			System.out.print("Introduzca el nombre del " + (i + 1) + "º alumno:");
			alumno = sc.next();
			alumnos[i][0] = alumno;
			for (int k = 1; k < alumnos[0].length; k++) {
				valor = String.valueOf((int) (0 + Math.random() * 2));
				alumnos[i][k] = valor;
			}
		}
		System.out.println("Alumnos guardados correctamente...");
		System.out.println("Valores generados correctamente...");
	}

	public static void obtenerNota(String alumnos[][]) {
		int nota = 0, option;

		System.out.println("--------------------");
		for (int i = 0; i < alumnos.length; i++) {
			System.out.println((i + 1) + ". " + alumnos[i][0]);
		}
		System.out.println("--------------------\n");

		do {
			System.out.print("Selecciona un alumno para saber su nota: ");
			option = sc.nextInt();
			if (option < 1 || option > alumnos.length) {
				System.out.println("[ERROR] Alumno no encontrado...");
			}
		} while (option < 1 || option > alumnos.length);
		option--;
		for (int j = 1; j < alumnos[0].length; j++) {
			nota = nota + Integer.parseInt(alumnos[option][j]);
		}
		System.out.println("Nota de " + alumnos[option][0] + ": " + nota);
	}

	public static void obtenerMedia(String alumnos[][]) {
		int totalNotas = 0;
		for (int i = 0; i < alumnos.length; i++) {
			for (int j = 1; j < alumnos[0].length; j++) {
				totalNotas = totalNotas + Integer.parseInt(alumnos[i][j]);
			}
		}
		System.out.println("Nota MEDIA de alumnos: " + totalNotas / alumnos.length);
	}
	
  public static void obtenerFallos(String alumnos[][]) {
    int cont0 = 0, aux = 0;
    int fallos [] = new int [10];
    for (int i = 1; i < alumnos[0].length; i++) {
      for (int j = 0; j < alumnos.length; j++) {
        if (alumnos[j][i].equals("0")) {
          cont0++;
        }
      }
      fallos[i-1] = cont0;
      if (cont0 > aux) {
          aux = cont0;
        }
      cont0 = 0;
    }
    System.out.println("El/Los Ejercicio(s) con más FALLOS es/son: " );
    for(int i=0;i<fallos.length;i++) {
    	if(fallos[i]==aux) {
    	System.out.println(" • Ejercicio " + (i+1));
    	}
    }
    System.out.print("-- Con " + aux + " ❌FALLOS❌  \n"); 
  }

  public static void obtenerAciertos(String alumnos[][]) {
    int cont1 = 0, aux = 0;
    int [] aciertos = new int [10];
    for (int i = 1; i < alumnos[0].length; i++) {
      for (int j = 0; j < alumnos.length; j++) {
        if (alumnos[j][i].equals("1")) {
          cont1++;
        }
      }
      aciertos[i-1] = cont1;
      if (cont1 > aux) {
          aux = cont1;
        }
      cont1 = 0;
    }
    
    System.out.println("El/Los Ejercicio(s) con más ACIERTOS es/son: " );
    for(int i=0;i<aciertos.length;i++) {
    	if(aciertos[i]==aux) {
    	System.out.println(" • Ejercicio " + (i+1));
    	}
    }
    System.out.print("--- Con " + aux + " ✔️ACIERTOS✔️ \n");
  } 

	public static void notaBaja(String alumnos[][]) {
		int fila = 0, acumulador = 0, aux = 90;
		for (int i = 0; i < alumnos.length; i++) {
			for (int j = 1; j < alumnos[i].length; j++) {
				acumulador = acumulador + Integer.parseInt(alumnos[i][j]);
			}
			if (acumulador < aux) {
				aux = acumulador;
				fila = i;
			}
			acumulador = 0;
		}
		System.out.println(
				"El alumno con la nota más BAJA, un " + aux + ", es " + alumnos[fila][0]);
	}

	public static void notaAlta(String alumnos[][]) {
		int fila = 0, acumulador = 0, aux = 0;
		for (int i = 0; i < alumnos.length; i++) {
			for (int j = 1; j < alumnos[i].length; j++) {
				acumulador = acumulador + Integer.parseInt(alumnos[i][j]);
			}
			if (acumulador > aux) {
				aux = acumulador;
				fila = i;
			}
			acumulador = 0;
		}
		System.out.println(
				"El alumno con la nota más ALTA, un " + aux + ", es " + alumnos[fila][0]);
	}

	public static void corregirRespuestas(String alumnos[][]) {
		int option;

		System.out.println("--------------------");
		for (int i = 0; i < alumnos.length; i++) {
			System.out.println((i + 1) + ". " + alumnos[i][0]);
		}
		System.out.println("--------------------\n");

		do {
			System.out.print("Selecciona un alumno para saber su nota: ");
			option = sc.nextInt();
			if (option < 1 || option > alumnos.length) {
				System.out.println("[ERROR] Alumno no encontrado...");
			}
		} while (option < 1 || option > alumnos.length);
		option--;

		for (int j = 1; j < alumnos[0].length; j++) {
			do {

				if (!(Integer.parseInt(alumnos[option][j]) == 1) && !(Integer.parseInt(alumnos[option][j]) == 0)) {
					System.out.println("[Error] El valor debe ser comprendido entre 0 y 1");
				}

				System.out.print(
						"Introduce el nuevo valor de la " + j + "º pregunta: ");
				alumnos[option][j] = sc.next();
			} while (!(Integer.parseInt(alumnos[option][j]) == 1) && !(Integer.parseInt(alumnos[option][j]) == 0));
		}

	}
} // fin main
