import java.io.*;
import java.net.*;

public class Client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            new Thread(new ReadThread(socket)).start();
            new Thread(new WriteThread(socket)).start();
        } catch (IOException e) {
            System.out.println("Client2 could not connect to server.");
        }
    }
}
