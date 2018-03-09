package mix;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class MovingAverage {
    private Queue<Integer> window = new ArrayDeque<>(); // queue holds the max elements equal to the windowsize
    private final int windowSize;
    private double sum;


    MovingAverage(int windowSize){
        this.windowSize = windowSize;
    }

    public void nextNumber(int num){
        sum += num;
        window.add(num);
        if(window.size() > windowSize){
            sum -= window.remove();
        }
    }

    public double average(){
        if (window.size() == 0) return 0.0;
        return sum/window.size();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Window size:");
        int windowSize = in.nextInt();
        MovingAverage average = new MovingAverage(windowSize);
        for(int i =0;i < 100;i++) {
            System.out.print("Number:");
            average.nextNumber((int) Double.parseDouble(in.next()));
            System.out.println("Average:"+average.average()+ "\r");
        }
    }
}

