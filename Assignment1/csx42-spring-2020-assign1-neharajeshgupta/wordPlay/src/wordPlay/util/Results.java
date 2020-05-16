package wordPlay.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1> Results class </h1>
 *
 * This class implements two interfaces namely FileDisplayInterface and StdoutDisplayInterface.
 *
 * It overrides and implements those methods.
 *
 * @author Neha Gupta
 * @version 1.0.0
 */

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    public static HashMap<Enum, String> output = new HashMap<>();
    public static String REVERSE_STRING;
    FileProcessor file = new FileProcessor();
    FileWriter fileWrite;

    public Results() throws IOException {
        /*writeToConsole();
        writeToOutputFile();
        writeToMetricsFile();*/
    }

    /**
     * This is the method which will write the calculated metrics into the file.
     * @return Nothing
     * @exception IOException On input error.
     * @see IOException
     */

    @Override
    public void writeToMetricsFile() throws IOException{
        try{
            String filename = file.getMetricFilename();
            //System.out.println(filename);
            fileWrite=new FileWriter(filename,false);
            for(Map.Entry<Enum,String> entry : output.entrySet()){
                //System.out.println(entry.getKey().toString() + "\t" + entry.getValue().toString() + "\n");
                fileWrite.write(entry.getKey()+ "\t" + entry.getValue() + "\n");
            }
            fileWrite.close();
        }catch(IOException | NullPointerException e){
            e.printStackTrace();
        }finally {
            fileWrite.close();
        }
    }

    /**
     * This is the method which will write the output into the file.
     * @return Nothing
     * @exception IOException On input error.
     * @see IOException
     */

    @Override
    public void writeToOutputFile() throws IOException {
        try{
            String filename = file.getOutputFilename();
            //System.out.println(filename);
            fileWrite=new FileWriter(filename,false);
            fileWrite.write(REVERSE_STRING);
            fileWrite.close();
        }catch(IOException | NullPointerException e){
            e.printStackTrace();
        }finally {
            fileWrite.close();
        }
    }

    /**
     * This is the method which will write the output into the console.
     * @return Nothing
     */

    @Override
    public void writeToConsole() {
        for(Map.Entry<Enum,String> entry : output.entrySet()){
            System.out.println(entry.getKey().name() + "\t" + entry.getValue() + "\n");
            //fileWrite.write(entry.getKey().toString() + "\t" + entry.getValue().toString() + "\n");
        }
        System.out.println(REVERSE_STRING);
    }
}
