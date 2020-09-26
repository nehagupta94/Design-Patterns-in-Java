package replicaSystem.util;

import java.io.IOException;

/**
 * This is just an interface with writetoFile function which writes the output to a file
 * writetoResultFile is being used to write the results to the file.
 * writetoErrorFile is being used to write errors to the error file. It takes String s as a parameter.
 * Both the methods are overridden in the Results class.
 */
public interface FileDisplayInterface {

    void writeToResultFile() throws IOException;

    void writeToErrorFile(String s) throws IOException;
	
}
