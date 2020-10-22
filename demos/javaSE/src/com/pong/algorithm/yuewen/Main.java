package com.pong.algorithm.yuewen;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public String removeDuplicatedChars (String str) {
        // write code here
        Set<Character> set = new HashSet<>();
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            set.add(aChar);
        }
        String result = "";
        for (Character character : set) {
            result = result.concat(String.valueOf(character));
        }
        return result;
    }

    public static void main(String[] args) {
        Main main = new Main();
        String s = main.removeDuplicatedChars("AAAAA");
        System.out.println(s);
    }
}
