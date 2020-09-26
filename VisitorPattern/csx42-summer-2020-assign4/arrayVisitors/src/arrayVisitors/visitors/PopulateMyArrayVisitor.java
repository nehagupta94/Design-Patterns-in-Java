package arrayVisitors.visitors;

import arrayVisitors.adt.MyArray;
import arrayVisitors.adt.MyArrayI;
import arrayVisitors.adt.MyArrayListI;
import arrayVisitors.util.FileProcessor;

import java.io.IOException;

public class PopulateMyArrayVisitor implements Visitor {

    FileProcessor fp;

    public PopulateMyArrayVisitor(FileProcessor fpIn){
        this.fp = fpIn;
        //myArray = new MyArray();
    }

    @Override
    public void visit(MyArrayI element) {
        buildArray(element);
    }

    @Override
    public void visit(MyArrayListI arrayList) {
        // No logic
    }

    /**
     *
     * @param input
     * The method validates the input, checks if the numbers are in correct input.
     * @exception Number format exception.
     */

    private boolean validateInput(String input){
        if(input.trim().length() > 2){
            return false;
        }
        try{
            Integer.parseInt(input);
            return true;
        }catch (NumberFormatException e){
            System.err.println("Number Format exception caught in reading input file!");
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param element
     * The methods builds an array.
     * @exception  Input file is empty.
     */

    private void buildArray(MyArrayI element){

        try{
            String line = fp.poll();
            if(line == null){
                throw new RuntimeException("Input file is empty!!");
            }
            while(line != null){
                if(validateInput(line)){
                    element.add(Integer.parseInt(line));
                    //myArray.addInList();
                }else{
                    throw new RuntimeException("Issue in polling input file!");
                }
                line = fp.poll();
            }
        }catch (IOException e){

        }
    }
}
