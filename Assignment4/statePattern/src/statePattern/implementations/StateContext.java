package implementations;

import headers.State;
import util.FileProcessor;
import util.Metrics;
import util.Results;
import util.BackEnd;

import java.io.IOException;

/** State context class, holds getters setters to all the states */

public class StateContext {
    State basic;
    State moderatelyExpensive;
    State superExpensive;
    State currentState;
    Results results;
    Metrics metrics;

    public StateContext(){
        metrics = new Metrics();
        results = new Results();
    }

    public void initializeStates(){
        basic = new BasicState(this);
        moderatelyExpensive = new LuxuriousState(this);
        superExpensive = new ExtravagantState(this);

        currentState = basic;
    }

    public State getBasicState(){
        return basic;
    }

    public State getModeratelyExpensiveState(){
        return moderatelyExpensive;
    }

    public State getSuperExpensive(){
        return superExpensive;
    }

    public void setCurrentState(State currentStateIn) {
        this.currentState = currentStateIn;
    }

    public Results update(FileProcessor inputFile, FileProcessor availableItemsFile, String windowSize) throws IOException {
        String inputLine = inputFile.poll();
        String itemsLine = availableItemsFile.poll();
        BackEnd store = new BackEnd();
        metrics.setWindowSize(Integer.parseInt(windowSize));
        while(itemsLine != null){
            store.storeItemsFile(itemsLine);
            itemsLine = availableItemsFile.poll();
        }
        while(inputLine != null){
            String[] input = store.extractValues(inputLine);
            String r = currentState.agreeDisasgree(input,metrics,store);
            results.addResults(r);
            inputLine = inputFile.poll();
        }
        return results;
    }
}
