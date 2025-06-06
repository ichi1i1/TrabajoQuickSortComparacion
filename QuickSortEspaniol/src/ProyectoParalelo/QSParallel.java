package ProyectoParalelo;

import java.util.Random;
import java.util.Scanner;

public class QSParallel implements Runnable {
    int[] datos;
    int inicio, fin;

    QSParallel(int[] datos, int inicio, int fin) {
        this.datos = datos;
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run() {
       quickSort
       (this.datos, this.inicio, this.fin);
    }

    static void intercambiar(int[] datos, int i, int j) {
        int tmp = datos[i];
        datos[i] = datos[j];
        datos[j] = tmp;
    }

    static int particion(int[] datos, int inicio, int fin) {
        if (inicio == fin)
            return inicio;
        int pivote = datos[fin];
        int s = inicio - 1;
        for (int i = inicio; i < fin; i++)
            if (datos[i] <= pivote)
                intercambiar(datos, ++s, i);
        intercambiar(datos, ++s, fin);
        return s;
    }

    static void quickSort(int[] datos, int inicio, int fin) {
        if (fin <= inicio)
            return;
        int s = particion(datos, inicio, fin);
        quickSort(datos, inicio, s - 1);
        quickSort(datos, s + 1, fin);
    }

    // Generar arreglo aleatorio
    static int[] generarArregloAleatorio(int n, int rango) {
        Random random = new Random();
        int[] datos = new int[n];
        for (int i = 0; i < n; i++) {
            datos[i] = random.nextInt(rango);
        }
        return datos;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Cantidad total de elementos: ");
        int num = in.nextInt();

        int[] datos = generarArregloAleatorio(num, 1_000_000);

        double tiempoInicio = System.nanoTime();

        // Particion inicial para dividir el arreglo en dos
        int s = particion
       (datos, 0, datos.length - 1);

        // Crear y lanzar dos hilos para ordenar las dos mitades en paralelo
        Thread t1 = new Thread
       (new QSParallel(datos, 0, s - 1));
        Thread t2 = new Thread
        (new QSParallel(datos, s + 1, datos.length - 1));

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        double tiempoFin = System.nanoTime();
        double tiempoTotal = tiempoFin - tiempoInicio;

        System.out.println("Tiempo total de ejecución  " + tiempoTotal);
    }
}