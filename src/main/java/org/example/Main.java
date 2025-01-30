package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static ArrayList<String> comandos;

    public static void main(String[] args) {
        // Parte 1: Aquí tenemos que leer del archivo y generar la lista
        comandos = LeerArchivo.getLista();
        // lista puede ser nula

        // Parte 2: En base a la lista, creamos el menú de usuario
        menu();
        System.out.println("Menú:");
        for (int i = 0; i < comandos.size(); i++) {

        }

        // Parte 3: Parte de lanzar el proceso


    }

    private static void menu() {

        while(true){



        }
    }
}