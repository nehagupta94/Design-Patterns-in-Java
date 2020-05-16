package wordPlay.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * <h1> File Processor class </h1>
 *
 * This class is for reading a line from a file.
 *
 * @author Neha Gupta
 * @version 1.0.0
 */

public class FileProcessor {
	BufferedReader reader;
	File file;
	private String METRIC_FILE;
	private String OUTPUT_FILE;

	public FileProcessor(String[] filename) {
		try {
			String inputFile = getInputFileName(filename);
			file = new File(inputFile);
			if (file.exists() && confirmFileExtension(inputFile) && !file.isDirectory())
				reader = new BufferedReader(new FileReader(filename[0]));
			setOutputFilename(filename);
			setMetricsFilename(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FileProcessor() { }

	/**
	 * This is readLine method which will read the line and return it's value.
	 * @return line as a string
	 * @exception IOException On input error.
	 * @see IOException
	 */
	public String readLine() throws IOException {
		String read = null;
 		try{
			if ((read = reader.readLine()) != null)
				;
		}catch (IOException e){
 			e.printStackTrace();
		}
 		return read;
	}

	/**
	 * This is the method to check the type of extension of the file.
	 * @param path of the file
	 * @return true or false
	 */

	private boolean confirmFileExtension(String path){
		String extension = path.substring(path.lastIndexOf(".") + 1, path.length());
		if(extension.trim().contentEquals("txt"))
			return true;
		return false;
	}

	String getInputFileName(String[] names){
		return names[0];
	}

	void setOutputFilename(String[] names) throws NullPointerException {
		try {
			if(confirmFileExtension(names[1]))
				OUTPUT_FILE = names[1];
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	void setMetricsFilename(String[] names) {
		if(confirmFileExtension(names[2]))
			METRIC_FILE = names[2];
	}

	public String getOutputFilename(){
		return OUTPUT_FILE;
	}

	public String getMetricFilename(){
		return METRIC_FILE;
	}
}
