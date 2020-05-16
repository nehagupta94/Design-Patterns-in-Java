package numberPlay.subject;

import numberPlay.filter.FilterI;
import numberPlay.observer.EventData;
import numberPlay.observer.ObserverI;
import numberPlay.util.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Publisher is the library of the Subject Interface.
 * It will register, remove and notify observers.
 *
 * It uses the EventData class which acts as the filter
 * to which the observers are registered.
 *
 *
 * @author  Neha Gupta
 */

public class Publisher implements SubjectI {
    private Map<FilterI,List<ObserverI>> observers;

    public Publisher(){
        observers = new HashMap<>();
    }

    @Override
    public void registerObserver(ObserverI o,FilterI f) {
        if(!observers.containsKey(f)){
            observers.put(f,new ArrayList<>());
        }
        observers.get(f).add(o);
    }

    @Override
    public void removeObserver(ObserverI o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String data) {
        EventData d = new EventData(data);
        Event e = d.getEvent();
        for (Map.Entry<FilterI, List<ObserverI>> entry : observers.entrySet()){
            if (entry.getKey().check(e)){
                for (ObserverI ob : entry.getValue()){
                    ob.update(e, d);
                }

            }

        }

    }
}
