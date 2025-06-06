package ProyectoParalelo;

import java.util.Random;
import java.util.Scanner;

public class QSSerial {

    static void intercambiar(int[] datos, int i, int j) {
        int tmp = datos[i];
        datos[i] = datos[j];
        datos[j] = tmp;
    }

    static int particion(int[] datos, int inicio, int fin) {
        if (inicio == fin) return inicio;
        int pivote = datos[fin];
        int s = inicio - 1;
        for (int i = inicio; i < fin; i++) {
            if (datos[i] <= pivote)
                intercambiar(datos, ++s, i);
        }
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

    static int[] generarArregloAleatorio(int n, int maxValor) {
        Random random = new Random();
        int[] datos = new int[n];
        for (int i = 0; i < n; i++)
            datos[i] = random.nextInt(maxValor);
        return datos;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Cantidad total de elementos: ");
        int num = in.nextInt();
        int[] datos = generarArregloAleatorio(num, 1_000_000);

        double tiempoInicio = System.nanoTime();
        quickSort(datos, 0, datos.length - 1);
        double tiempoFin = System.nanoTime();

        System.out.println(tiempoFin - tiempoInicio); // Tiempo en nanosegundos
    }
}
