package decoratorPattern.decorator;

import decoratorPattern.util.InputDetails;

import java.util.HashMap;
import java.util.Map;


public class MostFrequentDecorator extends AbstractTextDecorator {

    private AbstractTextDecorator atd;
    private InputDetails id;

    public MostFrequentDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
        atd = atdIn;
        id = idIn;
        start = "MOST_FREQUENT_";
        end = "_MOST_FREQUENT";
    }

    /**
     * This function again processes the input.
     */

    @Override
    public void processInputDetails() {
        String input = id.getInput();
        String output = processMostFrequent(input);
        id.updateInput(output);
        if (null != atd) {
            atd.processInputDetails();
        }
    }

    /**
     *
     * @param line
     * @return
     * This function is used to process the most frequent word taking line as a parameter.
     */

    private String processMostFrequent(String line){
        String frequentWord = getFrequentWord(getMap(line));
        String appendedWord = appendToWord(getFrequentWord(getMap(line)));
        Map<String, String> map = new HashMap<String, String>(){{
            put(frequentWord,appendedWord);
        }
        };
        String[] args = getWordsFromLine(line);
        for(String s : args){
            if(s.equalsIgnoreCase(frequentWord)){
                map.put(s,appendToWord(s));
            }
        }
        return appendToLine(map, line);
    }


    /**
     *
     * @param map
     * @return
     * This function gets the most frequent word taking map as a parameter.
     */

    private String getFrequentWord(Map<String, Integer> map) {
        Map.Entry<String,Integer> max = null;
        for (Map.Entry<String,Integer> entry : map.entrySet())
        {
            if (max == null || entry.getValue().compareTo(max.getValue()) > 0)
            {
                max = entry;
            }
        }
        //System.out.println(max);
        return max.getKey();
    }

    private Map<String,Integer> getMap(String line){
        Map<String,Integer> map = new HashMap<>();
        for(String s : line.split("[\\s,]+")){
            String toCheck = s.toLowerCase().trim();
            if(!map.containsKey(toCheck)){
                map.put(toCheck,1);
            }else{
                map.put(toCheck, map.get(toCheck) + 1);
            }
        }
        //System.out.println(map);
        return map;
    }
}
