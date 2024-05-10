package com.efemsepci.autocratvideogame;

import com.efemsepci.autocratvideogame.classes.GameSession;
import com.efemsepci.autocratvideogame.classes.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private List<ClientHandler> clients = new ArrayList<>();

    private static String user1 = "WAITING";
    private static String user2 = "WAITING";
    private static String user3 = "WAITING";
    private static String user4 = "WAITING";

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
                    processMessage(message);
                    System.out.println("Client: " + message);


                    // Broadcast the message to all clients
                    for (ClientHandler client : clients) {
                        if (client != this) {
                            client.sendMessageToClient();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Method to send message to the client
        public void sendMessageToClient() throws IOException {
            String usernamesDataJSON = "{\"user1\":\"" + user1 + "\", \"user2\":\"" + user2 + "\", \"user3\":\"" + user3 + "\", \"user4\":\"" + user4 + "\"}";
            out.writeUTF(usernamesDataJSON);
        }
    }

    private String convertObjectListToJson(List<Object> objectList) {
        try {
            return objectMapper.writeValueAsString(objectList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private void processMessage(String message) {
        try {
            // Gelen mesajı JSON formatından nesnelere çevir
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(message);
            String username = null;
            // JSON dizesindeki her bir nesneyi işle
            for (JsonNode node : rootNode) {
                // Nesnenin türüne göre işlem yap
                if (node.has("username")) {
                    username = node.get("username").asText();
                    System.out.println("Received user: " + username);
                } else if (node.has("numOfPlayers")) {
                    int numOfPlayers = node.get("numOfPlayers").asInt();
                    if(numOfPlayers == 0){
                        user1 = username;
                    }
                    else if(numOfPlayers == 1){
                        user2 = username;
                    }
                    else if(numOfPlayers == 2){
                        user3 = username;
                    }
                    else if(numOfPlayers == 3){
                        user4 = username;
                    }
                    System.out.println("Received game session with " + numOfPlayers + " players.");
                } else {
                    System.out.println("Unknown object received: " + node);
                }
            }

            // Değişkenleri güncelledikten sonra, clientlara yeni veriyi gönder
            sendUpdatedUsernamesToClients();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendUpdatedUsernamesToClients() {
        for (ClientHandler client : clients) {
            try {
                client.sendMessageToClient();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
