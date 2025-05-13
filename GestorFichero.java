package Practica;

import java.io.*;

public class GestorFichero {


    public static void escribirFrasesEnFichero(String nombreFichero, String[] frases) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero))) {
            for (String frase : frases) {
                bw.write(frase);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }


    public static void leerFicheroYContarPalabras(String nombreFichero) {
        int contadorPalabras = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                String[] palabras = linea.trim().split("\\s+");
                contadorPalabras += palabras.length;
            }
            System.out.println("Total de palabras: " + contadorPalabras);
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}


