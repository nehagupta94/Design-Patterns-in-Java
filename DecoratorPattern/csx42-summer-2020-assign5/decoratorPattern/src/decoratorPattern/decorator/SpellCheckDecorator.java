package decoratorPattern.decorator;

import decoratorPattern.util.InputDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellCheckDecorator extends AbstractTextDecorator {

    private AbstractTextDecorator atd;
    private InputDetails id;


    public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
        atd = atdIn;
        id = idIn;
        start = "SPELLCHECK_";
        end = "_SPELLCHECK";
    }

    @Override
    public void processInputDetails() {
        //System.out.println("REACHED HERE! " + id.getSentence());
        String line = id.retrieveInput();
        String[] args = getWordsFromLine(line);
        List<String> misspelledWordsList = id.getMisspelledWords();
        String output = processSpellCheckDecorator(args,misspelledWordsList,line);
        id.updateInput(output);
        if (null != atd) {
            atd.processInputDetails();
        }
    }

    /**
     *
     * @param args
     * @param misspelledWordsList
     * @param line
     * This function is used to carry out SpellCheck taking args, misspelledWordsList and line as parameters.
     * @return
     */

    private String processSpellCheckDecorator(String[] args, List<String> misspelledWordsList, String line) {
        Map<String,String> map = new HashMap<>();
        String output = null;
        char dot = '.';
        for(String s : misspelledWordsList){
            for(String arg : args){
                if(arg.charAt(arg.length() - 1) == dot){
                    String change = arg.replace(".","");
                    if(s.equalsIgnoreCase(change)){
                        String word = appendToWord(change) + ".";
                        map.put(arg,word);
                    }
                }
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
