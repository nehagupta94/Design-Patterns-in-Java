package arrayVisitors.visitors;

import arrayVisitors.adt.MyArrayI;
import arrayVisitors.adt.MyArrayListI;
import arrayVisitors.util.Results;

import java.util.ArrayList;
import java.util.List;

public class MissingIntsVisitor implements Visitor {

    Results missingIntsVisitorResults;
    private final List<Integer> arrayOfInt;
    List<Integer> listOne;
    List<Integer> listTwo;


    public MissingIntsVisitor(Results missingIntsVisitorResultsIn){
        missingIntsVisitorResults = missingIntsVisitorResultsIn;
        arrayOfInt = missingArray();
        listOne = new ArrayList<>();
        listTwo = new ArrayList<>();
    }

    private List<Integer> missingArray(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i <= 99 ;i++){
            list.add(i);
        }
        //System.out.println(list);
        return list;
    }

    @Override
    public void visit(MyArrayI array) {
        // no logic
    }

    @Override
    public void visit(MyArrayListI arrayList) {
        List<List<Integer>> result = missingIntsLogic(arrayList);
        missingIntsVisitorResults.addInResult(result);
    }

    /**
     *
     * @param arrayList
     * The method adds values in List.
     */

    private List<List<Integer>> addValueInList(MyArrayListI arrayList){
        List<List<Integer>> lists = new ArrayList<>();
        List<MyArrayI> list = arrayList.getList();
        for(MyArrayI myArrayI : list){
            lists.add(myArrayI.getIntegerList());
        }
        return lists;
    }

    /**
     *
     * @param lists
     * The method compares both the list.
     */

    private List<List<Integer>> compareLists(List<List<Integer>> lists){
        List<List<Integer>> result = new ArrayList<>();
        result.add(getDifference(lists.get(0)));
        result.add(getDifference(lists.get(1)));
        System.out.println(result);
        //rrayOfInt.removeAll(lists.get(0));
        return result;
    }

    /**
     *
     * @param list
     * The method finds the difference in the lists.
     */

    private List<Integer> getDifference(List<Integer> list){
        List<Integer> result = new ArrayList<>();
        for(Integer i : arrayOfInt){
            if(!list.contains(i)){
                result.add(i);
            }
        }
        return result;
    }

    /**
     *
     * @param arrayList
     * The method finds the missing ints.
     */

    private List<List<Integer>> missingIntsLogic(MyArrayListI arrayList){
        List<List<Integer>> lists = addValueInList(arrayList);
        return compareLists(lists);
    }
}
