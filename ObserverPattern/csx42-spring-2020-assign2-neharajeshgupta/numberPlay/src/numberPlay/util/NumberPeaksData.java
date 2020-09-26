package numberPlay.util;

import numberPlay.validator.Validator;
import numberPlay.validator.ValidatorUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * NumberPeaksData is the library class which implements
 * interfaces PersisterI, NumberPeaksResultsI;
 *
 *
 * @author  Neha Gupta
 */

public class NumberPeaksData implements PersisterI, NumberPeaksResultsI {

	private ArrayList<Double> result;

	private final String filename;
	private FileWriter fileWriter;

	private static class ValidatorFetcher {
		public static Validator fileNameValidator(NumberPeaksData npd) {
			return new Validator() {
				@Override
				public void run() throws Exception {
					if (npd.getFilename() == "") {
						throw new Exception("No Running average file provided!");
					}
					if(!npd.getFilename().endsWith(".txt")){
						throw new  Exception("File extension not txt!");
					}
				}
			};
		}

	}

	public NumberPeaksData(String filenameIn) throws Exception {
		result = new ArrayList<>();
		filename = filenameIn;

		ValidatorUtil.validate("failed",
				ValidatorFetcher.fileNameValidator(this));
	}
	@Override
	public void store(Double d) {
		result.add(d);
	}

	private ArrayList<Double> getResult(ArrayList<Double> result){
		ArrayList<Double> newResult = new ArrayList<>();
		newResult.add(result.get(0));
		for(int i = 1; i < result.size() ; i++){
			if(result.get(i-1) != result.get(i))
				newResult.add(result.get(i));
		}
		return newResult;
	}

	@Override
	public void writeToFile(){
		try{
			fileWriter = new FileWriter(filename, false);
			for(Double d : getResult(result)){
				fileWriter.write(d + "\n");
			}
		}catch (IOException e){
			System.err.println("Output file cannot be opened!!");
			System.exit(1);
		}catch (NullPointerException e){
			System.err.println("Caught during write to file.");
		}

	}

	@Override
	public void close() {
		try{
			fileWriter.close();
		}catch(IOException e){
			System.err.println("Output file cannot be closed!!");
			System.exit(1);
		}

	}

	public String getFilename() {
		return filename;
	}

}
