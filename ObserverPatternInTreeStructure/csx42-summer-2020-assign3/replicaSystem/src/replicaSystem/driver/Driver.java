package replicaSystem.driver;

import replicaSystem.util.FileProcessor;
import replicaSystem.util.ReplicaSystem;
import replicaSystem.util.Results;

import java.io.File;

/**
 *
 * This is the driver class.
 *
 * @author 
 */
public class Driver {
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 6;

    /**
     *
     * @param args
     * @throws Exception Incorrect number of arguments
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
                    (args[0].equals("${input}")) ||
                    (args[1].equals("${modify}")) ||
                    (args[2].equals("${out1}")) ||
                    (args[3].equals("${out2}")) ||
                    (args[4].equals("${out3}")) ||
                    (args[5].equals("${error}"))) {
                System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
                System.exit(0);
            }
        }catch (Exception e){
            System.err.println("Exception caught in Driver class.");
        }finally {
        }

        System.out.println("Hello World! Lets get started with the assignment");

        FileProcessor inputFile = new FileProcessor(args[0]);
        Results error = new Results();
        ReplicaSystem replicaSystem = new ReplicaSystem(error);
        replicaSystem.readInputFile(inputFile);

        //System.out.println("Tree set up done!");

        FileProcessor modifyFile = new FileProcessor(args[1]);
        replicaSystem.readModifyFile(modifyFile);


        Results replica0 = new Results(args[2]);
        Results replica1 = new Results(args[3]);
        Results replica2 = new Results(args[4]);


        replicaSystem.printData(replica0);
        replicaSystem.printData(replica1);
        replicaSystem.printData(replica2);

        error.writeToErrorFile(args[5]);

        System.out.println("Completed!! ");


    }
}
