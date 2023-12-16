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
    private String quizName;
    private ArrayList<Question> questions;
    private ArrayList<Participant> participants;
    private int currentQuestionIndex = 0;
    private Participant admin;
    private QuizState state;


    public void nextQuestion() {
        // check if the participant that issued the command is the admin in the App.QuizManager
        currentQuestionIndex++;
    }
    public void startQuiz() {
        // check if the participant that issued the command is the admin in the App.QuizManager
        state = QuizState.IN_PROGRESS;

        Question question = questions.get(currentQuestionIndex);

        Message message = new Message();
        message.setMessage("question");
        message.setObjectType("Question");
        message.setPayload(question);

        broadcastToParticipants(message);

    }
    public void endQuiz() {
        // check if the participant that issued the command is the admin in the App.QuizManager
        state = QuizState.FINISHED;
    }
    public void addParticipant(Participant participant) {

        participants.add(participant);

        int numberOfParticipants = participants.size();

        Message message = new Message();
        message.setMessage("participant.count");
        message.setObjectType("Integer");
        message.setPayload(numberOfParticipants);

        broadcastToParticipants(message);

    }
    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }
    public void answerQuestion(Participant participant, Answer answer) {

        if(answer.getQuestionIndex() != currentQuestionIndex) {
            //TODO handle error
            return;
        }
        Question question = questions.get(currentQuestionIndex);
        if (question.getCorrectAnswerIndex() == answer.getAnswerIndex()) {
            //TODO handle correct answer
        } else {
            //TODO handle wrong answer
        }

    }
    private void broadcastToParticipants(Message message) {
        for (Participant participant : participants) {
            participant.getClientHandler().sendMessage(message);
        }
    }

}
