package decoratorPattern.decorator;

import decoratorPattern.util.InputDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeywordDecorator extends AbstractTextDecorator {

    private AbstractTextDecorator atd;
    private InputDetails id;

    public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
        atd = atdIn;
        id = idIn;
        start = "KEYWORD_";
        end = "_KEYWORD";
    }

    /**
     * This function processes all the input.
     */

    @Override
    public void processInputDetails() {
        String line = id.retrieveInput();
        String[] args = getWordsFromLine(line);
        List<String> keyWordList = id.getKeyWords();
        String output = processKeywordDecorator(args,keyWordList,line);
        id.updateInput(output);
        if (null != atd) {
            atd.processInputDetails();
        }
    }

    /**
     *
     * @param args
     * @param keyWordList
     * @param line
     * This function is used to implement part of decorator pattern taking args, line and keyword list as parameters.
     * @return
     */

    private String processKeywordDecorator(String[] args, List<String> keyWordList, String line) {
        Map<String,String> map = new HashMap<>();
        String output = null;
        for(String s : keyWordList){
            for(String arg : args){
                if(s.equalsIgnoreCase(arg)){
                    map.put(arg,appendToWord(arg));
                }
            }
        }
        if(!map.isEmpty()){
            output = appendToLine(map,line);
        }else {
            output = line;
        }
        return output;
    }
}
