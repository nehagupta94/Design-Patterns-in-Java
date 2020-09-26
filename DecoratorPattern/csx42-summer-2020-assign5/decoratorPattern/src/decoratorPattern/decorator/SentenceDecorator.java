package decoratorPattern.decorator;

import decoratorPattern.util.InputDetails;

public class SentenceDecorator extends AbstractTextDecorator {

    private AbstractTextDecorator atd;
    private InputDetails id;

    public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
        atd = atdIn;
        id = idIn;
        start = "BEGIN_SENTENCE__";
        end = "__END_SENTENCE.";
    }


    @Override
    public void processInputDetails() {
        String line = id.retrieveInput();
        String output = processSentenceDecorator(line);
        id.updateInput(output);
        if (null != atd) {
            atd.processInputDetails();
        }
    }

    /**
     *
     * @param line
     * @return
     * This function implements decorator Pattern logic on a complete sentence taking line as a parameter.
     */

    private String processSentenceDecorator(String line) {
        StringBuilder str = new StringBuilder();
        String[] args = line.split("[\\.]+");
        int count = args.length;
        for(String s : args){
            if(count > 1){
                str.append(start);
                str.append(s);
                str.append(end);
                count --;
            }
        }
        return str.toString();
    }
}
