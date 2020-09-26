package numberPlay.util;

import numberPlay.subject.SubjectI;

import java.io.IOException;

/**
 * NumberProcessor is the class which will
 * use the poll method from FileProcessor class to read one number at a time
 * and notify the obeservers about the number read.
 *
 *
 * @author  Neha Gupta
 */

public class NumberProcessor {
    FileProcessor fp;
    int number;

    public NumberProcessor(String filename) throws IOException{
        number = 0;
        fp = new FileProcessor(filename);
    }


    public void checkNumber(SubjectI publisher) throws IOException {
        String numberIn;
        do{
            numberIn = fp.poll();
            publisher.notifyObservers(numberIn);
        }while(numberIn != null);
        publisher.notifyObservers(numberIn);
        fp.close();
    }

}
