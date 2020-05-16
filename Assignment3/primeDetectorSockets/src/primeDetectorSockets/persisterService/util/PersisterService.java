package primeDetectorSockets.persisterService.util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PersisterService implements PersisterI {

    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    DataInputStream in = null;
    PrintWriter out = null;
    FileWriter myWriter;

    public PersisterService(String portIn, String outputFileIn) throws IOException {

        myWriter = new FileWriter(outputFileIn);

        try {
            serverSocket = new ServerSocket(Integer.valueOf(portIn));

            String line = "";
            while (!line.equals("STOP")){
                clientSocket = serverSocket.accept();
                System.out.println("server connected");
                in = new DataInputStream(clientSocket.getInputStream());
                line = in.readUTF();
                System.out.println("line: " + line);
                myWriter.write(line + "\n");
                Thread.sleep(500);
            }

            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
            close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws IOException {

        myWriter.close();
    }

    @Override
    public void writeToFile(String data) {

        try {
            myWriter.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
