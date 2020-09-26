package arrayVisitors.visitors;

import arrayVisitors.adt.MyArrayI;
import arrayVisitors.adt.MyArrayListI;
import arrayVisitors.util.Results;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonIntsVisitor implements Visitor {

    Results commonIntsVisitorResults;
    List<Integer> listOne;
    List<Integer> listTwo;

    public CommonIntsVisitor(Results commonIntsVisitorResultsIn){
        commonIntsVisitorResults = commonIntsVisitorResultsIn;
        listOne = new ArrayList<>();
        listTwo = new ArrayList<>();
    }

    @Override
    public void visit(MyArrayI array) {
        //no logic
    }

    /**
     *
     * @param arrayList
     * The methods addsValues in List.
     */

    private List<List<Integer>> addValueInList(MyArrayListI arrayList){
        List<List<Integer>> lists = new ArrayList<>();
        List<MyArrayI> list = arrayList.getList();
        for(MyArrayI myArrayI : list){
            lists.add(myArrayI.getIntegerList());
        }
        return lists;
    }

    @Override
    public void visit(MyArrayListI arrayList) {
        List<List<Integer>> lists = addValueInList(arrayList);
        List<Integer> result = compareLists(lists);
        commonIntsVisitorResults.addResult(result);
    }

    /**
     *
     * @param lists
     * The method compares both the lists.
     */


    private List<Integer> compareLists(List<List<Integer>> lists){
        listOne = lists.get(0);
        listTwo = lists.get(1);
        listOne.retainAll(listTwo);
        Set<Integer> set = new HashSet(listOne);
        List<Integer> result = new ArrayList<>(set);
        return result;
    }
}
