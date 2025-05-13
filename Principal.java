package Practica;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


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


        scanner.close();
    }
}

