package primeDetectorSockets.primeDetector.threadManager;

import primeDetectorSockets.primeDetector.container.ThreadPool;
import primeDetectorSockets.primeDetector.util.FileProcessor;
import primeDetectorSockets.primeDetector.util.IsPrime;
import primeDetectorSockets.primeDetector.util.Results;

import java.util.ArrayList;
import java.util.List;

public class CreateWorkers {

    FileProcessor fileProcessor;
    IsPrime isPrime;
    Results results;
    List<Thread> threads;
    ThreadPool threadPool;

    public CreateWorkers(FileProcessor fileProcessorIn, IsPrime isPrimeIn, Results resultsIn) {

        fileProcessor = fileProcessorIn;
        isPrime = isPrimeIn;
        results = resultsIn;

    }

    public void startWorkers(String numOfThreads) throws InterruptedException {

        threads = new ArrayList<>();
        threadPool = new ThreadPool(new WorkerThread(fileProcessor, isPrime, results));
        for (int i = 0; i < Integer.valueOf(numOfThreads); i++){
            threads.add(threadPool.borrow());
            threads.get(threads.size() - 1).start();
        }

        for (Thread t : threads){t.join();}
        for (Thread t : threads){threadPool.returnToPool(t);}

    }
}
