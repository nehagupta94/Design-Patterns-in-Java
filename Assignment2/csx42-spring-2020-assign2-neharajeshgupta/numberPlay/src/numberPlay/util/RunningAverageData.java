package numberPlay.util;

import numberPlay.validator.Validator;
import numberPlay.validator.ValidatorUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * RunningAverageData is the library class which implements
 * interfaces PersisterI, NumberPeaksResultsI;
 *
 *
 *
 * @author  Neha Gupta
 */

public class RunningAverageData implements PersisterI, RunningAverageResultsI {

	private final String filename;
	private static int windowSize;
	FileWriter fileWriter;
	private List<Double> result;

	private static class ValidatorFetcher {
		public static Validator fileNameValidator(RunningAverageData rad) {
			return new Validator() {
				@Override
				public void run() throws Exception {
					if (rad.getFilename() == "") {
						throw new Exception("No Running average file provided!");
					}
					if(!rad.getFilename().endsWith(".txt")){
						throw new  Exception("File extension not txt!");
					}
				}
			};
		}

		public static Validator windowSizeValidator(RunningAverageData rad) {
			return new Validator() {
				@Override
				public void run() throws Exception {
					if (rad.getWindowSize() <=0) {
						throw new Exception("Provide number greater than zero!");
					}
				}
			};
		}
	}

	public RunningAverageData(String windowSizeIn, String filenameIn) throws Exception{
		filename = filenameIn;
		result = new ArrayList<>();
		try {
			windowSize = Integer.parseInt(windowSizeIn);
		}catch(NumberFormatException e){
			System.err.println("Enter a valid Integer number!");
			e.printStackTrace();
		}
		ValidatorUtil.validate("failed",
				ValidatorFetcher.fileNameValidator(this),
				ValidatorFetcher.windowSizeValidator(this));
	}



	@Override
	public void store(Double d) {
		result.add(d);
	}

	@Override
	public void writeToFile() {
		try{
			fileWriter = new FileWriter(filename, false);
			for(Double d : result)
				fileWriter.write(d + "\n");
		}catch(IOException e){
			System.err.println("Error in opening output file");
			System.exit(1);
		}
	}

	@Override
	public void close() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getWindowSize() {
		return windowSize;
	}

	public String getFilename() {
		return filename;
	}

}
