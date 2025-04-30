package model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Funciones {
    
    // Método auxiliar para manejar rutas
    private static Path getFullPath(String basePath, String fileName) {
        Path path = Paths.get(basePath);
        if (!path.isAbsolute()) {
            path = Paths.get(System.getProperty("user.dir"), basePath);
        }
        return fileName != null ? path.resolve(fileName) : path;
    }

    // 1. Crear carpeta
    public static void createFolder(String folderName) throws IOException {
        Path path = getFullPath(folderName, null);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
    
    // 2. Crear/actualizar archivo
    public static void createFile(String path, String fileName, String content) throws IOException {
        Path filePath = getFullPath(path, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, content.getBytes(), 
            StandardOpenOption.CREATE, 
            StandardOpenOption.APPEND);
    }
    
    // 3. Listar archivos
    public static String[] showListFiles(String path) {
        Path dirPath = getFullPath(path, null);
        File dir = dirPath.toFile();
        return dir.exists() ? dir.list() : new String[0];
    }
    
    // 4. Mostrar contenido
    public static String showFile(String path, String fileName) throws IOException {
        Path filePath = getFullPath(path, fileName);
        return new String(Files.readAllBytes(filePath));
    }
    
    // 5. Sobrescribir archivo
    public static boolean overWriteFile(String path, String fileName, String newContent) throws IOException {
        Path filePath = getFullPath(path, fileName);
        if (Files.exists(filePath)) {
            Files.write(filePath, newContent.getBytes());
            return true;
        }
        return false;
    }
    
    // 6. Borrar archivo (CORREGIDO)
    public static void deleteFile(String path, String fileName) throws IOException {
        Path filePath = getFullPath(path, fileName);
        Files.deleteIfExists(filePath);
    }
    
    // 7. Contar caracteres
    public static int countChars(String path, String fileName) throws IOException {
        String content = showFile(path, fileName);
        return content.length();
    }
    
    // 8. Contar palabras
    public static int countWords(String path, String fileName) throws IOException {
        String content = showFile(path, fileName);
        return content.split("\\s+").length;
    }
    
    // 9. Reemplazar palabras
    public static String swapWords(String path, String fileName, String oldWord, String newWord) throws IOException {
        String content = showFile(path, fileName);
        String newContent = content.replaceAll(oldWord, newWord);
        overWriteFile(path, fileName, newContent);
        return newContent;
    }
    
    // 10. Crear PDF (versión básica)
    public static void printPDF(String path, String fileName) throws IOException {
        Path pdfPath = getFullPath(path, fileName + ".pdf");
        Files.createDirectories(pdfPath.getParent());
        Files.write(pdfPath, new byte[0]); // PDF vacío
    }
}