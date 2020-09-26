package headers;

import util.BackEnd;
import util.Metrics;

/** State interface */

public interface State {
    String MONEY = "money";
    String ITEM = "item";

    String agreeDisasgree(String[] inputLine, Metrics metrics, BackEnd store);
}
