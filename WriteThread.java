import java.io.*;
import java.net.*;

public class WriteThread implements Runnable {
    private PrintWriter out;
    private BufferedReader console;

    public WriteThread(Socket socket) throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            String input;
            while ((input = console.readLine()) != null) {
                out.println(input);
            }
        } catch (IOException e) {
            System.out.println("Error sending message.");
        }
    }
}
