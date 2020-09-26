package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;

import java.util.Map;

/**
 * * This class inherits Abstract State.
 */

public class MildlyPopularState extends AbstractState {

    public MildlyPopularState(ContextI ctx, Results res) {
        super(ctx, res, StateName.MILDLY_POPULAR);
    }


    @Override
    public boolean approveAd(int length) {
        return 2 <= length && length <= 20;
    }
}
