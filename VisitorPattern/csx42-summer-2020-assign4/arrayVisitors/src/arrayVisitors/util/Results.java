package arrayVisitors.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Results implements FileDisplayInterface{

    List<List<Integer>> resultArray;
    String filename1;
    String filename2;
    FileWriter fileWrite1;
    FileWriter fileWrite2;
    boolean flag;

    public Results(String fileNameIn){
        resultArray = new ArrayList<>();
        this.filename1 = fileNameIn;
        this.filename2 = null;
    }

    public Results(String fileNameIn,String filenameIn){
        flag = true;
        resultArray = new ArrayList<>();
        this.filename1 = fileNameIn;
        this.filename2 = filenameIn;
    }

    private Results(){
    }

    /**
     *
     * @param add
     * The method is used to add the result and print it.
     */


    public void addResult(List<Integer> add){
        resultArray.add(add);
        System.out.println("Result added!!");
    }

    /**
     *
     * @param add
     * The method adds the list and prints it.
     */


    public void addInResult(List<List<Integer>> add){
        for(List<Integer> list : add){
            resultArray.add(list);
        }
        System.out.println("Result added!!");
    }

    /**
     *
     * @throws IOException
     * The method writes the results to the output files.
     */

    @Override
    public void writeToResultFile() throws IOException {
        try{
            if(flag){
                fileWrite1 = new FileWriter(filename1,false);
                fileWrite2 = new FileWriter(filename2, false);
                List<Integer> list1 = resultArray.get(0);
                List<Integer> list2 = resultArray.get(1);
                for(Integer i : list1){
                    fileWrite1.write(String.format("%02d", i)+ System.lineSeparator());
                }
                //fileWrite.write(str + System.lineSeparator());
                fileWrite1.close();

                for(Integer i : list2){
                    fileWrite2.write(String.format("%02d", i) + System.lineSeparator());
                }
                //fileWrite.write(str + System.lineSeparator());
                fileWrite2.close();
            }else{
                fileWrite1 = new FileWriter(filename1,false);
                for(List<Integer> list : resultArray){
                    for(Integer i : list){
                        fileWrite1.write(String.format("%02d", i) + System.lineSeparator());
                    }
                }
                //fileWrite.write(str + System.lineSeparator());
                fileWrite1.close();
            }
        }catch(IOException | NullPointerException e){
            System.err.println("Exception: ");
            System.out.println("Exception: "+ e.getMessage().getClass().getName());
            e.printStackTrace();
        }finally {
        }

    }
}
