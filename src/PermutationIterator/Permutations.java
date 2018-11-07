package PermutationIterator;

import java.io.IOException;
import java.util.Arrays;

public class Permutations{
    private static Integer[] bestOrder;
    private static int bestTravels;
    private static int maxWeight;
    
    public static void main(String[] args) throws IOException{
        
        
        Integer[]list = FileIO.initializeArray(args[0]);
        maxWeight = FileIO.getMaxWeight();
        
        PermutationIterator<Integer> perm;
        perm = new PermutationIterator<>(list);
        
        int t;
        Integer[] arr;
        bestOrder = perm.next();
        bestTravels = countTravels(bestOrder);
        //System.out.println("first: " + bestTravels);
         while(perm.hasNext()){
            arr = perm.next();
            //System.out.println(Arrays.toString(arr));
            t = countTravels(arr);
            //System.out.println("travels: " + t);
            if (t < bestTravels){
                bestTravels = t;
                bestOrder = arr.clone();
            }
             
        }
        System.out.println("Best Order: " + Arrays.toString(bestOrder));
        //System.out.println("Travels: " + bestTravels);
        System.out.println("Travels: " + bestTravels);
        System.out.println("Max Weight: " + maxWeight);
        /*int count = 0;
        while(perm.hasNext()){
            
            System.out.println(Arrays.toString(perm.next()));
            count++;
        }
        System.out.println("total: " + count);
        for (String arg : args) {
            System.out.println(arg);
        }*/
        //System.out.println(Arrays.toString(list));
        //System.out.println("Max= " + max);
        
    }
    
    private static int countTravels(Integer[] arr){
        int travels = 0;
        int weight = 0;
        for (int i = 0; i < arr.length; i++) {
            weight = weight + arr[i];
            //System.out.println(weight);
            if (weight >= maxWeight /*|| i == arr.length - 1*/){
                travels++;
                //weight = weight - maxWeight;
                if (weight > maxWeight){
                    weight = arr[i];
                    //System.out.println("mayor");
                }else if(weight == maxWeight){
                    weight = 0;
                    //System.out.println("igual");
                }
            }
            
        }
        if (weight != 0) travels = travels + 1;
        return travels;
    }
}