package arrayVisitors.adt;

import arrayVisitors.visitors.Visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyArray implements MyArrayI {

    List<Integer> list;

    public MyArray(){
        list = new ArrayList<>(10);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void add(Integer i){
        list.add(i);
    }

    /*@Override
    public void printList() {
        System.out.println(list);
    }*/

    @Override
    public MyArrayI getList() {
        return this;
    }

    @Override
    public List<Integer> getIntegerList() {
        return list;
    }


}
