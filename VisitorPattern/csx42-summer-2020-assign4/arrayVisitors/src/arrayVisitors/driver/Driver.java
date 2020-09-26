package arrayVisitors.driver;

import arrayVisitors.adt.MyArray;
import arrayVisitors.adt.MyArrayI;
import arrayVisitors.adt.MyArrayList;
import arrayVisitors.adt.MyArrayListI;
import arrayVisitors.util.FileProcessor;
import arrayVisitors.util.Results;
import arrayVisitors.visitors.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

/**
 *
 * This is the driver class.
 *
 * @author
 */
public class Driver {

    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

    /**
     *
     * @param args
     * @throws Exception Incorrect number of arguments
     * @throws Exception Empty Input File
     *
     */
    public static void main(String[] args) throws Exception {

        /*
         * As the build.xml specifies the arguments as input,output or metrics, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used
         */
        try{
            if ((args.length != REQUIRED_NUMBER_OF_CMDLINE_ARGS) ||
                    (args[0].equals("${input1}")) ||
                    (args[1].equals("${input2}")) ||
                    (args[2].equals("${commonintsout}")) ||
                    (args[3].equals("${missingintsout1}") ||
                    (args[4].equals("${missingintsout2}")))) {
                System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
                System.exit(0);
            }
        }catch (Exception e){
            System.err.println("Exception caught in Driver class.");
        }finally {
        }

        try{
            if(args[0].equalsIgnoreCase(args[1])){
                throw new RuntimeException("Input files name match!!");
            }
            if(args[2].equalsIgnoreCase(args[3]) || args[2].equalsIgnoreCase(args[4])
            || args[3].equalsIgnoreCase(args[4])){
                throw new RuntimeException(("Output files name match!!"));
            }
            if(!(args[2].endsWith(".txt") && args[3].endsWith(".txt") && args[4].endsWith(".txt"))){
                throw new RuntimeException("Output file name does not end with .txt !");
            }
        }catch (Exception e){
            System.err.println("Exception caught in validation of inputs!!");
            System.exit(0);
        }
        System.out.println("Hello World! Lets get started with the assignment");

        FileProcessor inputFile1 = null,inputFile2 = null;
        try{
            inputFile1 = new FileProcessor(args[0]);
            inputFile2 = new FileProcessor(args[1]);
        }catch (InvalidPathException | SecurityException | IOException e) {
            System.err.println("File Processor exception caught in Driver!!");
        }

        Visitor populateMyArrayVisitor1 = new PopulateMyArrayVisitor(inputFile1);
        Visitor populateMyArrayVisitor2 = new PopulateMyArrayVisitor(inputFile2);

        Results missingIntsVisitorResults = new Results(args[3],args[4]);
        Visitor missingIntsVisitor = new MissingIntsVisitor(missingIntsVisitorResults);

        Results commonIntsVisitorResults = new Results(args[2]);
        Visitor commonIntsVisitor = new CommonIntsVisitor(commonIntsVisitorResults);

        MyArrayI myArrayIElementOne = new MyArray();
        myArrayIElementOne.accept(populateMyArrayVisitor1);
        //myArrayIElementOne.printList();
        MyArrayI inputArrayOne = myArrayIElementOne.getList();
        //inputArrayOne.printList();
        MyArrayI myArrayIElementTwo = new MyArray();
        myArrayIElementTwo.accept(populateMyArrayVisitor2);
        MyArrayI inputArrayTwo = myArrayIElementTwo.getList();
        //myArrayIElementTwo.printList();


        MyArrayListI myArrayListIElement = new MyArrayList();
        myArrayListIElement.add(inputArrayOne);
        myArrayListIElement.add(inputArrayTwo);

        //myArrayListIElement.printList();
        myArrayListIElement.accept(missingIntsVisitor);
        myArrayListIElement.accept(commonIntsVisitor);

        missingIntsVisitorResults.writeToResultFile();
        commonIntsVisitorResults.writeToResultFile();

        System.out.println("Completed!! ");


    }
}
