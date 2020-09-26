package channelpopularity.context;

import channelpopularity.state.StateName;

import java.util.Map;

/**
 * This is the Context Interface which is implemented by the ChannelContext Class.
 *
 *The method setCurrentState takes StateName as an argument and is used to set the currentState.
 * The method operation takes Map as an argument and calls the different operations of the states accordingly.
 * getPopularityScore and setPopularityScore are just the getter and setter methods for PopularityScore.
 * GetVideos is another getter method.
 * */

public interface ContextI {

    void setCurrentState(StateName nextState);
    void operation(Map<String,Object> map);
     double getPopularityScore();
     void setPopularityScore(double popularityScore);
     Map<String, Map<String, Integer>> getVideos();
}
