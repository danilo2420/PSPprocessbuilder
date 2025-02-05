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
        if (comandos == null) {
            System.out.println("\u001B[31m" + "⚠️ No hay comandos disponibles. Modifica el archivo de comandos." + "\u001B[0m");
            return;
        }

        // Parte 2: En base a la lista creamos el menú de usuario
        menu();

        // Parte 3: Parte de lanzar el proceso
    }

    private static void menu() {
        while (true) {
            dibujarMenu();

            int input = pedirOpcion();
            if (input == 0) break;
            if (input == -1) {
                añadirComando();
                continue;
            }

            lanzarComando(input - 1);
        }
        System.out.println("\u001B[33m" + "👋 Saliendo del programa... ¡Hasta pronto!" + "\u001B[0m");
    }

    private static void dibujarMenu() {
        int anchoMenu = 40; // Ajuste final del ancho

        System.out.println("\n\u001B[36m╔" + "═".repeat(anchoMenu - 2) + "╗");
        System.out.printf("║ %-"+(anchoMenu-4)+"s ║\n", "🚀 MENÚ PRINCIPAL");
        System.out.println("╠" + "═".repeat(anchoMenu - 2) + "╣\u001B[0m");

        for (int i = 0; i < comandos.size(); i++) {
            System.out.printf("\u001B[32m║ %2d - %-"+(anchoMenu-9)+"s ║\u001B[0m\n", i + 1, comandos.get(i));
        }

        System.out.println("\u001B[36m╠" + "═".repeat(anchoMenu - 2) + "╣");
        System.out.printf("\u001B[31m║ %2d - ❌ %-"+(anchoMenu-12)+"s ║\u001B[0m\n", 0, "Salir");
        System.out.printf("\u001B[35m║ %2d - ➕ %-"+(anchoMenu-12)+"s ║\u001B[0m\n", -1, "Añadir comando");
        System.out.println("\u001B[36m╚" + "═".repeat(anchoMenu - 2) + "╝\u001B[0m");
    }

    private static void lanzarComando(int input) {
        // 🔄 Recargar la lista desde el archivo antes de ejecutar un comando
        comandos = LeerArchivo.getLista();

        // Validar que la opción ingresada es correcta después de recargar la lista
        if (input < 0 || input >= comandos.size()) {
            System.out.println("\u001B[31m❌ Error: Comando inválido.\u001B[0m");
            return;
        }

        String comando = comandos.get(input);
        System.out.println("\u001B[34m🔹 Ejecutando comando: " + comando + "\u001B[0m");

        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            pb.start();
        } catch (IOException e) {
            System.out.println("\u001B[31m❌ Error al ejecutar el comando.\u001B[0m");
        }
    }

    private static void añadirComando() {
        System.out.print("\u001B[35m📝 Introduzca el comando a añadir: \u001B[0m");
        Scanner scanner = new Scanner(System.in);
        String comandoNuevo = scanner.nextLine();

        comandos.add(comandoNuevo);
        LeerArchivo.actualizarFichero(comandos);

        // 🔄 Recargar la lista después de actualizar el archivo
        comandos = LeerArchivo.getLista();

        System.out.println("\u001B[32m✅ Comando añadido correctamente.\u001B[0m");
    }

    private static int pedirOpcion() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        while (true) {
            System.out.print("\u001B[33m➡️ Introduzca una opción: \u001B[0m");
            String entrada = scanner.nextLine().trim(); // Leer entrada y eliminar espacios en blanco

            // Verificar si la entrada es un número válido
            if (!entrada.matches("-?\\d+")) { // Si no es un número, mostrar mensaje de error
                System.out.println("\u001B[31m⚠️ Error: La opción debe ser un número entero.\u001B[0m");
                continue; // Volver a pedir la opción
            }

            // Convertir a número entero
            opcion = Integer.parseInt(entrada);

            // Verificar si está dentro del rango permitido
            if (opcion >= -1 && opcion <= comandos.size()) {
                return opcion;
            }

            System.out.println("\u001B[31m❌ Opción no válida. Inténtelo de nuevo.\u001B[0m");
        }
    }
}