package replicaSystem.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Results implements FileDisplayInterface{

    static List<String> errorList;
    List<String> resultArray;
    String filename;
    FileWriter fileWrite;

    public Results(String fileNameIn){
        resultArray = new ArrayList<>();
        this.filename = fileNameIn;
    }

    public Results(){
        errorList = new ArrayList<>();
    }

    @Override
    public void writeToResultFile() throws IOException {
        try{
            fileWrite = new FileWriter(filename,false);
            for(String str : resultArray)
                fileWrite.write(str + System.lineSeparator());
            fileWrite.close();
        }catch(IOException | NullPointerException e){
            System.err.println("Exception: ");
            System.out.println("Exception: "+ e.getMessage().getClass().getName());
            e.printStackTrace();
        }finally {
        }

    }

    @Override
    public void writeToErrorFile(String s) throws IOException {
        try{
            fileWrite = new FileWriter(s,false);
            for(String str : errorList)
                fileWrite.write(str + System.lineSeparator());
            fileWrite.close();
        }catch(IOException | NullPointerException e){
            System.err.println("Exception: ");
            System.out.println("Exception: "+ e.getMessage().getClass().getName());
            e.printStackTrace();
        }finally {
        }
    }
}
