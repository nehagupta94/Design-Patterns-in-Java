package numberPlay.observer;

import numberPlay.util.Event;

/**
 * EventData class contains ENUM for three events,
 * This class is will be used in filtering the
 * events and wrapping the event and data into one entity for future ease.
 *
 *
 * @author  Neha Gupta
 */

public class EventData {
    public enum Type {
        INTEGER,
        FLOAT,
        EOF
    }

    private Type t;
    private Object data;

    public Object getData() {
        return data;
    }

    private EventData () {}
    public EventData(String data) {
        if (data == null) {
            this.data = data;
            t = Type.EOF;
        }
        else
            try {
                int i = Integer.parseInt(data);
                this.data = i;
                t = Type.INTEGER;
            } catch (NumberFormatException nex) {
                try {
                    double d = Double.parseDouble(data);
                    this.data = d;
                    t = Type.FLOAT;
                } catch (NumberFormatException nex1) {
                    throw new RuntimeException(String.format("Expected Integer or Float, got : [%s]", data));
                }
            }
    }

    public Event getEvent() {
        if (t.equals(Type.INTEGER)) return Event.INTEGER;
        if (t.equals(Type.FLOAT))   return Event.FLOAT;
        if (t.equals(Type.EOF))     return Event.PROCESSING_COMPLETE;
        return null;
    }
}
