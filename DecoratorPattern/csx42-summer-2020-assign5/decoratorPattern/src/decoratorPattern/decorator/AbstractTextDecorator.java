package decoratorPattern.decorator;

import java.util.Map;

public abstract class AbstractTextDecorator {

    String start = null;
    String end = null;

    public abstract void processInputDetails();

    /**
     *
     * @param word
     * @return
     * The appendToWord function is used to append to words taking Word as a parameter
     */

    public final String appendToWord(String word) {
        StringBuilder str = new StringBuilder();
        str.append(start);
        str.append(word);
        str.append(end);
        //System.out.println(str.toString());
        return str.toString();
    }

    /**
     *
     * @param map
     * @param line
     * @return
     * The appendToline function is used to to append to line taking map and line as parameters.
     */

    public final String appendToLine(Map<String,String> map, String line) {
        StringBuilder str = new StringBuilder();
        String[] args = getWordsFromLine(line);
        //System.out.println(Arrays.toString(args));
        for(String s : args){
            if(map.containsKey(s)){
                str.append(map.get(s));
                str.append(" ");
            }else{
                str.append(s);
                str.append(" ");
            }
        }
        //System.out.println(str.toString());
        return str.toString();
    }

    /**
     *
     * @param line
     * @return
     * This function is used to retrieve words from line taking line as a parameter.
     */

    public final String[] getWordsFromLine(String line){
        return line.split("[ ,]+");
    }

}
