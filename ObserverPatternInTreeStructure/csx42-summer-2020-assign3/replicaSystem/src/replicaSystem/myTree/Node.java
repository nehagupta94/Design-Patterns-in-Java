package replicaSystem.myTree;

import replicaSystem.util.Action;
import replicaSystem.util.StudentRecord;

import java.util.LinkedList;
import java.util.List;

public class Node implements ObserverI, SubjectI, Cloneable {

    public Node left;
    public Node right;

    public StudentRecord studentRecord;
    public List<ObserverI> observers = new LinkedList<>();

    public Node(StudentRecord studentRecordIn) {
        this.studentRecord = studentRecordIn;
        left = null;
        right = null;
    }

    @Override
    public void update(StudentRecord studentRecordIn, Action action) {
        switch (action){
            case INSERT:
                this.studentRecord = studentRecordIn;
                break;
            case MODIFY:
                this.studentRecord = studentRecordIn;
                break;
            default:
                System.err.println("Does not match the pre-requisite action! Check again");
                System.exit(0);
        }

    }

    @Override
    public void registerObserver(ObserverI observerI) {
        if(!observers.contains(observerI)){
            observers.add(observerI);
        }
    }

    @Override
    public void removeObserver(ObserverI observerI) {
        if(observers.contains(observerI)){
            observers.remove(observerI);
        }
    }

    @Override
    public void notifyObserver(Action action) {
        for (ObserverI observerI : observers) {
            observerI.update(studentRecord, action);
        }
    }

    /**
     *
     * clone method is used to replicate the nodes.
     */

    @Override
    public Node clone() {
        Node node = null;
        try {
            node = (Node) super.clone();
        } catch (Exception e) {
            System.err.println("Unable to clone!");
            e.printStackTrace();
        }
        return node;
    }

}
