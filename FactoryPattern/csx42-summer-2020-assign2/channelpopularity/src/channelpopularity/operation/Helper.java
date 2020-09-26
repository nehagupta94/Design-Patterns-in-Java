package channelpopularity.operation;

import channelpopularity.util.FileProcessor;

import java.io.IOException;
import java.util.*;

public class Helper {
    FileProcessor fileProcessor;
    List<String> input;
    final String QUOTES = "::";
    final String UNDERSCORE = "__";
    final String OPEN_BRAC = "[";
    final String CLOSE_BRAC = "]";
    final String COMMA = ",";
    final String EQUAL = "=";

    public Helper() {
        input = new ArrayList<>();
    }

    /**
     *
     * @parmas Map<String,Object>
     *     This methods here parses through each line in the input file and splits the lines based on Quotes, Underscore,Open & Close Brac, Comma and Equal and then stores the result in the form of a hashmap.
     * @exception  SYNTAX IS NOT VALID FOR METRICS.
     * @return innermap
     */

    public Map<String,Object> getInputArray(String line){
        Map<String, Object> innerMap = new HashMap<>();
        String[] split1 = line.split(QUOTES);
        if(line.startsWith(Operation.ADD_VIDEO.name()))
        {
            innerMap.put("operation", Operation.ADD_VIDEO);
            innerMap.put("vidName",split1[1]);
        }
        if(line.startsWith(Operation.AD_REQUEST.name())) {
            innerMap.put("operation", Operation.AD_REQUEST);
            String[] split2 = split1[1].split(EQUAL);
            innerMap.put("vidName", split1[0].split(UNDERSCORE)[1]);
            innerMap.put(split2[0].toLowerCase(), Integer.parseInt(split2[1]));
        }
       if(line.startsWith(Operation.REMOVE_VIDEO.name())){
           innerMap.put("operation",Operation.REMOVE_VIDEO);
           innerMap.put("vidName",split1[1]) ;
       }
       if(line.startsWith(Operation.METRICS.name())) {
           innerMap.put("operation",Operation.METRICS);
           innerMap.put("vidName",split1[0].split(UNDERSCORE)[1]);
           if(!split1[1].startsWith(OPEN_BRAC)||!split1[1].endsWith(CLOSE_BRAC))
               throw new RuntimeException("SYNTAX IS NOT VALID FOR METRICS!");
           split1[1] = split1[1].substring(1,split1[1].length()-1);
           for(String param : split1[1].split(COMMA)){
               String[] splitPs = param.split(EQUAL);
               innerMap.put(splitPs[0].toLowerCase(),Integer.parseInt(splitPs[1]));
           }

    }
return innerMap;
    }
}
