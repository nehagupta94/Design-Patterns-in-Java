package util;

import java.util.ArrayList;
import java.util.List;

/** Calculations for running average */

public class Metrics {
    private List<Integer> arr;
    private int n;
    private int windowSize;

    public Metrics(){
        arr = new ArrayList<>();
    }

    public int calculateRunningAverage(int data){
        int sum=0;
        int c=0;
         if(n>=getWindowSize())
            arr.remove(0);
        arr.add(data);
        n++;

        for(int i=0;i<arr.size();i++)
        {
            sum=sum+arr.get(i);
            c++;
        }
        return (sum/c);
    }


    public int getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }


}
