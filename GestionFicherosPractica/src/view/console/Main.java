package view.console;

import model.Funciones;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        
        do {
            printMenu();
            
            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1: createPersonalFolder(); break;
                    case 2: createOrUpdateFile(); break;
                    case 3: listFiles(); break;
                    case 4: showFileContent(); break;
                    case 5: overwriteFile(); break;
                    case 6: deleteFile(); break;
                    case 7: countCharsInFile(); break;
                    case 8: countWordsInFile(); break;
                    case 9: swapWordsInFile(); break;
                    case 10: createPDF(); break;
                    case 0: exit = true; break;
                    default: System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace(); // Para debug
            }
        } while (!exit);
        
        scanner.close();
        System.out.println("Programa terminado.");
    }

    private static void printMenu() {
        System.out.println("\n--- MENU GESTION DE ARCHIVOS DE VICTOR BARCELO :)) ---");
        System.out.println("1. Crear carpeta personal");
        System.out.println("2. Crear/actualizar archivo");
        System.out.println("3. Listar archivos");
        System.out.println("4. Mostrar contenido archivo");
        System.out.println("5. Sobrescribir archivo");
        System.out.println("6. Borrar archivo");
        System.out.println("7. Contar caracteres");
        System.out.println("8. Contar palabras");
        System.out.println("9. Reemplazar palabras");
        System.out.println("10. Crear PDF");
        System.out.println("0. Salir");
        System.out.print("Seleccione opcion: ");
    }

    private static void createPersonalFolder() throws IOException {
        System.out.print("Nombre de la carpeta: ");
        String folderName = scanner.nextLine();
        Funciones.createFolder(folderName);
        System.out.println("Carpeta '" + folderName + "' creada/verificada.");
    }

    private static void createOrUpdateFile() throws IOException {
        System.out.print("Ruta de la carpeta (ej: 'Carpeta_1_Victor' o ruta completa): ");
        String path = scanner.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scanner.nextLine();
        System.out.print("Contenido: ");
        String content = scanner.nextLine();
        Funciones.createFile(path, fileName, content);
        System.out.println("Archivo actualizado en: " + Paths.get(path, fileName).toAbsolutePath());
    }

    private static void listFiles() {
        System.out.print("Ruta de la carpeta (ej: 'Carpeta_1_Victor' o ruta completa): ");
        String path = scanner.nextLine();
        String[] files = Funciones.showListFiles(path);
        
        if (files.length == 0) {
            System.out.println("La carpeta está vacía o no existe.");
        } else {
            System.out.println("Archivos encontrados:");
            for (String file : files) {
                System.out.println("- " + file);
            }
        }
    }

    private static void showFileContent() throws IOException {
        System.out.print("Ruta de la carpeta (ej: 'Carpeta_1_Victor' o ruta completa): ");
        String path = scanner.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scanner.nextLine();
        
        String content = Funciones.showFile(path, fileName);
        System.out.println("\n Contenido de " + fileName + ":");
        System.out.println("----------------------------------------");
        System.out.println(content);
        System.out.println("----------------------------------------");
    }

    private static void overwriteFile() throws IOException {
        System.out.print("Ruta de la carpeta (ej: 'Carpeta_1_Victor' o ruta completa): ");
        String path = scanner.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scanner.nextLine();
        System.out.print("Nuevo contenido: ");
        String newContent = scanner.nextLine();
        
        boolean success = Funciones.overWriteFile(path, fileName, newContent);
        System.out.println(success ? "Archivo sobrescrito." : "El archivo no existe.");
    }

    private static void deleteFile() throws IOException {
        System.out.print("Ruta de la carpeta (ej: 'Carpeta_1_Victor' o ruta completa): ");
        String path = scanner.nextLine();
        System.out.print("Nombre del archivo a borrar: ");
        String fileName = scanner.nextLine();
        
        System.out.println("¿Estas seguro? (S/N)");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("S")) {
            Funciones.deleteFile(path, fileName);
            System.out.println("Archivo borrado (si existía).");
        } else {
            System.out.println("Operacion cancelada.");
        }
    }

    private static void countCharsInFile() throws IOException {
        System.out.print("Ruta de la carpeta (ej: 'Carpeta_1_Victor' o ruta completa): ");
        String path = scanner.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scanner.nextLine();
        
        int chars = Funciones.countChars(path, fileName);
        System.out.println("Caracteres en " + fileName + ": " + chars);
    }

    private static void countWordsInFile() throws IOException {
        System.out.print("Ruta de la carpeta (ej: 'Carpeta_1_Victor' o ruta completa): ");
        String path = scanner.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scanner.nextLine();
        
        int words = Funciones.countWords(path, fileName);
        System.out.println("Palabras en " + fileName + ": " + words);
    }

    private static void swapWordsInFile() throws IOException {
        System.out.print("Ruta de la carpeta (ej: 'Carpeta_1_Victor' o ruta completa): ");
        String path = scanner.nextLine();
        System.out.print("Nombre del archivo: ");
        String fileName = scanner.nextLine();
        System.out.print("Palabra a reemplazar: ");
        String oldWord = scanner.nextLine();
        System.out.print("Nueva palabra: ");
        String newWord = scanner.nextLine();
        
        String newContent = Funciones.swapWords(path, fileName, oldWord, newWord);
        System.out.println("\n🔄 Contenido actualizado:");
        System.out.println("----------------------------------------");
        System.out.println(newContent);
        System.out.println("----------------------------------------");
    }

    private static void createPDF() throws IOException {
        System.out.print("Ruta de la carpeta (ej: 'Carpeta_1_Victor' o ruta completa), si lo quieres crear en la carpeta del proyecto no pongas nada: ");
        String path = scanner.nextLine();
        System.out.print("Nombre del archivo (sin .pdf): ");
        String fileName = scanner.nextLine();
        
        Funciones.printPDF(path, fileName);
        System.out.println(" el PDF ha sido creado en: " + Paths.get(path, fileName + ".pdf").toAbsolutePath());
    }
}