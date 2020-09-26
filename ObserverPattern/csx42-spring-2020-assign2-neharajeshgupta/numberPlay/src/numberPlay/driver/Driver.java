package numberPlay.driver;

import numberPlay.observer.NumberPeaksDataObserver;
import numberPlay.observer.ObserverI;
import numberPlay.observer.RunningAverageObserver;
import numberPlay.observer.TopKNumbersDataObserver;
import numberPlay.filter.Filter;
import numberPlay.filter.FilterI;
import numberPlay.subject.Publisher;
import numberPlay.subject.SubjectI;
import numberPlay.util.*;

/**
 * @author Neha Gupta
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
					(args[0].equals("${inputNumStream}")) ||
					(args[1].equals("${runAvgWindowSize}")) ||
					(args[2].equals("${runAvgOutFile}")) ||
					(args[3].equals("${k}")) ||
					(args[4].equals("${topKNumOutFile}")) ||
					(args[5].equals("${numPeaksOutFile}"))) {

				System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
				System.exit(0);
			}
		}catch(NumberFormatException e){
			System.err.println("Enter a integer value needed for runAvgWindowSize and k");
			System.exit(0);
		}catch (Exception e){
			System.err.println("Exception caught in Driver class.");
		}finally {
		}

		System.out.println("Let's get started with this assignment!");

		TopKNumbersResultsI topKNumbersResults = new TopKNumbersData(args[3],args[4]);
		RunningAverageResultsI runningAverageResults = new RunningAverageData(args[1],args[2]);
		NumberPeaksResultsI numberPeaksResults = new NumberPeaksData(args[5]);

		// FIXME Instantiate the subject.

		SubjectI publisher = new Publisher();

		// FIXME Instantiate the observers, providing the necessary filter and the results object.

		ObserverI topKNumbersObserver = new TopKNumbersDataObserver(topKNumbersResults);
		ObserverI runningAverageObserver = new RunningAverageObserver(runningAverageResults);
		ObserverI numberPeakDataObserver = new NumberPeaksDataObserver(numberPeaksResults);

		FilterI floatFilter = new Filter(Event.FLOAT);
		FilterI intFilter = new Filter(Event.INTEGER);
		FilterI processingComplete = new Filter(Event.PROCESSING_COMPLETE);

		// FIXME Register each observer with the subject for the necessary set of events.

		publisher.registerObserver(numberPeakDataObserver,intFilter);
		publisher.registerObserver(numberPeakDataObserver,floatFilter);
		publisher.registerObserver(numberPeakDataObserver,processingComplete);

		publisher.registerObserver(topKNumbersObserver,intFilter);
		publisher.registerObserver(topKNumbersObserver,floatFilter);
		publisher.registerObserver(topKNumbersObserver,processingComplete);

		publisher.registerObserver(runningAverageObserver,intFilter);
		publisher.registerObserver(runningAverageObserver,processingComplete);

		// FIXME Delegate control to a separate utility class/method that provides numbers one at a time, from the FileProcessor, to the subject.

		NumberProcessor numberProcessor = new NumberProcessor(args[0]);
		numberProcessor.checkNumber(publisher);

		publisher.removeObserver(topKNumbersObserver);
		publisher.removeObserver(runningAverageObserver);
		publisher.removeObserver(numberPeakDataObserver);

		System.out.println("Completed!!!!!");

	}
}
