package primeDetectorSockets.persisterService.driver;


import primeDetectorSockets.persisterService.util.PersisterI;
import primeDetectorSockets.persisterService.util.PersisterService;
import primeDetectorSockets.persisterService.validator.ValidatorUtil;

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
        final int REQUIRED_NUMBER_OF_ARGS = 2;
        try{
            if ((args.length != REQUIRED_NUMBER_OF_ARGS) ||
                    (args[0].equals("${port}")) ||
                    (args[1].equals("${outputFile}"))) {

                System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
                System.exit(0);
            }
        }catch(NumberFormatException e){
            System.err.println("Enter a integer value needed for port");
            System.exit(0);
        }catch (Exception e){
            System.err.println("Exception caught in Driver class.");
        }finally {
        }

        System.out.println("Let's get started with Persister Service!");
        new ValidatorUtil(args);

        PersisterI p = new PersisterService(args[0], args[1]);


        System.out.println("Completed!!!!!");

    }
}

