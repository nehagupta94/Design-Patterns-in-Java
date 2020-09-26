package numberPlay.observer;

import numberPlay.util.Event;
import numberPlay.util.PersisterI;
import numberPlay.util.RunningAverageData;
import numberPlay.util.RunningAverageResultsI;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * RunningAverageObserver is the Observer for NumberPeaks data and it listens
 * to all the events(Int,Process Complete).
 * It is responsible to calculate the running average of the data as and when given
 * with respect to a particular window size which is provided
 * at run time.
 *
 *
 * @author  Neha Gupta
 */

public class RunningAverageObserver implements ObserverI {

    private static final String PROCESSING_COMPLETE = "PROCESSING_COMPLETE";
    final private RunningAverageResultsI runningAverageResults;
    final private PersisterI runningAveragePersister;
    ArrayList<Integer> arr = new ArrayList<>();
    float sum,c;
    int n;

    private RunningAverageObserver() {
        runningAverageResults = null;
        runningAveragePersister = null;
    }

    public RunningAverageObserver(RunningAverageResultsI runningAverageResultsIn) {
        runningAverageResults = runningAverageResultsIn;
        runningAveragePersister = (PersisterI) runningAverageResultsIn;
    }

    @Override
    public void update(Event event, EventData data) {
        if(!event.toString().equals(PROCESSING_COMPLETE)){
            if(runningAverageResults != null){
                float average = calculateRunningAverage(RunningAverageData.getWindowSize(),Integer.parseInt(data.getData().toString()));
                BigDecimal bd = new BigDecimal(average).setScale(2, RoundingMode.HALF_UP);
                runningAverageResults.store(bd.doubleValue());
            }
        }else{
            runningAveragePersister.writeToFile();
            runningAveragePersister.close();
        }

    }

    public float calculateRunningAverage(int windowSize,int data){
        sum=0;
        c=0;

        if(n>=windowSize)
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

}
