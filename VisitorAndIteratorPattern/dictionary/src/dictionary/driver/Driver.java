package driver;

import results.Results;
import results.SpellCheckResults;
import results.TopKFreqWordsResults;
import elements.Element;
import util.FileProcessor;
import elements.MyArrayList;
import validator.ValidatorUtil;
import visitor.SpellCheckAnalyzer;
import visitor.TopKMostFreqAnalyzer;
import visitor.Visitor;

public class Driver {
    private static void runAnalysis(FileProcessor fileProcessor, Visitor... visitors) {
        Element myArrayList = new MyArrayList.Builder()
                .withFileProcessor(fileProcessor)
                .build();

        for (Visitor visitor : visitors) {
            myArrayList.accept(visitor);
        }
    }

    private static void persistResults(Results... analysisResults) {
        for (Results results : analysisResults) {
            results.writeToFile();
        }
    }

    public static void main(String[] args) throws Exception {

        /*
         * As the build.xml specifies the arguments as argX, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used
         * FIXME Refactor commandline validation using the validation design taught in class.
         */
        final int REQUIRED_NUMBER_OF_ARGS = 5;
        try {
            if ((args.length != REQUIRED_NUMBER_OF_ARGS) ||
                    (args[0].equals("${inputFilename}")) ||
                    (args[1].equals("${acceptableWordsFilename}")) ||
                    (args[2].equals("${k}")) ||
                    (args[3].equals("${topKOutputFilename}")) ||
                    (args[4].equals("${spellCheckOutputFilename}"))) {

                System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.err.println("Enter a integer value needed for k");
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Exception caught in Driver class.");
        } finally {

        }
        new ValidatorUtil(args);
        System.out.println("Let's get started with this assignment!");

        FileProcessor fileProcessor = new FileProcessor(args[0]);
        Results topKFreqWordsResults = new TopKFreqWordsResults(args[3]);
        Visitor topKMostFreqAnalyzer = new TopKMostFreqAnalyzer(args[2], topKFreqWordsResults);

        Results spellCheckResults = new SpellCheckResults(args[4]);
        Visitor spellCheckAnalyzer = new SpellCheckAnalyzer(args[1], spellCheckResults);

        runAnalysis(fileProcessor, topKMostFreqAnalyzer, spellCheckAnalyzer);

        persistResults(topKFreqWordsResults, spellCheckResults);

        System.out.println("Completed!");
    }

}
