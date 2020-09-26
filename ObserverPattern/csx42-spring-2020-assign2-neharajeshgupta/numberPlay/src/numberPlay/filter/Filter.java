package numberPlay.filter;

import numberPlay.util.Event;

/**
 * Filter class implements the Filter interface which will
 * be responsible for filtering the events using the check
 * method.
 *
 *
 * @author  Neha Gupta
 */

public class Filter implements FilterI{

    Event event;

    public Filter(Event e) {
        event = e;
    }

    @Override
    public boolean check(Event e) {
        return event.equals(e);
    }

}
