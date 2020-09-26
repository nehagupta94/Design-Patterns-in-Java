package arrayVisitors.visitors;

import arrayVisitors.adt.MyArrayI;
import arrayVisitors.adt.MyArrayListI;

/**
 * This interface has been implemented by CommonIntsVisitor, MissingIntsVisitor and PopulateMyArray
 * The method visit takes MyarrayI as a parameter and is used to define the logic for missing int and common int.
 * The method visit takes MyArrayListI as a parameter and works on the ArrayList.
 */

public interface Visitor {
    void visit(MyArrayI array);
    void visit(MyArrayListI arrayList);
}
