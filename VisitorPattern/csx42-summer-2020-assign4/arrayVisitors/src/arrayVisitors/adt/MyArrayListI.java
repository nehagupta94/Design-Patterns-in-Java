package arrayVisitors.adt;

import arrayVisitors.visitors.Element;
import arrayVisitors.visitors.Visitor;

import java.util.List;

/**
 * This interface is being implemented in MyArrayList.
 * The method add is used to add the two arrays and takes MyArrayI as a parameter.
 * The method getList is used to to get the List.
 */

public interface MyArrayListI extends Element<Visitor> {

    void add(MyArrayI inputArray);
    //void printList();
    List<MyArrayI> getList();
}
