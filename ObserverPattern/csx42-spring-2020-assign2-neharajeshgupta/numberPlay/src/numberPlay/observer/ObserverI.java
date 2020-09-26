package numberPlay.observer;

import numberPlay.util.Event;

public interface ObserverI {
    void update(Event event, EventData data);
}