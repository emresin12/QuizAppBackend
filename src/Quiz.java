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
    private boolean isStarted = false;
    private boolean isEnded = false;


    public void nextQuestion() {
        // check if the participant that issued the command is the admin in the QuizManager
        currentQuestionIndex++;
    }
    public void startQuiz() {
        // check if the participant that issued the command is the admin in the QuizManager
        isStarted = true;
    }
    public void endQuiz() {
        // check if the participant that issued the command is the admin in the QuizManager
        isEnded = true;
    }

}
