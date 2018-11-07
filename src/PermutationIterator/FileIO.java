/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PermutationIterator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ALberto del Rosario Déniz
 */
public class FileIO {
    private static int maxWeight;
    
    public static Integer[] initializeArray(String filename) throws IOException {
        
        try(Scanner scanner = new Scanner(new File(filename))){
            Integer[] arr = new Integer[scanner.nextInt()];
            maxWeight = scanner.nextInt();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = scanner.nextInt();
            }
            return arr;
        }
    }

    public static int getMaxWeight() {
        return maxWeight;
    }
    
}
