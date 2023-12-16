import App.ClientHandler;
import controller.ClientController;
import service.ClientService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        ClientService clientService = new ClientService();
        ClientController clientController = new ClientController(clientService);

        try {
            ServerSocket serverSocket = new ServerSocket(3131); // replace with your port

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, clientController);
                new Thread(clientHandler).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}