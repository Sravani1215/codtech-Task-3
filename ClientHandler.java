import java.io.*;
import java.net.*;
import java.util.Set;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String name;
    private Set<ClientHandler> clients;

    public ClientHandler(Socket socket, Set<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error creating client handler: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            out.println("Enter your name:");
            name = in.readLine();
            Server.broadcast(name + " has joined the chat.", this);

            String message;
            while ((message = in.readLine()) != null) {
                Server.broadcast(name + ": " + message, this);
            }
        } catch (IOException e) {
            System.out.println(name + " disconnected.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clients.remove(this);
            Server.broadcast(name + " has left the chat.", this);
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
