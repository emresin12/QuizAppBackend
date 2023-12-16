package App;

import Model.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.ClientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private final ClientController clientController;
    Gson gson;
    public ClientHandler(Socket socket, ClientController clientController) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Message.class, new MessageDeserializer());
        Gson gson = gsonBuilder.create();

        this.gson = gson;
        this.clientController = clientController;
    }

    @Override
    public void run() {

        try {
            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {


                Message message = gson.fromJson(clientMessage, Message.class);
//                Object payload = message.getPayload();
//                String payloadType = message.getObjectType();
                clientController.RouteMessage(message, this);

                System.out.println("Received message: " + message);

                // handle client message here
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
