import java.io.*;
import java.net.*;

public class ReadThread implements Runnable {
    private BufferedReader in;

    public ReadThread(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Disconnected from server.");
        }
    }
}
