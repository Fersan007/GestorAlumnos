package edu.ad.ficheros.gestoralumnos;

import java.io.*;
import java.util.Scanner;
 
public class GestorAlumnos {
 
    static final String FICHERO = "alumnos.txt";
    static Scanner teclado = new Scanner(System.in);
 
    public static void main(String[] args) throws IOException {
        int opcion;
        do {
            System.out.println("\n1. Alta  2. Mostrar  3. Buscar  4. Contar  0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(teclado.nextLine());
 
            switch (opcion) {
                case 1:
                    alta();
                    break;
                case 2:
                    mostrar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    contar();
                    break;
                default:
                    break;
            }
        } while (opcion != 0);
    }
 
    static void alta() throws IOException {
        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();
        try (FileWriter fw = new FileWriter(FICHERO, true)) {
            fw.write(nombre + "\n");
        }
        System.out.println("Alumno añadido.");
    }
 
    static void mostrar() throws IOException {
        if (!new File(FICHERO).exists()) {
            System.out.println("No hay alumnos.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        }
    }
 
    static void buscar() throws IOException {
        if (!new File(FICHERO).exists()) {
            System.out.println("No hay alumnos.");
            return;
        }
        System.out.print("Buscar: ");
        String texto = teclado.nextLine().toLowerCase();
        BufferedReader br = new BufferedReader(new FileReader(FICHERO));
        String linea;
        boolean encontrado = false;
        while ((linea = br.readLine()) != null) {
            if (linea.toLowerCase().contains(texto)) {
                System.out.println(linea);
                encontrado = true;
            }
        }
        br.close();
        if (!encontrado) System.out.println("No encontrado.");
    }
 
    static void contar() throws IOException {
        int total = 0;
        if (new File(FICHERO).exists()) {
            BufferedReader br = new BufferedReader(new FileReader(FICHERO));
            while (br.readLine() != null) total++;
            br.close();
        }
        System.out.println("Total: " + total);
    }
}
