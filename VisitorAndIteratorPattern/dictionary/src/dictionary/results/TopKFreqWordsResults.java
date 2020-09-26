package results;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopKFreqWordsResults implements Results {

    private String filename;
    private List<List<String>> result;
    FileWriter fileWrite;

    private TopKFreqWordsResults(){}

    public TopKFreqWordsResults(String arg) {
        filename = arg;
        result = new ArrayList<>();
    }

    public void addResult(List<String> add){
        result.add(add);
        /*for(List<String> l : result){
            System.out.println(Arrays.toString(l.toArray()));
        }*/
    }

    @Override
    public void writeToFile() {
        try{
            fileWrite = new FileWriter(filename, false);
            for(List<String> l : result){
                fileWrite.write(Arrays.toString(l.toArray()));
                fileWrite.write(System.lineSeparator());
            }
            fileWrite.close();
        }catch(NullPointerException | IOException e){
            System.err.println("Exception: ");
            System.out.println("Exception: "+ e.getMessage().getClass().getName());
            e.printStackTrace();
        }finally {

        }
    }

}
