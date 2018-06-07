package TCPServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jhene
 */
public class TCPServer implements Cloneable, Runnable {

    ServerSocket server = null;
    Thread runner = null;
    volatile Boolean stop = false;
    Socket data = null;
    int port = 6789;

    public synchronized void startServer(int port) throws IOException {
        if (runner != null) {
            server = new ServerSocket(port);
            runner = new Thread(this);
            runner.start();
        }
    }

    public synchronized void stopServer() throws Exception {
        if (server != null) {
            runner.interrupt();
            runner = null;
            stop = true;
            try {
                server.close();
            } catch (IOException e) {
                System.out.println("Server Stopped");
            }

            server = null;

        }
    }

    @Override
    public void run() {
        if (server != null) {
            while (!stop) {
                try {
                    Socket datasocket = server.accept();
                    TCPServer newSocket = (TCPServer) clone();
                    newSocket.server = null;
                    newSocket.data = datasocket;
                    newSocket.runner = new Thread(newSocket);
                    newSocket.runner.start();

                } catch (Exception e) {
                }
            }
        } else {
            try {
                run(data);
            } catch (IOException ex) {
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void run(Socket data) throws IOException {
        try {
            ServerSocket welcomeSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Servidor Aguardando cliente");
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                String clientSentence = inFromClient.readLine();
                System.out.println("Texto vindo do cliente: " + clientSentence);
                String capitalizeSentence = clientSentence.toUpperCase() + "\n";
                outToClient.writeBytes(capitalizeSentence);
            }
        } catch (Exception e) {
        }
    }

}
