package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;

import java.util.Map;

/**
 * * This class inherits Abstract State.
 */

public class UnpopularState extends AbstractState {

    public UnpopularState(ContextI ctx, Results res) {
        super(ctx, res, StateName.UNPOPULAR);
    }


    @Override
    public boolean approveAd(int length) {
        return 2 <= length && length <= 10;
    }
}

