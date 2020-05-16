package primeDetectorSockets.primeDetector.driver;


import primeDetectorSockets.primeDetector.threadManager.CreateWorkers;
import primeDetectorSockets.primeDetector.util.FileProcessor;
import primeDetectorSockets.primeDetector.util.IsPrime;
import primeDetectorSockets.primeDetector.util.Results;
import primeDetectorSockets.primeDetector.validator.Validator;
import primeDetectorSockets.primeDetector.validator.ValidatorUtil;

/**
 * @author Team-DP
 * TODO update the author name.
 */
public class Driver {

    public static void main(String[] args) throws Exception {

        /*
         * As the build.xml specifies the arguments as argX, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used
         * FIXME Refactor commandline validation using the validation design taught in class.
         */
        final int REQUIRED_NUMBER_OF_ARGS = 6;
        try{
            if ((args.length != REQUIRED_NUMBER_OF_ARGS) ||
                    (args[0].equals("${inputStream}")) ||
                    (args[1].equals("${numThreads}")) ||
                    (args[2].equals("${capacity}")) ||
                    (args[3].equals("${persisterServiceIp}")) ||
                    (args[4].equals("${persisterServicePort}")) ||
                    (args[5].equals("${debugValue}"))) {

                System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
                System.exit(0);
            }
        }catch(NumberFormatException e){
            System.err.println("Enter a integer value needed for numThreads, capacity, persisterServicePort, and debugValue");
            System.exit(0);
        }catch (Exception e){
            System.err.println("Exception caught in Driver class.");
        }finally {
        }

        System.out.println("Let's get started with this assignment!");
        new ValidatorUtil(args);

        CreateWorkers createWorkers = new CreateWorkers(new FileProcessor(args[0]), new IsPrime(), new Results(args[2], args[3], args[4]));
        createWorkers.startWorkers(args[1]);

        System.out.println("Completed!!!!!");

    }
}

