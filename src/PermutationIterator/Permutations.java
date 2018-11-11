package PermutationIterator;

import java.io.IOException;
import java.util.Arrays;

public class Permutations{
    private static Integer[] bestOrder;
    private static int bestTravels;
    private static int maxWeight;
    private static boolean debugIn = false;
    private static boolean debugOut = false;
    private static boolean debugComp = false;
    private static boolean debugTime = false;
    
    public static void main(String[] args) throws IOException{
        double startTime = System.nanoTime();
        Integer[]list = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-di")){
                debugIn = true;
            }
            
            if (args[i].equals("-do")){
                debugOut = true;
            }
            
            if (args[i].equals("-dc")){
                debugComp = true;
            }
            
            if (args[i].equals("-dt")){
                debugTime = true;
            }
            
            if (args[i].equals("-f") && i < args.length - 1){
               list = FileIO.initializeArray(args[i+1]);
               maxWeight = FileIO.getMaxWeight();
               i++;
            }
            
            if (args[i].equals("-n") && i < args.length - 1){
                try{
                    maxWeight = Integer.parseInt(args[i+1]);
                }catch(NumberFormatException e){
                    System.out.println("Error: Parametro -n incorrecto");
                    System.exit(0);
                }
               i++;
            }
        }
        
        if(list != null){
            PermutationIterator<Integer> perm;
            perm = new PermutationIterator<>(list);

            //do in debug input----------------------------------------
            if (debugIn == true){
                System.out.println("*** Input");
                for (int i = 0; i < list.length; i++) {
                    System.out.println((i+1) + " => " + list[i]);
                }
                System.out.println("-------------- Max_Weight = " + maxWeight);
            }
            ///--------------------------------------------------------
            
            int count = 0;
            int t;
            Integer[] arr;
            bestTravels = list.length;
            
            while(perm.hasNext()){
                arr = perm.next();
                t = countTravels(arr);
                count++;
                
                if (t < bestTravels){
                    bestOrder = arr.clone();
                    bestTravels = t;

                    //do in debug comp-----------------------------------------
                    if (debugComp == true){
                        System.out.print("Order: " + Arrays.toString(bestOrder));
                        System.out.println(" => Travels: " + bestTravels);
                    }
                    //---------------------------------------------------------
                }
            }
            double stopTime = System.nanoTime();
            double elapsedTime = (stopTime - startTime)/1000000000;

             //do in debug output-------------------------------------------
             if (debugOut == true){
                System.out.println("------------------------------");
                System.out.println("Combinations tested: " + count);
                System.out.println(" Best computed item selection: ");
                System.out.println("  Best Order: " + Arrays.toString(bestOrder));
                System.out.println("   Travels: " + bestTravels);
             }
            //-------------------------------------------------------------- 
            
            //do in debug time
            if (debugTime == true){
                System.out.println("    Time: " + elapsedTime + " seconds");
            }
            //-------------------------------------------------------------- 
        }
        
    }
    
    private static int countTravels(Integer[] arr){
        int travels = 0;
        int weight = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxWeight){
                System.out.println("Error: El peso " + arr[i] + " excede la carga máxima de " + maxWeight);
                System.exit(0);
                return -1;
            }
            weight = weight + arr[i];
            if (weight >= maxWeight){
                travels++;
                if (weight > maxWeight){
                    weight = arr[i];
                }else if(weight == maxWeight){
                    weight = 0;
                }
            }
        }
        if (weight != 0) travels = travels + 1;
        return travels;
    }
}