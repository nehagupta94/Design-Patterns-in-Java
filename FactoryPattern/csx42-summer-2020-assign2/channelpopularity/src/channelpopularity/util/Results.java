package channelpopularity.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    protected final List<String> cache = new LinkedList<>();
    protected final Writer fileOut;

    public Results(String filename) throws IOException {
        this.fileOut = new FileWriter(filename);
    }

    public void save(String s) {
        this.cache.add(s);
    }

    public boolean hasNext() {
        return !this.cache.isEmpty();
    }

    public String next() {
        return this.hasNext() ? this.cache.remove(0) : null;
    }

    @Override
    public void writeToFile(String writeStr) throws IOException {
        fileOut.write(writeStr);
    }

    @Override
    public void writeToConsole(String writeStr) {
        System.out.print(writeStr);
    }

    public void close() throws IOException {
        this.fileOut.close();
    }

}
