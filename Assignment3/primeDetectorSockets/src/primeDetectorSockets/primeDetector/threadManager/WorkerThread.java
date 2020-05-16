package primeDetectorSockets.primeDetector.threadManager;

import primeDetectorSockets.primeDetector.util.FileProcessor;
import primeDetectorSockets.primeDetector.util.IsPrime;
import primeDetectorSockets.primeDetector.util.Results;

import java.io.IOException;

public class WorkerThread implements Runnable {

    FileProcessor fileProcessor;
    IsPrime isPrime;
    Results results;
    private String number;
    static int sum;

    public synchronized void processNumber(int number) throws InterruptedException {

        if (isPrime.checkPrime(number)){
            System.out.println(Thread.currentThread().getName() + " in progress with " + number);
            synchronized (this){
                while (results.checkCapacity()){
                    wait();
                }
                new Results(String.valueOf(number));
                sum+= Integer.valueOf(number);
                notifyAll();
            }
        }
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " started");
            try {
                number = fileProcessor.poll();

                while (number != null){

                    try {

                        processNumber(Integer.valueOf(number));

                    } catch (NumberFormatException | InterruptedException nfe){

                        nfe.printStackTrace();
                    }
                    number = fileProcessor.poll();
                }

                Results.list.add("STOP");

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " done");
    }

    public WorkerThread(FileProcessor fileProcessorIn, IsPrime isPrimeIn, Results resultsIn){

        fileProcessor = fileProcessorIn;
        isPrime = isPrimeIn;
        results = resultsIn;
    }
}
