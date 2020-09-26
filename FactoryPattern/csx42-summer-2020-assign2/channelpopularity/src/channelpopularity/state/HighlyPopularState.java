package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;

import java.util.Map;

/**
 * This class inherits Abstract State.
 */

public class HighlyPopularState extends AbstractState {

    public HighlyPopularState(ContextI ctx, Results res) {
        super(ctx, res, StateName.HIGHLY_POPULAR);
    }


    @Override
    public boolean approveAd(int length) {
        return 2 <= length && length <= 30;
    }
}
