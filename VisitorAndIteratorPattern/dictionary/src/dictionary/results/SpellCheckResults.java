package results;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellCheckResults implements Results {

    private String filename;
    FileWriter fileWrite;
    Map<String, List<String>> result;

    public SpellCheckResults(String arg) {
        filename = arg;
        result = new HashMap<>();
    }

    public Map<String, List<String>> getResult(){
        return result;
    }

    @Override
    public void writeToFile() {
        try{
            fileWrite = new FileWriter(filename, false);
            for (Map.Entry<String, List<String>> entry : result.entrySet()) {
                fileWrite.write(entry.getKey()+"::[");
                int comma = entry.getValue().size();
                for(String s : entry.getValue()){
                    fileWrite.write(s);
                    if(comma > 1){
                        fileWrite.write(",");
                    }
                    comma --;
                }
                fileWrite.write("]");
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
