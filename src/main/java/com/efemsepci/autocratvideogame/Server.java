package com.efemsepci.autocratvideogame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<ClientHandler> clients = new ArrayList<>();

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started on port 12345");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private DataInputStream in;
        private DataOutputStream out;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Receive messages from the client
                    String message = in.readUTF();
                    System.out.println("Client: " + message);

                    // Broadcast the message to all clients
                    for (ClientHandler client : clients) {
                        if (client != this) {
                            client.sendMessage(message);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Method to send message to the client
        public void sendMessage(String message) throws IOException {
            out.writeUTF(message);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
