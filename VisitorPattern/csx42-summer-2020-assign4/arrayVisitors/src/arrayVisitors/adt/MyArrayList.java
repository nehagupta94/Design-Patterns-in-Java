package arrayVisitors.adt;

import arrayVisitors.visitors.Visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyArrayList implements MyArrayListI {

    private List<MyArrayI> myArrayList;

    public MyArrayList(){
        myArrayList = new ArrayList<>();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void add(MyArrayI inputArray) {
        myArrayList.add(inputArray);
    }

    @Override
    public List<MyArrayI> getList() {
        return myArrayList;
    }
}
