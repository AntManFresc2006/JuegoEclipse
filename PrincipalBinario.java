package Practica;

import java.util.Scanner;

public class PrincipalBinario {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elige una opción:");
        System.out.println("1. Fichero de texto");
        System.out.println("2. Fichero binario");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        if (opcion == 1) {
            // Ejercicio 1
            String[] frases = {
                "El sol brilla intensamente hoy.",
                "Me gusta aprender cosas nuevas cada día.",
                "La programación es una habilidad muy útil.",
                "A veces, los errores enseñan más que los aciertos.",
                "Leer libros expande la mente."
            };

            System.out.print("Introduce el nombre del fichero (sin espacios, sin tildes y con .txt): ");
            String nombreFichero = scanner.nextLine();

            GestorFichero.escribirFrasesEnFichero(nombreFichero, frases);
            GestorFichero.leerFicheroYContarPalabras(nombreFichero);

        } else if (opcion == 2) {
            // Ejercicio 2
            Listin[] lista = new Listin[2];

            for (int i = 0; i < lista.length; i++) {
                System.out.println("Introduce los datos del contacto " + (i + 1));
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Dirección: ");
                String direccion = scanner.nextLine();
                System.out.print("Teléfono: ");
                String telefono = scanner.nextLine();

                lista[i] = new Listin(nombre, direccion, telefono);
            }

            System.out.print("Introduce el nombre del fichero binario (.bin): ");
            String nombreFicheroBin = scanner.nextLine();

            GestorBinario.guardarListin(nombreFicheroBin, lista);
            System.out.println("Leyendo los datos guardados...");
            GestorBinario.leerListin(nombreFicheroBin);
        }

        scanner.close();
    }
}
