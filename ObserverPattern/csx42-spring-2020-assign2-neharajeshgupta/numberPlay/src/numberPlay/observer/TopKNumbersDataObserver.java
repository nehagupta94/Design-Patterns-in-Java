package numberPlay.observer;

import numberPlay.util.Event;
import numberPlay.util.PersisterI;
import numberPlay.util.TopKNumbersData;
import numberPlay.util.TopKNumbersResultsI;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * TopKNumbersDataObserver is the Observer for TopKNumbersData data and it listens
 * to all the events(Int,Float,Process Complete).
 * It is responsible to calculate the Top K numbers in that window frame.
 *
 *
 * @author  Neha Gupta
 */

public class TopKNumbersDataObserver implements ObserverI {

    private static final String PROCESSING_COMPLETE = "PROCESSING_COMPLETE";
    private final TopKNumbersResultsI topKNumbersResults;
    private final PersisterI topKNumbersPersister;
    PriorityQueue<Double> pq= new PriorityQueue<>();

    private TopKNumbersDataObserver(){
        topKNumbersPersister = null;
        topKNumbersResults = null; }

    public TopKNumbersDataObserver(TopKNumbersResultsI tkn) {
        this.topKNumbersResults = tkn;
        topKNumbersPersister = (PersisterI) tkn;
    }

    @Override
    public void update(Event event, EventData data) {
        if(!event.toString().equals(PROCESSING_COMPLETE)){
            if(topKNumbersResults != null)
                topKNumbersResults.store(calculateTopK(TopKNumbersData.getK(),data.getData().toString()));
        }else{
            topKNumbersPersister.writeToFile();
            topKNumbersPersister.close();
        }

    }

    private List<Double> calculateTopK(int k, String data) {
        if(pq.size() >= k)
            pq.poll();
        pq.add(Double.parseDouble(data));
        return new ArrayList<>(pq);
    }

}
