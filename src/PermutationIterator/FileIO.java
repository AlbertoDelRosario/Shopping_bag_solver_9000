package PermutationIterator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
    private static int maxWeight;
    
    public static Integer[] initializeArray(String fileName) throws IOException {
        
        try(Scanner scanner = new Scanner(new File(fileName))){
            Integer[] arr = new Integer[scanner.nextInt()];
            maxWeight = scanner.nextInt();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = scanner.nextInt();
            }
            return arr;
        }
        catch(IOException e){
            System.out.println("Error: No se ha encontrado el fichero " + fileName);
            System.exit(0);
            return null;
        }
    }

    public static int getMaxWeight() {
        return maxWeight;
    }
    
}
