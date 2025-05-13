package Practica;
import java.io.*;

public class GestorBinario {

    public static void guardarListin(String nombreFichero, Listin[] lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero))) {
            oos.writeObject(lista);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar en binario: " + e.getMessage());
        }
    }

    public static void leerListin(String nombreFichero) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero))) {
            Listin[] lista = (Listin[]) ois.readObject();
            for (Listin l : lista) {
                System.out.println(l);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer fichero binario: " + e.getMessage());
        }
    }
}
