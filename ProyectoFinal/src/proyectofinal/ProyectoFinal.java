package quicksort;

import java.util.Arrays;

/**
 *
 * @author luisa
 */
public class QuickSort {
    
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            
            int pi = partition(array, low, high);
 
            quickSort(array, low, pi - 1);   // parte izquierda del pivote
            quickSort(array, pi + 1, high);  // parte derecha del pivote
        }
    }

    public static int partition(int[] array, int low, int high) {
        // Pivote: el elemento de la derecha
        int pivot = array[high];
 
        // Apuntador del ultimo elemento más pequeño
        int i = low - 1;
 
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                // Avanzar apuntador
                i = i + 1;
                // Intercambiar elementos
                swap(array, i, j);
            }
        }
 
        // Al final intercambiar el pivote
        swap(array, i + 1, high);
 
        // Regresa la posicion final del pivote
        return i + 1;
    }
 
    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    public static void main(String[] args) {
     int[] datos = {10, 7, 8, 9, 1, 5, 3, 12, 2};
 
        System.out.println("Antes:   " + Arrays.toString(datos));
        quickSort(datos, 0, datos.length - 1);
        System.out.println("Después: " + Arrays.toString(datos));   
    }
    
}
