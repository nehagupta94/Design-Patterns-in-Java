package decoratorPattern.driver;

import decoratorPattern.decorator.*;
import decoratorPattern.util.FileDisplayInterface;
import decoratorPattern.util.FileProcessor;
import decoratorPattern.util.InputDetails;

import java.io.IOException;
import java.nio.file.InvalidPathException;

/**
 *
 * This is the driver class.
 *
 * @author
 */
public class Driver {

    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 4;

    /**
     *
     * @param args
     * @throws Exception Incorrect number of arguments
     * @throws Exception Files name match
     * @throws Exception File names does not end with .txt
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
                    (args[1].equals("${keywords}")) ||
                    (args[2].equals("${misspelledWords}")) ||
                    (args[3].equals("${output}"))) {
                System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
                System.exit(0);
            }
        }catch (Exception e){
            System.err.println("Exception caught in Driver class.");
        }finally {
        }

        try{
            if(args[0].equalsIgnoreCase(args[1]) || args[2].equalsIgnoreCase(args[3])
            || args[1].equalsIgnoreCase(args[3]) || args[0].equalsIgnoreCase(args[3])){
                throw new RuntimeException(("Files name match!!"));
            }
            if(!(args[0].endsWith(".txt") && args[1].endsWith(".txt") && args[2].endsWith(".txt") && args[3].endsWith(".txt"))){
                throw new RuntimeException("File names does not end with .txt !");
            }
        }catch (Exception e){
            System.err.println("Exception caught in validation of inputs!!");
            System.exit(0);
        }
        System.out.println("Hello World! Lets get started with the assignment");

        InputDetails inputD = new InputDetails(args[0], args[1], args[2], args[3]);
        AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
        AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
        AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
        AbstractTextDecorator mostFreqWordDecorator = new MostFrequentDecorator(keywordDecorator, inputD);

        mostFreqWordDecorator.processInputDetails();

        ((FileDisplayInterface) inputD).writeToResultFile();

        System.out.println("Completed!! ");


    }
}
