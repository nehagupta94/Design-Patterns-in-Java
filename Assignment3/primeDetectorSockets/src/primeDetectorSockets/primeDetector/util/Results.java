package primeDetectorSockets.primeDetector.util;

import java.util.ArrayList;
import java.util.List;

public class Results {

    static int capacity = 0;
    static String port;
    static String ipAddress;
    public static List<String> list;

    public Results(String capacityIn, String ipAddressIn, String portIn){

        capacity = Integer.valueOf(capacityIn);
        port = portIn;
        ipAddress = ipAddressIn;
        list = new ArrayList<>();
    }

    public Results(String data) {

        synchronized (this){
            list.add(data);
            System.out.println("PQ" + list);
            Thread t = new Thread(new DataSender(list.remove(0), port, ipAddress));
            t.start();
        }

    }

    public Boolean checkCapacity(){
        if (list.size() >= capacity){
            return true;
        } else {
            return false;
        }
    }
}
