
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Boolean.TRUE;

public class ClientThread extends Thread {
    private Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            int ok=1;
            while(ok!=0) {
                String request = in.readLine();
                if (request.equals("stop") == TRUE) {
                    String raspuns = "Server stopped!";
                    out.println(raspuns);
                    out.flush();
                    socket.close();
                    ok=0;
                } else {
                    String raspuns = "Server received the request...";
                    out.println(raspuns);
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        }
    }

}