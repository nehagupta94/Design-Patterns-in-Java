package implementations;

import headers.State;
import util.BackEnd;
import util.Luxury;
import util.Metrics;

/** Basic state, implements the State interface */
public class BasicState implements State {
    StateContext context;

    private BasicState(){}

    public BasicState(StateContext contextIn){
        context = contextIn;
    }

    @Override
    public String agreeDisasgree(String[] inputLine, Metrics metrics, BackEnd store) {
        int money = 0;
        int value = 0;
        String result = null;
            if(inputLine[0].equalsIgnoreCase(MONEY)){
                try{
                    value = Integer.parseInt(inputLine[1]);
                    if(value <= 0)
                        throw new Exception();
                }catch(NumberFormatException e){
                    System.err.println("Exception: ");
                    System.out.println("Exception: "+ e.getMessage().getClass().getName());
                    e.printStackTrace();
                } catch (Exception e) {
                    System.err.println("Exception: Value cannot be <= 0");
                    System.out.println("Exception: "+ e.getMessage().getClass().getName());
                    e.printStackTrace();
                }
                money = metrics.calculateRunningAverage(value);
                if(money >= 10000 && money <50000){
                    context.setCurrentState(context.getModeratelyExpensiveState());
                }else if(money >= 50000){
                    context.setCurrentState(context.getSuperExpensive());
                }
            }else if(inputLine[0].equalsIgnoreCase(ITEM)){
                if(store.isPresent(inputLine[1],Luxury.BASIC)){
                    result = Luxury.BASIC + "::" + inputLine[1] + "--" + "YES";
                }else{
                    result = Luxury.BASIC + "::" + inputLine[1] + "--" + "NO";
                }
            }
        return result;
    }
}
