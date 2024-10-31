package org.example;


import java.util.*;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq =  new HashMap<>();
    public static final int numberThread = 1000;
    public static final int numberChar = 100;
    public static void main(String[] args) {
        for (int i = 0; i < numberThread; i++) {
            new Thread(() ->{
                String word = generateRoute("RLRFR", numberChar);
                int countR = countRight(word);
                synchronized (sizeToFreq){
                    sizeToFreq.put(countR, sizeToFreq.getOrDefault(countR, 0)+1);
                }

            }).start();
        }
        int maxFreq = 0;
        int mostRepeatedSize = 0;
        System.out.println("Самое частое количество повторений:");
        for (Map.Entry<Integer, Integer> entry : sizeToFreq.entrySet()) {
            int size = entry.getKey();
            int freq = entry.getValue();
            System.out.println("- " + size + " (" + freq + " раз)");
            if (freq > maxFreq) {
                maxFreq = freq;
                mostRepeatedSize = size;
            }
        }
        System.out.println("Самое частое количество повторений " + mostRepeatedSize + " (встретилось " + maxFreq + " раз)");


    }
    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
    public static int countRight(String word){
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == 'R'){
                count++;
            }
        }
        return count;
    }
}