package wordPlay.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1> Word Util class </h1>
 *
 * This class has all the string manipulation methods like reverse words, calculate the metrics of the sentence.
 *
 * @author Neha Gupta
 * @version 1.0.0
 */

public class WordUtil {

    private StringBuilder reverseString = new StringBuilder();
    private String longestWord = "";
    private String frequentWord = "";
    private float sentenceCount, wordCount, charCount = 0;
    private String averageWords, averageChars = "";
    private static DecimalFormat df = new DecimalFormat("0.00");
    private HashMap<String, Integer> map = new HashMap();

    enum Result{
        LONGEST_WORD, AVG_NUMBER_WORDS_PER_SENTENCE, AVG_NUM_CHARS_PER_SENTENCE, MAX_FREQ_WORD
    }

    /**
     * This is the method which will reverse the word of the sentence.
     * @param word as a string
     * @return Nothing
     * @exception IOException On input error.
     * @see IOException
     */

    String reverse(String word) {
//        return word;
        char temp;
        char[] chars = word.toCharArray();
        for (int i=0, len=word.length()-1; i<len; i++, len--) {
            temp = chars[i];
            chars[i] = chars[len];
            chars[len] = temp;
        }
        return new String(chars);
    }

    public void reverseWord(String line) {
//        char temp;
//        int c = 0;
//        char[] arr = line.toCharArray();
//        for (int i = 0; i < line.length(); i++) {
//            if (i == line.length() - 1 ||  String.valueOf(arr[i]).matches("[^a-zA-Z0-9]")) {
//                int k = c;
//                for (int j = i - 1; j >= k; j--) {
//                    temp = arr[j];
//                    arr[j] = arr[k];
//                    arr[k] = temp;
//                    k++;
//                }
//                c = i + 1;
//            }
//        }
//        reverseString.append(String.copyValueOf(arr)).append("\n");
        //System.out.println(reverseString.toString());

        reverseString.append(
                Stream.of(line.split("\\.")).map(sentence -> Stream.of(sentence.split("\\s")).map(word -> reverse(word)).collect(Collectors.joining(" "))).collect(Collectors.joining("."))
        ).append("\n");
    }

    public void calculateMetrics(String line){
        count(line);
        frequentWord(map);
        storeResult();
    }

    /**
     * This is the method which will write the calculated metrics into the file.
     * @param
     * @return average of two numbers which have been rounded-off to two decimal places.
     *
     */
    String average(float a, float b){
        return df.format(a/b);
    }

    /**
     * This is the method which will calculate the most frequent word used.
     *
     */

    void frequentWord(HashMap<String, Integer> fr){
        Set<Map.Entry<String, Integer>> set = fr.entrySet();
        int val = 0;

        for (Map.Entry<String, Integer> entry : set)
        {
            if (entry.getValue() > val)
            {
                val = entry.getValue();
                frequentWord = entry.getKey();
            }
        }
        /*System.out.println("Longest word : " + longestWord);
        System.out.println("Most frequency word : " + frequentWord);*/
    }

    /**
     * This is implements the count of characters,words and sentences.
     *
     */

    void count(String line){
        String[] paragraph=line.trim().split("[.?!]",0);
        for(String sentence : paragraph)
        {
            sentenceCount++;
            String[] words = sentence.trim().split("\\r?\\n| ",0);

            for (String word : words)
            {
                charCount += word.length();
                if (map.containsKey(word))
                    map.put(word, map.get(word) + 1);
                else
                    map.put(word, 1);
                if(!word.equals(""))
                    wordCount++;

                if(word.length() > longestWord.length())
                    longestWord=word;

            }
            charCount++;
            charCount += words.length - 1 ;


        }
        /*System.out.println("wordCount: "+wordCount);
        System.out.println("charCount: "+charCount);
        System.out.println("sentenceCount: "+sentenceCount);*/
        averageWords = average(wordCount,sentenceCount);
        averageChars = average(charCount,sentenceCount);
        /*System.out.println("Avg no of words per sentenceCount : " + averageWords);
        System.out.println("Avg no of chars per sentenceCount : " + averageChars);*/
    }

    public void storeResult() {
        Results.output.put(Result.AVG_NUM_CHARS_PER_SENTENCE,averageChars);
        Results.output.put(Result.AVG_NUMBER_WORDS_PER_SENTENCE,averageWords);
        Results.output.put(Result.LONGEST_WORD,longestWord);
        Results.output.put(Result.MAX_FREQ_WORD,frequentWord);
        Results.REVERSE_STRING = reverseString.toString();
    }

}
