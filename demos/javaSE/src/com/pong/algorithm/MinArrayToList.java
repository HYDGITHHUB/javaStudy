package com.pong.algorithm;

public class MinArrayToList {
    public static void main(String[] args) {
        System.out.println(PrintMinNumber(new int[]{2,1}));
    }
    public static String PrintMinNumber(int [] numbers) {
        if(numbers == null || numbers.length == 0)return "";
        for(int i=0; i < numbers.length; i++){
            for(int j = i+1; j < numbers.length; j++){
                int number = numbers[i];
                int number1 = numbers[j];
                int sum1 = Integer.valueOf(numbers[i]+""+numbers[j]);
                int sum2 = Integer.valueOf(numbers[j]+""+numbers[i]);
                if(sum1 > sum2){
                    int temp = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = temp;
                }
            }
        }
        String str = new String("");
        for(int i=0; i < numbers.length; i++)
            str = str + numbers[i];
        return str;

    }
}
