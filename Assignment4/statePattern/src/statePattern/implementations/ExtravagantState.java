package implementations;

import headers.State;
import util.BackEnd;
import util.Luxury;
import util.Metrics;

/** Extravagant state, implements the State interface */
public class ExtravagantState implements State {
    StateContext context;

    private ExtravagantState(){}

    public ExtravagantState(StateContext contextI){
        context = contextI;
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
            if(money >= 0 && money < 10000){
                context.setCurrentState(context.getBasicState());
            }else if(money >= 10000 && money < 50000){
                context.setCurrentState(context.getModeratelyExpensiveState());
            }
        }else if(inputLine[0].equalsIgnoreCase(ITEM)){
            if(store.isPresent(inputLine[1],Luxury.EXTRAVAGANT)){
                result = Luxury.EXTRAVAGANT + "::" + inputLine[1] + "--" + "YES";
            }else{
                result = Luxury.EXTRAVAGANT + "::" + inputLine[1] + "--" + "NO";
            }
        }
        return result;
    }
}
