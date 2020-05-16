package primeDetectorSockets.primeDetector.util;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;


public final class FileProcessor {
	private BufferedReader reader;
	private String line;

	public FileProcessor(String inputFilePath) 
		throws InvalidPathException, SecurityException, FileNotFoundException, IOException {
		
		if (!Files.exists(Paths.get(inputFilePath))) {
			throw new FileNotFoundException("invalid input file or input file in incorrect location");
		}
		Logger.writeMessage("In Parameterized Constructor of FileProcessor.\n", Logger.DebugLevel.DEBUG4);
		reader = new BufferedReader(new FileReader(new File(inputFilePath)));
		line = reader.readLine();
	}

	public synchronized String poll() throws IOException {
		if (null == line) return null;

		String newValue = line.trim();
		line = reader.readLine();
		return newValue;
	}

	public void close() throws IOException {
		try {
			reader.close();
			line = null;
		} catch (IOException e) {
			throw new IOException("failed to close file", e);
		}
	}
}
