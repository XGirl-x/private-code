package org.example.LeetCode;

/**
 * 递归乘法，写一个递归函数，不适用*运算符，实现两个正整数相乘，可以使用加号，减号，位移，但要吝啬一些
 * 2 * 3 = 3 + (1 * 3)
 * 4 * 3 = 4 + (4 * 2)
 */
public class RecursiveMultiply {

    public int multiply(int A, int B) {
        if (A == 0 || B == 0) {
            return 0;
        }
        if (A < B) {
            return B + multiply(A -1, B);
        }
        return A + multiply(A, B -1);
    }

    public static void main(String[] args) {
        RecursiveMultiply recursiveMultiply = new RecursiveMultiply();
        int i = recursiveMultiply.multiply(7, 3);
        System.out.println(i);
    }

}
