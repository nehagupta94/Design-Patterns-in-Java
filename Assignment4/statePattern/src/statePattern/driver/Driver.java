package driver;

import headers.FileDisplayInterface;
import headers.StdoutDisplayInterface;
import implementations.StateContext;
import util.FileProcessor;
import validator.ValidatorUtil;

/**
 * @author Neha Gupta
 */
public class Driver {
    public static void main(String[] args) throws Exception {

        /*
         * As the build.xml specifies the arguments as argX, in case the
         * argument value is not given java takes the default value specified in
         * build.xml.
         */
        final int REQUIRED_NUMBER_OF_ARGS = 4;
        try{
            if ((args.length != REQUIRED_NUMBER_OF_ARGS) ||
                    (args[0].equals("${inputFile}")) ||
                    (args[1].equals("${availableItemsFile}")) ||
                    (args[2].equals("${runningAverageWindowSize}")) ||
                    (args[3].equals("${outputFile}"))) {

                System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
                System.exit(0);
            }
            new ValidatorUtil(args);

        System.out.println("Let's get started with this assignment!");

        FileProcessor inputFile = new FileProcessor(args[0]);
        FileProcessor availableItemsFile = new FileProcessor(args[1]);
        StateContext context = new StateContext();
        context.initializeStates();
        StdoutDisplayInterface results = context.update(inputFile,availableItemsFile,args[2]);

        results.writeToConsole();
        FileDisplayInterface fileResults = (FileDisplayInterface) results;
        fileResults.writeToFile(args[3]);

        inputFile.close();
        availableItemsFile.close();
        System.out.println("Completed!!!!!");

        }catch (Exception e){
            System.err.println("Exception caught in Driver class: ");
            System.out.println("Exception: "+ e.getMessage().getClass().getName());
            e.printStackTrace();
        }finally {

        }


    }
}
