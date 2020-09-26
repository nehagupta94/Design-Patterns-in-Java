package replicaSystem.util;

import java.io.IOException;
import java.util.*;

public class ReplicaSystem {

    TreeHelper treeHelper;
    Results results;

    public ReplicaSystem(Results results){
        treeHelper = new TreeHelper(results);
        this.results = results;
    }

    /**
     *
     * @param fileProcessor
     * @exception  Input file is empty
     The method is used to read the input file.
     */


    public void readInputFile(FileProcessor fileProcessor){
        try{
            String line = fileProcessor.poll();
            if(line == null)
                throw new RuntimeException("Input file is empty");
            while(line != null){
                //System.out.println(line);
                parseInput(line);
                line = fileProcessor.poll();
            }
            //treeHelper.printData(result);
            fileProcessor.close();
        }catch (IOException e){
            System.err.println("Error in polling the input file!");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param fileProcessor
     * @exception Modify file is empty
     * The methods reads the modify file.
     */

    public void readModifyFile(FileProcessor fileProcessor){
        try{
            String line = fileProcessor.poll();
            if(line == null)
                throw new RuntimeException("Modify file is empty");
            while (line != null){
                //System.out.println(line);
                parseModifyFile(line);
                line = fileProcessor.poll();
            }
            //treeHelper.printData(result);
            fileProcessor.close();
        }catch (IOException e){
            System.err.println("Error in polling the modify file!");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param line
     * @exception  Parse Error
     * this method is used to parse modify file.
     */


    private void parseModifyFile(String line){
        String[] lineArray = line.split(",");
        int replicaId = 0,bNumber = 0;
        Map<String,String> modify = new HashMap<>();
        try{
            if(validateReplicaId(Integer.parseInt(lineArray[0]))){
                replicaId = Integer.parseInt(lineArray[0]);
            }
            if(validateBNumber(lineArray[1])){
                bNumber = Integer.parseInt(lineArray[1]);
            }
            try{
                String[] array = lineArray[2].split(":");
                if(array[0] != null && array[1] != null){
                    modify.put(array[0],array[1]);
                }
            }catch (ArrayIndexOutOfBoundsException e){
                results.errorList.add("Parse Error!! Old/New Value absent for bNumber: "+bNumber);
            }
            treeHelper.modifyData(replicaId,bNumber,modify);
        }catch (NumberFormatException e){
            System.err.println("Replica ID not correct!");
        }
    }

    /**
     *
     * @param line
     * The method is used to parse input file.
     */


    private void parseInput(String line){
        String[] lineArray = line.split(":");
        int bNumber = 0;
        if(validateBNumber(lineArray[0])){
            bNumber = Integer.parseInt(lineArray[0]);
        }
        String[] array = lineArray[1].split(",");
        String firstName = array[0];
        String lastName = array[1];
        Double gpa = Double.parseDouble(array[2]);
        String major = array[3];
        List<String> skills = new ArrayList<>();
        for(int i = 4; i < array.length ; i++){
            skills.add(array[i]);
        }
        /*System.out.println("bNumber " + bNumber);
        System.out.println("firstName "+ firstName);
        System.out.println("lastName " + lastName);
        System.out.println("gpa " + gpa);
        System.out.println("major "+ major);
        System.out.println("skills "+ skills);*/
        try{
            treeHelper.insertData(bNumber,firstName,lastName,gpa,major,skills);
        }catch (RuntimeException e){
            System.err.println("Error caught while sending input to Tree!");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param bNumber
     * @return
     * The method checks Bnumber.
     */

    private boolean validateBNumber(String bNumber){
        if(bNumber.length() < 4 || Integer.parseInt(bNumber) < 0 ){
            return false;
        }else{
            return true;
        }
    }

    /**
     *
     * @param id
     * @return
     * The method checks ReplicaId
     */

    private boolean validateReplicaId(int id){
        if(id > 2 || id < 0){
            return false;
        }else{
            return true;
        }
    }

    /**
     *
     * @param result
     * @throws IOException
     * The method printsData
     */

    public void printData(Results result) throws IOException {
        treeHelper.printData(result);
    }
}
