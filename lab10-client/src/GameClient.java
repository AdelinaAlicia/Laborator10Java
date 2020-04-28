import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    public static void main (String[] args) throws IOException {
        String serverAddress = "127.0.0.1";// The server's IP address
        int PORT = 8100;// The server's port 
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader (
                        new InputStreamReader(socket.getInputStream())) ) {
            int ok=1;
            while(ok!=0) {
                Scanner keyboard = new Scanner(System.in);
                System.out.println("\nEnter a command:");
                // Send a request to the server
                String request = keyboard.nextLine();
                out.println(request);
                
                // Wait the response from the server
                String response = in.readLine();
                if(response.equals("Server stopped!"))
                    ok=0;
                System.out.println(response);
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}