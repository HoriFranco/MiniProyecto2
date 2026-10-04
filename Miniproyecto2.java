/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miniproyecto2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author HoriAndréFrancoRuiz
 */
public class Miniproyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Scanner lectura = new Scanner(System.in);
        ArbolBinario arbol = new ArbolBinario();
        int opcion;
 
        do {
            System.out.println("\nARBOL BINARIO");
            opcion = leerEntero(lectura, "Elige una opcion: ");
            System.out.println("1. Insertar nodo");
            System.out.println("2. Eliminar nodo");
            System.out.println("3. Buscar nodo (POR NIVEL)");
            System.out.println("4. Mostrar arbol");
            System.out.println("5. Recorrido PreOrden");
            System.out.println("6. Recorrido InOrden");
            System.out.println("7. Recorrido PostOrden");
            System.out.println("8. Cargar arbol desde archivo");
            System.out.println("9. Guardar recorridos en archivo");
            System.out.println("0. Salir");
            
 
            switch (opcion) {
                case 1:
                    int nuevo = leerEntero(lectura, "Valor a insertar: ");
                    if (arbol.insertar(nuevo)) {
                        System.out.println("Se inserto el " + nuevo);
                    } else {
                        System.out.println("El " + nuevo + " ya existe, no se inserto.");
                    }
                    break;
 
                case 2:
                    int borrar = leerEntero(lectura, "Valor a eliminar: ");
                    if (arbol.eliminar(borrar)) {
                        System.out.println("Se elimino el " + borrar);
                    } else {
                        System.out.println("El " + borrar + " no existe en el arbol.");
                    }
                    break;
 
                case 3:
                    int buscado = leerEntero(lectura, "Valor a buscar: ");
                    int nivel = arbol.buscarNivel(buscado);
                    if (nivel == -1) {
                        System.out.println("El " + buscado + " no se encontro.");
                    } else {
                        System.out.println("El " + buscado + " esta en el nivel " + nivel
                                + " (la raiz es el nivel 0).");
                    }
                    break;
 
                case 4:
                    if (arbol.estaVacio()) {
                        System.out.println("El arbol esta vacio.");
                    } else {
                        System.out.println("\n(Arbol girado: raiz a la izquierda, mayores arriba, menores abajo)\n");
                        System.out.println(arbol.dibujar());
                    }
                    break;
 
                case 5:
                    mostrar("PreOrden", arbol.preOrden(), arbol);
                    break;
 
                case 6:
                    mostrar("InOrden", arbol.inOrden(), arbol);
                    break;
 
                case 7:
                    mostrar("PostOrden", arbol.postOrden(), arbol);
                    break;
 
                case 8:
                    cargarArchivo(lectura, arbol);
                    break;
 
                case 9:
                    guardarResultados(lectura, arbol);
                    break;
 
                case 0:
                    System.out.println("Hasta luego.");
                    break;
 
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }
 
    //Otros metodos
 
    private static void mostrar(String nombre, String resultado, ArbolBinario arbol) {
        if (arbol.estaVacio()) {
            System.out.println("El arbol esta vacio.");
        } else {
            System.out.println(nombre + ": " + resultado);
        }
    }
 
    /** Pide un numero entero y repite la pregunta si el usuario escribe otra cosa. */
    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un numero entero, intenta de nuevo.");
            }
        }
    }
 
    // El txt
    private static void cargarArchivo(Scanner scanner, ArbolBinario arbol) {
        System.out.print("Nombre del archivo: ");
        String ruta = scanner.nextLine().trim();
 
        try (BufferedReader leerarchivo = new BufferedReader(new FileReader(ruta))) {
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = leerarchivo.readLine()) != null) {
                contenido.append(linea).append(",");
            }
 
            arbol.vaciar();                       
            for (String parte : contenido.toString().split(",")) {
                parte = parte.trim();
                if (parte.isEmpty()) {
                    continue;
                }
                try {
                    arbol.insertar(Integer.parseInt(parte));
                } catch (NumberFormatException e) {
                    System.out.println("Este" + parte + "no es un numero.");
                }
            }
            System.out.println("Arbol creado con " + arbol.contarNodos() + " nodos.");
            System.out.println("Ahora puedes agregar mas con la opcion 1.");
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo. Revisa el nombre o la ruta.");
        }
    }
 
    // Guardar los recorridos 
    private static void guardarResultados(Scanner scanner, ArbolBinario arbol) {
        if (arbol.estaVacio()) {
            System.out.println("El arbol esta vacio, no hay nada que guardar.");
            return;
        }
        System.out.print("Nombre del archivo de salida (ej. resultados.txt): ");
        String ruta = scanner.nextLine().trim();
 
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            pw.println("Resultados de los recorridos del arbol");
            pw.println("PreOrden:  " + arbol.preOrden());
            pw.println("InOrden:   " + arbol.inOrden());
            pw.println("PostOrden: " + arbol.postOrden());
            System.out.println("Resultados guardados en " + ruta);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo.");
        }
    
    }
    
}
