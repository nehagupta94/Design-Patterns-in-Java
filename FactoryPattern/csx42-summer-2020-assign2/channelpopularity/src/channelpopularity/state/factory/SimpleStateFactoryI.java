package channelpopularity.state.factory;

import channelpopularity.context.ContextI;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.util.Results;

/**
 * This is just an interface which is being implemented by SimpleStateFactory Class with a create function decalred which takes StateName, Context and Results as it's arguments.
 */

public interface SimpleStateFactoryI {

    StateI create(StateName stateName, ContextI ctx, Results res);

}
