package arrayVisitors.adt;

import arrayVisitors.visitors.Element;
import arrayVisitors.visitors.Visitor;

import java.util.List;

/**
 * This is an interface MyArray, all the methods have been implemented in MyArray
 * The method add is used to add the integer and takes Integer as parameter.
 * The method get list is used to get the list
 * The method getIntegerList is used to get in the list in integer format.
 */

public interface MyArrayI extends Element<Visitor> {

    void add(Integer integer);
    //void printList();
    MyArrayI getList();
    List<Integer> getIntegerList();
}
