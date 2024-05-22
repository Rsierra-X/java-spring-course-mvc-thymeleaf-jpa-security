package org.rsierra.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Utility {

    public static String saveFile(MultipartFile multiPart, String route) {
        // Obtenemos el nombre original del archivo.
        String originalFilename = multiPart.getOriginalFilename();
        assert originalFilename != null;
        originalFilename = originalFilename.replace(" ", "-");
        String finalName = randomAlphaNumeric(8) + originalFilename;
        try {
            // Formamos el nombre del archivo para guardarlo en el disco duro.
            File imageFile = new File(route+ finalName);
            System.out.println("Archivo: " + imageFile.getAbsolutePath());
            //Guardamos fisicamente el archivo en HD.
            multiPart.transferTo(imageFile);
            return finalName;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }

    /**
     * Metodo para generar una cadena aleatoria de longitud N
     * @param count
     * @return
     */
    public static String randomAlphaNumeric(int count) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * characters.length());
            builder.append(characters.charAt(character));
        }
        return builder.toString();
    }
}
