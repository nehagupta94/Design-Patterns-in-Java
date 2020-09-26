package replicaSystem.myTree;

import replicaSystem.util.Action;
import replicaSystem.util.StudentRecord;

/** Observer Interface
 * It has the method update which is being overridden in the Node class and has studentRecord and action as it's arguments
 * This method tells the program what to do, to insert or to modify the file
 * */


public interface ObserverI {

    void update(StudentRecord studentRecord, Action action);

}
