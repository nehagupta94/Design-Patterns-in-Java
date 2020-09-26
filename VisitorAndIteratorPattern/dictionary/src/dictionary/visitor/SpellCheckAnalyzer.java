package visitor;

import results.Results;
import results.SpellCheckResults;
import elements.Element;
import util.FileProcessor;
import elements.MyElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/** SpellCheckAnalyzer implements the Visitor interface
 * Checks input file word with all the words from the acceptableFile
 * and returns the list of words similar.
 * Performs spell check.
 */
public class SpellCheckAnalyzer implements Visitor {

    private String filename;
    Results spellCheckResults;
    private FileProcessor fp;
    private List<String> acceptableWords;
    Map<String, List<String>> result;


    public SpellCheckAnalyzer(String arg, Results spellCheckResultsIn) {
        filename = arg;
        spellCheckResults = spellCheckResultsIn;
        acceptableWords = new ArrayList<>();
        result = new HashMap<>();
        readAcceptableFile(filename);
    }

    public void readAcceptableFile(String filename){
        try{
            fp = new FileProcessor(filename);
            String word = fp.poll();
            while(word != null){
                acceptableWords.add(word);
                word = fp.poll();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] getWordsFromElement(Element element){
        String[] temp = ((MyElement)element).getSentence().split("\\s+");
        return temp;
    }

    @Override
    public void visit(Element element) {
        //System.out.println("Reached spell check visitor");
        performSpellCheck(element);
        appendResult(result);
    }

    private boolean isSimilar(String firstWord, String toCompare) {
        if(firstWord.length() != toCompare.length()){
            return false;
        }
        if (firstWord.equalsIgnoreCase(toCompare)) {
            return false;
        }
        int matchingAlphabets = 0;
        for (int i = 0; i < firstWord.length(); i++) {
            if (firstWord.charAt(i) == toCompare.charAt(i)) {
                matchingAlphabets++;
            }
        }
        if (matchingAlphabets == firstWord.length() - 1) {
            return true;
        }
        return false;
    }

    private boolean checkValidWord(String wordA){
        if(wordA.length() > 2)
            return true;
        else
            return false;
    }

    private void addValueInMap(String word, String acceptableWord){
        List<String> l = result.get(word);
        if(l == null){
            l = new ArrayList<>();
            l.add(acceptableWord);
            result.put(word,l);
        }else{
            if(!l.contains(acceptableWord))
                l.add(acceptableWord);
        }
    }


    private void performSpellCheck(Element element){
        String[] words = getWordsFromElement(element);
        for(String word : words){
            if(checkValidWord(word)){
                for(String acceptableWord : acceptableWords){
                    if(isSimilar(word,acceptableWord)){
                        addValueInMap(word,acceptableWord);
                    }
                }
            }
        }
    }


    public void appendResult(Map<String,List<String>> result){
        ((SpellCheckResults)spellCheckResults).getResult().putAll(result);
    }


}
