package primeDetectorSockets.primeDetector.util;

import java.io.*;
import java.net.Socket;

public class DataSender implements Runnable{

    Socket clSocket = null;
    DataOutputStream out = null;
    BufferedReader in = null;
    String ipAddress;
    int port;
    String data;

    public DataSender(String dataIn, String portIn, String ipAddressIn) {

        data = dataIn;
        port = Integer.parseInt(portIn);
        ipAddress = ipAddressIn;
    }

    @Override
    public void run() {

        try {
            clSocket = new Socket(ipAddress, port);
            System.out.println("Connected");
            in = new BufferedReader(new InputStreamReader(
                    clSocket.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(clSocket.getOutputStream()));

            synchronized (data){


                while (Results.list.isEmpty()){
                    System.out.println("inside wait");
                    data.wait();
                }

                sendData(data, out);
                Thread.sleep(5000);
                data.notifyAll();

            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendData(String data, DataOutputStream out) throws IOException {

        System.out.println("data to be sent: " + data);
        while (!data.equals("STOP")){
            out.writeUTF(data);
            out.flush();
        }

        System.out.println("time to close");
        out.close();
        in.close();
        clSocket.close();
    }
}
