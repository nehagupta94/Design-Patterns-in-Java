package replicaSystem.myTree;

import replicaSystem.util.Action;

/** Subject Interface
 * This has the following methods which are being overridden in Node class
 * registerObserver with observerI as parameters is the function which is being used to register nodes as listeners
 * notifyObserver with action as parameter is the function which is being used to notify all other listeners when some changes are made
 * */

public interface SubjectI {

    void registerObserver(ObserverI observerI);

    void removeObserver(ObserverI observerI);

    void notifyObserver(Action action);
}
