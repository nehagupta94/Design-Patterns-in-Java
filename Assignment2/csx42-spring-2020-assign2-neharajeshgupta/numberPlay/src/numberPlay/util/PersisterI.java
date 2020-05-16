package numberPlay.util;

/**
* To be implemented by classes that persist generated output data.
*/
public interface PersisterI {
	void close();
	void writeToFile();
}
