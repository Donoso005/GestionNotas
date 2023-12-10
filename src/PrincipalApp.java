import java.util.Scanner;

public class PrincipalApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String repetir = "SI";
        String[][] alumnos;
        int numAlumnos, option;
        boolean ActividadesCheck = false;

        System.out.print(
                "----- Bienvenido al programa de examenes de alumnos ------\n¿Cuántos alumnos son?:");
        numAlumnos = sc.nextInt();
        alumnos = new String[numAlumnos][11];

        rellenarAlumnos(alumnos);
        
        // Do-While Repeat consult
        do {
            if (repetir.equalsIgnoreCase("SI")) {
                option = menu();
                //Switch Menu
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
                    case 9:
                        eliminarAlumno(alumnos);
                        break;
                    case 10:
                        crearAlumno(alumnos);
                        break;
                    case 11:
                    	ActividadesCheck = comprobarActividades(alumnos, ActividadesCheck);
                    case 12:
                        aprobadosSuspensos(alumnos);
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
        // ERRROR Out of Parameters
        do {
        	//Print Menu
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Obtener todos los alumnos y notas");
            System.out.println("2. Obtener nota de un alumno");
            System.out.println("3. Obtener la nota media de clase");
            System.out.println("4. Obtener la pregunta con más fallos");
            System.out.println("5. Obtener la pregunta con más aciertos");
            System.out.println("6. Obtener el alumno con nota más baja");
            System.out.println("7. Obtener el alumno con nota más alta");
            System.out.println("8. Corregir respuesta de alumno");
            System.out.println("9. Eliminar Alumno");
            System.out.println("10. Crear Alumno");
            System.out.println("11. Comprobar y agregar nota extra por actividades");
            System.out.println("12. Mostrar alumnos aprobados y suspensos");
            System.out.print("Introduzca el número de la opcion: ");
            option = sc.nextInt();
            System.out.print("\n");
            if (option < 1 || option > 12) {
                System.out.println("[ERROR] Opción incorrecta...");
            }
        } while (option < 1 || option > 12);

        return option;
    }
 
    //Imprime todos los alumnos con sus respuestas
    public static void getAlumnos(String[][] alumnos) {
    	// Loop travel Table
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

    //Rellena de forma manual la matriz alumnos según la cantidad de alumnos introducida anteriormente
    public static void rellenarAlumnos(String[][] alumnos) {
        String alumno, valor;
        // POST Alumnos in table
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

    //Verifica al alumno por numero, acumula sus respuestas y muestra la nota
    public static void obtenerNota(String alumnos[][]) {
        int nota = 0, option;

        // GET Alumnos Available
        System.out.println("--------------------");
        for (int i = 0; i < alumnos.length; i++) {
            System.out.println((i + 1) + ". " + alumnos[i][0]);
        }
        System.out.println("--------------------\n");
        
        // Select Alumno
        do {

            System.out.print("Selecciona un alumno para saber su nota: ");
            option = sc.nextInt();
            if (option < 1 || option > alumnos.length) {
                System.out.println("[ERROR] Alumno no encontrado...");
            }
        } while (option < 1 || option > alumnos.length);
        option--;
        
        // Get Mark total
        for (int j = 1; j < alumnos[0].length; j++) {
            nota = nota + Integer.parseInt(alumnos[option][j]);
        }
        System.out.println("Nota de " + alumnos[option][0] + ": " + nota);
    }

    //Recorre todos los alumnos, suma todas las respuestas e imprime la media
    public static void obtenerMedia(String alumnos[][]) {
        int totalNotas = 0;
        // GET Marks and Make Average
        for (int i = 0; i < alumnos.length; i++) {
            for (int j = 1; j < alumnos[0].length; j++) {
                totalNotas = totalNotas + Integer.parseInt(alumnos[i][j]);
            }
        }
        System.out.println("Nota MEDIA de alumnos: " + totalNotas / alumnos.length);
    }

    //Recorre todas las respuestas y las suma al contador si es un 0
    public static void obtenerFallos(String alumnos[][]) {
        int cont0 = 0, aux = 0;
        int fallos[] = new int[10];
        for (int i = 1; i < alumnos[0].length; i++) {
            for (int j = 0; j < alumnos.length; j++) {
                if (alumnos[j][i].equals("0")) {
                    cont0++;
                }
            }
            fallos[i - 1] = cont0;
            if (cont0 > aux) {
                aux = cont0;
            }
            cont0 = 0;
        }
        System.out.println("El/Los Ejercicio(s) con más FALLOS es/son: ");
        for (int i = 0; i < fallos.length; i++) {
            if (fallos[i] == aux) {
                System.out.println(" • Ejercicio " + (i + 1));
            }
        }
        System.out.print("-- Con " + aux + " ❌FALLOS❌  \n");
    }

    //Recorre todas las respuestas y las suma al contador si es un 1
    public static void obtenerAciertos(String alumnos[][]) {
        int cont1 = 0, aux = 0;
        int[] aciertos = new int[10];
        for (int i = 1; i < alumnos[0].length; i++) {
            for (int j = 0; j < alumnos.length; j++) {
                if (alumnos[j][i].equals("1")) {
                    cont1++;
                }
            }
            aciertos[i - 1] = cont1;
            if (cont1 > aux) {
                aux = cont1;
            }
            cont1 = 0;
        }

        System.out.println("El/Los Ejercicio(s) con más ACIERTOS es/son: ");
        for (int i = 0; i < aciertos.length; i++) {
            if (aciertos[i] == aux) {
                System.out.println(" • Ejercicio " + (i + 1));
            }
        }
        System.out.print("--- Con " + aux + " ✔️ACIERTOS✔️ \n");
    }

    //Calcula la nota de un alumno y si es menor que el anterior la guarda
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

    //Calcula la nota de un alumno y si es mayor que el anterior la guarda
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

    //Verifica alumnos por su numero y permite modificar sus respuestas
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

    //Elimina el nombre y las respuestas de un alumno
    public static void eliminarAlumno(String alumnos[][]) {
        int option;

        System.out.println("--------------------");
        for (int i = 0; i < alumnos.length; i++) {
            System.out.println((i + 1) + ". " + alumnos[i][0]);
        }
        System.out.println("--------------------\n");

        do {
            System.out.print("Selecciona un alumno para ELIMINARLO: ");
            option = sc.nextInt();
            if (option < 1 || option > alumnos.length) {
                System.out.println("[ERROR] Alumno no encontrado...");
            }
        } while (option < 1 || option > alumnos.length);
        option--;
        for (int i = 0; i < alumnos[0].length; i++) {
            alumnos[option][i] = "";
        }
        System.out.println("El alumno " + alumnos[option][0] + " ha sido ELIMINADO exitosamente");
    }

    //Permite crear un nuevo alumno si hay una fila de matriz disponible
    public static void crearAlumno(String[][] alumnos) {
        boolean disponible = false;
        int fila = 0;

        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i][0].equalsIgnoreCase("")) {
                disponible = true;
                fila = i;
            }
        }

        if (disponible) {
            System.out.print("Introduzca el nombre del alumno: ");
            alumnos[fila][0] = sc.next();

            for (int j = 1; j < alumnos[0].length; j++) {
                do {

                    System.out.print(
                            "Introduce el nuevo valor de la " + j + "º pregunta: ");
                    alumnos[fila][j] = sc.next();

                    if (!(Integer.parseInt(alumnos[fila][j]) == 1) && !(Integer.parseInt(alumnos[fila][j]) == 0)) {
                        System.out.println("[Error] El valor debe ser comprendido entre 0 y 1");
                    }
                } while (!(Integer.parseInt(alumnos[fila][j]) == 1) && !(Integer.parseInt(alumnos[fila][j]) == 0));
            }
            System.out.println("[EXITO] Se ha creado el alumno " + alumnos[fila][0] + " correctamente.");
        } else {
            System.out.println("[Error] Todos los campos están llenos, no se puede crear ningun alumno más");
        }
    }

    //Crea el vector actividades de manera aleatoria. Pregunta si aplicar las actividades y suma o resta a la primera respuesta posible.
    public static boolean comprobarActividades(String[][] alumnos, boolean actividadesCheck) {
	
        boolean[] actividades = new boolean[alumnos.length];
        String aplicarNota;
        int auxVar = 1;
        boolean tieneCero = false;
        
        if(actividadesCheck) {
        	System.out.println("[ERROR] Ya ha aplicado las penalizaciónes/bonificaciones anteriormente");
        	return true;
        }
        
        // Generate Actividades
        for (int i = 0; i < actividades.length; i++) {
            actividades[i] = ((int) (0 + Math.random() * 2) == 1);
        }
        // Check Actividades
        System.out.println("Estos son los alumnos que han hecho las actividades: ");
        for (int i = 0; i < alumnos.length; i++) {
            System.out.println((i + 1) + ". " + alumnos[i][0] + ": " + actividades[i]);
        }
        
        // Aplly the Punishment and Bonification
        do {
            System.out.println("Actividades hechas = +1 punto");
            System.out.println("Actividades no hechas = -1 punto");
            System.out.print("¿Desea aplicar la penalización/bonificacion a la nota de cada alumno? (SI/NO): ");
            aplicarNota = sc.next();
            if (aplicarNota.equalsIgnoreCase("SI")) {
                for (int i = 0; i < alumnos.length; i++) {
                	// IF do Actividades
                    if (actividades[i]) {
                        do {
                        	// Check every column if 0
                            if (Integer.parseInt(alumnos[i][auxVar]) == 0) {
                                tieneCero = true;
                                // Replace 0 to 1
                                alumnos[i][auxVar] = String.valueOf(1);
                            }
                            auxVar++;
                        } while (!tieneCero && auxVar < alumnos[0].length);
                    // ELSE
                    } else {
                        do {
                        	// Check every column if 0
                            if (Integer.parseInt(alumnos[i][auxVar]) == 1) {
                                tieneCero = true;
                                // Replace 1 to 0
                                alumnos[i][auxVar] = String.valueOf(0);
                            }
                            auxVar++;
                        } while (!tieneCero && auxVar < alumnos[0].length);
                    }
                    // Reset Aux Var
                    auxVar = 1;
                    tieneCero = false;
                }
                System.out.println(
                        "[EXITO] Se han aplicado las penalizaciones y bonificaciones de las actividades correctamente.");
                actividadesCheck = true;
            }
            if ((!aplicarNota.equalsIgnoreCase("SI") && !aplicarNota.equalsIgnoreCase("NO"))) {
                System.out.println("[ERROR] Opción incorrecta...");
            }
            System.out.println("\n");
        } while ((!aplicarNota.equalsIgnoreCase("SI") && !aplicarNota.equalsIgnoreCase("NO")));
        
        return actividadesCheck;
    }

    //Calcula y guarda los aprobados y suspensos en su respectivo vector y despues imprime ambos vectores
    public static void aprobadosSuspensos(String alumnos[][]) {
        int cont = 0;
        String[] aprobados, suspensos;
        aprobados = new String[alumnos.length];
        suspensos = new String[alumnos.length];

        for (int i = 0; i < alumnos.length; i++) {
            for (int j = 1; j < alumnos[0].length; j++) {
                cont = cont + Integer.parseInt(alumnos[i][j]);
            }
            if (cont > 4) {
                aprobados[i] = alumnos[i][0];
            } else {
                suspensos[i] = alumnos[i][0];
            }
            cont = 0;
        }
        System.out.println("Alumnos APROBADOS: ");
        for (int i = 0; i < aprobados.length; i++) {
            if (aprobados[i] != null) {
                System.out.println("• " + aprobados[i]);
            }
        }
        System.out.println("Alumnos SUSPENSOS: ");
        for (int i = 0; i < suspensos.length; i++) {
            if (suspensos[i] != null) {
                System.out.println("• " + suspensos[i]);
            }
        }
    }
} // fin main
