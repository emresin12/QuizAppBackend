package Model;

import App.ClientHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Participant {

    private String username;
    private int score;
    private ClientHandler clientHandler;

    public Participant(String username,ClientHandler clientHandler) {
        this.username = username;
        this.score = 0;
        this.clientHandler = clientHandler;
    }

    public void increaseScore() {
        score++;
    }

}
