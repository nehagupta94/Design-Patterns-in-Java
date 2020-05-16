package numberPlay.observer;


import numberPlay.util.Event;
import numberPlay.util.NumberPeaksResultsI;
import numberPlay.util.PersisterI;

/**
 * NumberPeaksDataObserver is the Observer for NumberPeaks data and it listens
 * to all the events(Int,Float,Process Complete).
 * It is responsible to calculate the fluctuations in data observed.
 *
 *
 * @author  Neha Gupta
 */

public class NumberPeaksDataObserver implements ObserverI {
    private static final String PROCESSING_COMPLETE = "PROCESSING_COMPLETE";
    final private NumberPeaksResultsI numberPeaksResults;
    final private PersisterI numberPeaksPersister;
    static int count = 0;
    private Double peak, current;

    private NumberPeaksDataObserver() {
        numberPeaksResults = null;
        numberPeaksPersister = null;
    }
    public NumberPeaksDataObserver(NumberPeaksResultsI numberPeaksResultsIn) {
        numberPeaksResults = numberPeaksResultsIn;
        numberPeaksPersister = (PersisterI) numberPeaksResultsIn;
    }

    @Override
    public void update(Event event, EventData data) {
        try {
            if(!event.toString().equals(PROCESSING_COMPLETE)){
                if (numberPeaksResults != null){
                    Double value = calculatePeak(Double.parseDouble(data.getData().toString()));
                    if(value != null)
                        numberPeaksResults.store(calculatePeak(Double.parseDouble(data.getData().toString())));
                }
            }else{
                numberPeaksPersister.writeToFile();
                numberPeaksPersister.close();
            }
        } catch (ClassCastException cce) {

        }
    }

    public Double calculatePeak(Double number){
        if(!(count<2)){
            if (peak == null || current.doubleValue() > number.doubleValue()) {
                peak = current;
                count++;
            }
        }

        current = number;
        count++;
        return peak;
    }
}
