package Model;

import enums.QuizState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    private int quizId;
    private String quizName;
    private ArrayList<Question> questions;
    private ArrayList<Participant> participants;
    private int currentQuestionIndex;
    private Participant admin;
    private QuizState state;


    public void nextQuestion() {
        // check if the participant that issued the command is the admin in the App.QuizManager
        currentQuestionIndex++;
    }
    public void startQuiz() {
        // check if the participant that issued the command is the admin in the App.QuizManager
        state = QuizState.WAITING_FOR_PLAYERS;
    }
    public void endQuiz() {
        // check if the participant that issued the command is the admin in the App.QuizManager
        state = QuizState.FINISHED;
    }
    public void addParticipant(Participant participant) {
        participants.add(participant);
    }
    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }


}
