package com.pong.algorithm.baidu;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countPeople = scanner.nextInt();
        int countNums = scanner.nextInt();
        int changePeople = scanner.nextInt();
        int[] peoples = new int[countPeople];
        for (int i = 0; i < countPeople; i++) {
            peoples[i] = scanner.nextInt();
        }
        System.out.println(carryMan(countPeople, countNums, changePeople, peoples));
        scanner.close();
    }

    public static int carryMan(int n,int t,int c,int[]value){
        int sumValue=0;
        int result=0;
        for(int i=0;i<c;i++){
            sumValue+=value[i];
        }
        if(sumValue<=t){
            result++;
        }
        for(int i=c;i<n;i++){
            sumValue=sumValue-value[i-c]+value[i];
            if(sumValue<=t)
                result++;
        }
        return result;
    }

    public static int getTimes(int countPeople, int countNums, int changePeople, int[] people) {
        int count = 0;
        int countNum = 0;
        int position = 0;
        if (changePeople > countPeople || countNums < 0) return 0;
        for (int j = 0; j < people.length; j++) {
            for (int i = 0; i < changePeople; i++) {
                if (j + i < countPeople) {
                    countNum += people[j + i];
                    position++;
                }
            }
            if (countNum <= countNums && position != 0) count++;
            countNum = 0;
            position = 0;
        }
        return count;
    }
}