package PermutationIterator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PermutationIterator<Integer> implements  Iterator<Integer[]>{
    
    private Integer[] arr;
    private int[] ind;
    private boolean has_next;

    public Integer[] output;//next() returns this array, make it public

    PermutationIterator(Integer[] arr){
        this.arr = arr.clone();
        ind = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            int aux = i;
            for (int j = 0; j < arr.length; j++) {
                Integer n = arr[i];
                if (n == arr[j]){
                    aux=j;
                    break;
                }
            }
            ind[i]=aux;
        }
        
        Arrays.sort(ind);//start with ascending sequence of integers

        //output = new E[arr.length]; <-- cannot do in Java with generics, so use reflection
        output = (Integer[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
        has_next = true;
    }

    @Override
    public boolean hasNext() {
        return has_next;
    }

    /**
     * Computes next permutations. Same array instance is returned every time!
     * @return
     */
    @Override
    public Integer[] next() {
        if (!has_next)
            throw new NoSuchElementException();

        for(int i = 0; i < ind.length; i++){
            output[i] = arr[ind[i]];
        }


        //get next permutation
        has_next = false;
        for(int tail = ind.length - 1;tail > 0;tail--){
            if (ind[tail - 1] < ind[tail]){//still increasing

                //find last element which does not exceed ind[tail-1]
                int s = ind.length - 1;
                while(ind[tail-1] >= ind[s])
                    s--;

                swap(ind, tail-1, s);

                //reverse order of elements in the tail
                for(int i = tail, j = ind.length - 1; i < j; i++, j--){
                    swap(ind, i, j);
                }
                has_next = true;
                break;
            }

        }
        return output;
    }

    private void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException(
                    "May not remove elements from a permutation");
    }
}
