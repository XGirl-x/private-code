package org.example.aopTest;

public class MathCalculator {

    public int div(int i, int j) {
        return i + j;
    }

    public void get() {
        int q = div(1,2);
    }

    public MathCalculator() {
        System.out.println("父类");
    }
}
