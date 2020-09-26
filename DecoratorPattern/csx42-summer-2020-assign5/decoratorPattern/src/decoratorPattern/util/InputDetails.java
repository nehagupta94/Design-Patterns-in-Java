package decoratorPattern.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputDetails implements FileDisplayInterface{

    private String inputFile;
    private String outputFile;
    private String misspelledFile;
    private String keywordFile;
    StringBuilder stringBuilder;
    List<String> rawData;
    List<String> keyWords;
    List<String> misspelledWords;
    List<String> sentences;
    FileWriter fileWrite;


    public InputDetails(String inputFile, String misspelledFile, String keywordFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.misspelledFile = misspelledFile;
        this.keywordFile = keywordFile;
        stringBuilder = new StringBuilder();
        rawData = new ArrayList<>();
        keyWords = new ArrayList<>();
        misspelledWords = new ArrayList<>();
        sentences = new ArrayList<>();
        readInputFile();
    }
    public List<String> getKeyWords() {
        return storeKeywordsList();
    }

    public List<String> getMisspelledWords() {
        return storeMisspelledWordsList();
    }

    /**
     * This function is used to store the keywords
     * @throws Exception Keywords file is empty
     * @return
     */

    private List<String> storeKeywordsList(){
        try{
            FileProcessor fp = new FileProcessor(keywordFile);
            String line = fp.poll();
            if(line == null){
                throw new RuntimeException("Keywords file found empty!!!");
            }
            while (line != null){
                keyWords.add(line);
                line = fp.poll();
            }
            fp.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return keyWords;
    }


    /**
     * This function stores the misspelledWords
     * @throws Exception Misspelled file is empty
     * @return
     */

    private List<String> storeMisspelledWordsList(){
        try{
            FileProcessor fp = new FileProcessor(misspelledFile);
            String line = fp.poll();
            if(line == null){
                throw new RuntimeException("Misspelled file found empty!!!");
            }
            while (line != null){
                misspelledWords.add(line);
                line = fp.poll();
            }
            fp.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return misspelledWords;
    }

    /**
     * This function read the input file.
     * @throws Exception Input file is empty.
     * @throws Exception Input file does not have required characters.
     */

    public void readInputFile(){
        try {
            FileProcessor fp = new FileProcessor(inputFile);
            String line = fp.poll();
            if(line == null){
                throw new RuntimeException("Input file found empty!!!");
            }
            while(line != null){
                if(line.matches("[A-Za-z0-9., ]+")){
                    stringBuilder.append(line);
                    line = fp.poll();
                }else {
                    throw new RuntimeException("Input file does not have required characters!");
                }
            }
            fp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function gets the Input.
     * @return
     */

    public String getInput(){
        return stringBuilder.toString();
    }

    /**
     * This function retrieves the input.
     * @return
     */

    public String retrieveInput(){
        return stringBuilder.toString();
    }

    /**
     * This function updates the input taking line as a parameter.
     * @param line
     */

    public void updateInput(String line){
        if(!line.isEmpty()){
            stringBuilder.setLength(0);
            stringBuilder.append(line);
        }
    }

    /**
     * This function writes to result file.
     * @throws IOException
     */

    @Override
    public void writeToResultFile() throws IOException {
        fileWrite = new FileWriter(outputFile,false);
        fileWrite.write(stringBuilder.toString());
        fileWrite.close();
    }
}
