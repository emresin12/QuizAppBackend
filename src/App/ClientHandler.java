package App;

import Model.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import controller.ClientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;
    private final ClientController clientController;
    Gson gson;
    public ClientHandler(Socket socket, ClientController clientController) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Message.class, new MessageDeserializer());
        this.gson = gsonBuilder.create();
        this.clientController = clientController;
    }

    @Override
    public void run() {

        try {
            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                Message message;
                try {
                    message = gson.fromJson(clientMessage, Message.class);
                } catch (JsonSyntaxException e) {
                    sendMessage(new Message("error", "String", "invalid json"));
                    continue;
                }

                if(message == null){
                    sendMessage(new Message("error", "String", "invalid message format"));
                    continue;
                }

                clientController.RouteMessage(message, this);

                System.out.println("Received message: " + message);

                // handle client message here
            }

        } catch (IOException e) {
            System.out.println("Client disconnected");
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(Message message) {
        String jsonString = gson.toJson(message);
        out.println(jsonString);
    }
}
