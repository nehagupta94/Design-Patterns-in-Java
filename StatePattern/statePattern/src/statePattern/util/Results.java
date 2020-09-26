package util;

import headers.FileDisplayInterface;
import headers.StdoutDisplayInterface;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Results class for writing the results to console, file etc */

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    private List<String> results;
    FileWriter fileWrite;

    public Results(){
        results = new ArrayList<>();
    }

    public void addResults(String str){
        if(str != null){
            results.add(str);
        }
    }

    @Override
    public void writeToFile(String filename) {
        try{
            fileWrite=new FileWriter(filename,false);
            for(String s : results)
                fileWrite.write(s + System.lineSeparator());
            fileWrite.close();
        }catch(IOException | NullPointerException e){
            System.err.println("Exception: ");
            System.out.println("Exception: "+ e.getMessage().getClass().getName());
            e.printStackTrace();
        }finally {
        }
    }

    @Override
    public void writeToConsole() {
        for(String s : results){
            System.out.println(s);
        }
    }
}
