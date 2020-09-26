package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Util to read and segregate the line read by files, process them according to states */

public class BackEnd {

    Map<Luxury, List<String>> map;

    public BackEnd(){
        map = new HashMap<>();
    }

    public void storeItemsFile(String item){
        String[] arg = extractValues(item);
        try{
            if(arg[0].equals(Luxury.BASIC.getValue())){
                if(!map.containsKey(Luxury.BASIC)){
                    map.put(Luxury.BASIC, new ArrayList<>());
                }
                map.get(Luxury.BASIC).add(arg[1]);
            }
            if(arg[0].equals(Luxury.LUXURIOUS.getValue()) || arg[0].equals(Luxury.BASIC.getValue())){
                if(!map.containsKey(Luxury.LUXURIOUS)){
                    map.put(Luxury.LUXURIOUS, new ArrayList<>());
                }
                map.get(Luxury.LUXURIOUS).add(arg[1]);
            }
            if(arg[0].equals(Luxury.BASIC.getValue()) || arg[0].equals(Luxury.LUXURIOUS.getValue()) || arg[0].equals(Luxury.EXTRAVAGANT.getValue())){
                if(!map.containsKey(Luxury.EXTRAVAGANT)){
                    map.put(Luxury.EXTRAVAGANT, new ArrayList<>());
                }
                map.get(Luxury.EXTRAVAGANT).add(arg[1]);
            }
        }catch(Exception e){
            System.err.println("Exception: ");
            System.out.println("Exception: "+ e.getMessage().getClass().getName());
            e.printStackTrace();
        }

    }

    public String[] extractValues(String item) {
        String[] arg = item.split(":");
        for(String s : arg){
            s.trim();
        }
        return arg;
    }

    public boolean isPresent(String item, Luxury state){
        for(Map.Entry<Luxury, List<String>> entry : map.entrySet()){
            if(entry.getKey().equals(state)){
                if(entry.getValue().contains(item))
                    return true;
            }
        }
        return false;
    }

}
