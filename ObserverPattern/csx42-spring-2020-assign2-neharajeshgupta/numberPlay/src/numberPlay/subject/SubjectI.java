package numberPlay.subject;

import numberPlay.filter.FilterI;
import numberPlay.observer.ObserverI;


/**
 * Subject Interface which contains
 * methods like register, remove, notify.
 *
 *
 * @author  Neha Gupta
 */

public interface SubjectI {

    void registerObserver(ObserverI o, FilterI f);
    void removeObserver(ObserverI o);
    void notifyObservers(String data);

}
