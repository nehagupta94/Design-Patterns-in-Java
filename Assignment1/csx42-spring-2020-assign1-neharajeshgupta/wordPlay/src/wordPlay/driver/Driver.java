package wordPlay.driver;

import wordPlay.util.*;

import java.io.IOException;
import java.util.List;

/**
 * <h1> Word Play </h1>
 * The word play program implements an algorithm which will read an input file
 * and to reverse the words constituting sentences in a file and also to calculate certain metrics.
 *
 * @author Neha Gupta
 * @version 1.0.0
 *
 */
public class Driver {
	public static void main(String[] args) throws IOException {

		/**
		 * This is the main method which makes use of FileProcessor, Results, WordPlay methods.
		 * @param args
		 * @return Nothing
		 * @exception IOException On input error.
		 * @see IOException
		 */

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${arg0}")) || (args[1].equals("${arg1}")) || (args[2].equals("${arg2}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment\n");

		String line = null;
		FileProcessor file = new FileProcessor(args);
		WordUtil word = new WordUtil();
		StringBuilder str = new StringBuilder();
		while((line = file.readLine()) != null){
			word.reverseWord(line);
			str.append(line).append("\n");
		}
		String metrics = str.toString().replace("\n"," ");
		word.calculateMetrics(metrics);
		StdoutDisplayInterface stdoutDisplayInterface = new Results();
		stdoutDisplayInterface.writeToConsole();
		FileDisplayInterface fileDisplayInterfacec= new Results();
		fileDisplayInterfacec.writeToMetricsFile();
		fileDisplayInterfacec.writeToOutputFile();
	}
}
