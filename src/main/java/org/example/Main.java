package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ArrayList<String> comandos;

    public static void main(String[] args) {
        // Parte 1: Aquí tenemos que leer del archivo y generar la lista
        comandos = LeerArchivo.getLista();
        if(comandos == null){
            System.out.println("No hay comandos disponibles. Modifica el archivo de comandos");
            return;
        }

        // Parte 2: En base a la lista, creamos el menú de usuario
        menu();

        // Parte 3: Parte de lanzar el proceso
    }

    private static void menu() {
        while(true){
            dibujarMenu();

            int input = pedirOpcion();
            if(input == 0) break;
            if(input == -1){
                añadirComando();
                continue;
            }

            lanzarComando(input - 1);
        }
        System.out.println("Saliendo del programa...");
    }

    private static void dibujarMenu() {
        System.out.println("MENÚ:");
        for (int i = 0; i < comandos.size(); i++)
            System.out.println((i+1) + " - " + comandos.get(i));
        System.out.println("0 - Salir");
        System.out.println("-1 - Añadir comando");
    }

    private static void lanzarComando(int input) {
        String comando = comandos.get(input);
        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void añadirComando() {
        System.out.println("Introduzca el comando a añadir:");
        Scanner scanner = new Scanner(System.in);
        String comandoNuevo = scanner.nextLine();
        comandos.add(comandoNuevo);
        LeerArchivo.actualizarFichero(comandos);
        comandos = LeerArchivo.getLista();
    }

    // Validación
    private static int pedirOpcion() {
        System.out.println("Introduzca una opción:");
        while(true){
            try {
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                if(input >= -1 && input <= comandos.size())
                    return input;
            } catch (Exception e) {}
            System.out.println("Input no válido. Inténtelo de nuevo.");
        }
    }

}