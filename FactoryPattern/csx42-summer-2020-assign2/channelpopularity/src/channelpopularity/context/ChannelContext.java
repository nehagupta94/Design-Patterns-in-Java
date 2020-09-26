package channelpopularity.context;

import channelpopularity.operation.Operation;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.Results;

import java.util.HashMap;
import java.util.Map;

public class ChannelContext implements ContextI {

    public double getPopularityScore() {
        return popularityScore;
    }

    public void setPopularityScore(double popularityScore) {
        this.popularityScore = popularityScore;
    }

    public Map<String, Map<String, Integer>> getVideos() {
        return videos;
    }

    Map<String,Map<String,Integer>> videos = new HashMap<>();

    Map<String, Map<String, Integer>> vidMap = new HashMap<>();

    double popularityScore;

    private StateI curState;

    private Map<StateName, StateI> availableStates = new HashMap<>();
    Results results;
    SimpleStateFactoryI factory;

    public ChannelContext(SimpleStateFactoryI stateFactoryIn,Results res) {
        //addValuesInMap(stateNames);
        // initialize states using factory instance and the provided state names.
        // initialize current state.
        results = res;
        factory = stateFactoryIn;
        for (StateName state : StateName.values())
            availableStates.put(state,
                    factory.create(state,this,res));
        setCurrentState(StateName.UNPOPULAR);
    }

    // Called by the States based on their logic of what the machine state should change to.
    public void setCurrentState(StateName nextState) {
        if (availableStates.containsKey(nextState)) { // for safety.
            curState = availableStates.get(nextState);
        }
    }

    @Override
    public void operation(Map<String, Object> map) {
        switch((Operation)map.get("operation")){
            case ADD_VIDEO: {
                curState.addVideo(map);
                break;
            }
            case REMOVE_VIDEO: {
                curState.removeVideo(map);
                break;
            }
            case METRICS: {
                curState.updateMetrics(map);
                break;
            }
            case AD_REQUEST: {
                curState.adRequest(map);
                break;
            }
        }
    }
    }




