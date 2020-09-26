package numberPlay.util;

import numberPlay.validator.Validator;
import numberPlay.validator.ValidatorUtil;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TopKNumbersData is the library class which implements
 * interfaces PersisterI, NumberPeaksResultsI;
 *
 *
 *
 * @author  Neha Gupta
 */

public class TopKNumbersData implements PersisterI, TopKNumbersResultsI{

	private final String filename;
	private static int k;
	List<List<Double>> list;
	private FileWriter fileWrite;

	private static class ValidatorFetcher {
		public static Validator fileNameValidator(TopKNumbersData tnd) {
			return new Validator() {
				@Override
				public void run() throws Exception {
					if (tnd.getFilename() == "") {
						throw new Exception("No Running average file provided!");
					}
					if(!tnd.getFilename().endsWith(".txt")){
						throw new  Exception("File extension not txt!");
					}
				}
			};
		}

		public static Validator windowSizeValidator(TopKNumbersData tnd) {
			return new Validator() {
				@Override
				public void run() throws Exception {
					if (tnd.getK() <=0) {
						throw new Exception("Provide number greater than zero!");
					}
				}
			};
		}
	}

	public TopKNumbersData(String kIn, String filenameIn) throws Exception {
		filename = filenameIn;
		try{
			k = Integer.parseInt(kIn);
		}catch(NumberFormatException e){
			System.err.println("Enter a valid Integer number!");
			e.printStackTrace();
		}

		list = new ArrayList<>();

		ValidatorUtil.validate("failed",
				ValidatorFetcher.fileNameValidator(this),
				ValidatorFetcher.windowSizeValidator(this));
	}

	@Override
	public void store(List<Double> topK) {
		list.add(topK);
	}

	@Override
	public void writeToFile() {
		try{
			fileWrite = new FileWriter(getFilename(), false);
			for(List<Double> d : list){
				fileWrite.write("[ ");
				Collections.sort(d,Collections.reverseOrder());
				int comma = 0;
				for(Double a : d ){
					fileWrite.write(Double.toString(a));
					if(comma < d.size() -1){
						fileWrite.write(" , ");
						comma ++;
					}
				}

				fileWrite.write(" ] \n");
			}
		}catch (FileNotFoundException e) {
			System.err.println("Output file cannot be opened!!");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Exception while writing double value to file.");
			System.exit(1);
		}
	}
	
	@Override
	public void close() {
		try{
			fileWrite.close();
		} catch (IOException e){
			System.err.println("Exception in close function of file.");
			System.exit(1);
		}

	}

	public String getFilename() {
		return filename;
	}

	public static int getK() {
		return k;
	}

}
