package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class LeerArchivo {

    private static String path = "C:\\a\\comandos.txt";

    public static ArrayList<String> getLista(){
        File file = new File(path);
        try {
            String contenido = Files.readString(Paths.get(path));
            String arr[] = contenido.split(";");
            ArrayList<String> resultado = new ArrayList<>();
            for (int i = 0; i < arr.length; i++)
                resultado.add(arr[i]);
            return resultado;
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            //throw new RuntimeException(e);
        }
        return null;
    }
}
