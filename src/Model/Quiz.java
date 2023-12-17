package Model;

import enums.QuizState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Semaphore;


@Getter
@Setter
public class Quiz {
    private String quizName;
    private ArrayList<Question> questions;
    private ArrayList<Participant> participants;
    private int currentQuestionIndex = 0;
    private Participant admin;
    private QuizState state;

    public Quiz(String quizName, Participant admin, ArrayList<Question> questions) {
        this.quizName = quizName;
        this.admin = admin;
        this.state = QuizState.WAITING_FOR_PLAYERS;
        this.questions = questions;
        this.participants = new ArrayList<>();
    }


    public synchronized void nextQuestion() {
        // check if the participant that issued the command is the admin in the App.QuizManager

        currentQuestionIndex++;

        Question question = questions.get(currentQuestionIndex);

        Message message = new Message();
        message.setMessage("question");
        message.setObjectType("Question");
        message.setPayload(question);

        broadcastToParticipants(message);
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
        //TODO calculate stats

        state = QuizState.FINISHED;
        Stats stats = new Stats(participants);

        Message message = new Message();
        message.setMessage("stats");
        message.setObjectType("Stats");
        message.setPayload(stats);

        broadcastToParticipants(message);
        admin.getClientHandler().sendMessage(message);


    }
    public void addParticipant(Participant participant) {
        // check if quiz state is waiting for players
        if (state != QuizState.WAITING_FOR_PLAYERS) {
            //TODO handle error
            return;
        }

        participants.add(participant);

        int numberOfParticipants = participants.size();

        Message message = new Message();
        message.setMessage("participant.count");
        message.setObjectType("Integer");
        message.setPayload(numberOfParticipants);

        broadcastToParticipants(message);
        admin.getClientHandler().sendMessage(message);

    }
    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }


    public synchronized void answerQuestion(String username,int questionIndex, int optionIndex) {

        if(questionIndex != currentQuestionIndex) {
            //TODO handle error
            return;
        }

        Question question = questions.get(currentQuestionIndex);

        if (question.getCorrectAnswerIndex() == optionIndex) {

            Optional<Participant> participant = participants.stream().filter(p -> p.getUsername().equals(username)).findFirst();
            participant.ifPresent(Participant::increaseScore);

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
