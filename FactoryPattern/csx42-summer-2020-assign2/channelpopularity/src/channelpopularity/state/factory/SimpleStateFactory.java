package channelpopularity.state.factory;

import channelpopularity.context.ContextI;
import channelpopularity.state.*;


import java.util.List;

import channelpopularity.state.StateName;
import channelpopularity.util.Results;

public class SimpleStateFactory implements SimpleStateFactoryI {

    /**
     *
     * @param stateName
     * @param ctx
     * @param res
     * @exception  Channel is in INVALID STATE.
     */


    @Override
    public StateI create(StateName stateName, ContextI ctx, Results res) {
            switch (stateName){
                case UNPOPULAR:
                    return new UnpopularState(ctx, res);
                case MILDLY_POPULAR:
                    return new MildlyPopularState(ctx, res);
                case ULTRA_POPULAR:
                    return new UltraPopularState(ctx, res);
                case HIGHLY_POPULAR:
                    return new HighlyPopularState(ctx, res);
                default:
                    throw new RuntimeException("INVALID STATE");
        }
    }
}
