package primeDetectorSockets.primeDetector.container;

import primeDetectorSockets.primeDetector.threadManager.WorkerThread;

import java.util.*;

public class ThreadPool {

    LinkedList<Thread> pool;
    Thread t;
    int poolSize = 10;

    public void addThreads(WorkerThread workerThread){

        pool = new LinkedList<>();
        for (int i = 0; i < poolSize; i++){
            t = new Thread(workerThread);
            pool.add(t);
        }
    }

    public Thread borrow() {
        return pool.poll();
    }

    public void returnToPool(Thread t) {

        pool.add(t);
    }

    public void invalidateThread(Thread t) {

        pool.remove(t);
    }

    public ThreadPool(WorkerThread workerThread){

        addThreads(workerThread);
    }


}
