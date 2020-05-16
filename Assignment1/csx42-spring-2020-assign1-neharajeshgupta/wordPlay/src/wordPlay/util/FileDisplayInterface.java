package wordPlay.util;

import java.io.IOException;

/**
 * <h1> File Display Interface </h1>
 *
 * Interface for File Display methods
 *
 * @author Neha Gupta
 * @version 1.0.0
 */

public interface FileDisplayInterface {
	void writeToMetricsFile() throws IOException;
	void writeToOutputFile() throws IOException;
}
