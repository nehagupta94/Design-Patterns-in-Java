package channelpopularity.driver;

import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.operation.Helper;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.FileDisplayInterface;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;
import channelpopularity.util.StdoutDisplayInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * This is the driver class.
 *
 * @author 
 */
public class Driver {
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

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
        if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
            System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
            System.exit(0);
        }
        System.out.println("Hello World! Lets get started with the assignment");

        //FileProcessor inputFile = new FileProcessor(args[0]);

        Results res = null;
        SimpleStateFactoryI simpleStateFactory;
        ContextI context;
        FileProcessor fileProcessor = null;
        Helper help;
        String line;

        try {
            res = new Results(args[1]);
            simpleStateFactory = new SimpleStateFactory();
            context = new ChannelContext(simpleStateFactory, res);
            fileProcessor = new FileProcessor(args[0]);
            help = new Helper();

            line = fileProcessor.poll();
            int numLines = 0;

            while (line != null) {
                context.operation(help.getInputArray(line));

                while (res.hasNext()) {
                    line = res.next() + "\n";
                    ((FileDisplayInterface) res).writeToFile(line);
                    ((StdoutDisplayInterface) res).writeToConsole(line);
                }

                numLines++;
                line = fileProcessor.poll();
            }

            if (numLines == 0)
                throw new RuntimeException("Empty input file");
        } catch (SecurityException |IOException | InvalidPathException exception){
            System.err.println("FILE READING ISSUE!");
            exception.printStackTrace();
        } finally{
            if (res != null)
                res.close();

            if (fileProcessor != null)
                fileProcessor.close();
        }

    }
}
