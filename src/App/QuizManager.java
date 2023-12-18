package App;

import Model.Answer;
import Model.Message;
import Model.Participant;
import Model.Quiz;
import enums.QuizState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class QuizManager {
    private static QuizManager instance;
    private HashMap<Integer,Quiz> quizzes;


    private QuizManager() {
        quizzes = new HashMap<>();
    }


    public static QuizManager getInstance() {
        if (instance == null) {
            instance = new QuizManager();
        }
        return instance;
    }

    public void createQuiz(Quiz quiz) {
        int quizId = generateRandomQuizId();
        quizzes.put(quizId, quiz);

        Message message = new Message();
        message.setMessage("shared.quiz.id");
        message.setObjectType("Integer");
        message.setPayload(quizId);

        quiz.getAdmin().getClientHandler().sendMessage(message);
    }
    public void endQuiz(int quizId) {
        // TODO: handle for participants
        Quiz quiz = quizzes.get(quizId);

        quiz.endQuiz();

        quizzes.remove(quizId);
    }
    public void startQuiz(int quizId) {
        Quiz quiz = quizzes.get(quizId);
        quiz.startQuiz();
    }
    public void nextQuestion(int quizId) {
        Quiz quiz = quizzes.get(quizId);
        quiz.nextQuestion();
    }
    public void addParticipantToQuiz(int quizId, Participant participant) {
        Quiz quiz = quizzes.get(quizId);
        if(quiz != null) {
            quiz.addParticipant(participant);
        }
        else{
            Message m = new Message();
            m.setMessage("error");
            m.setObjectType("String");
            m.setPayload("hosted quiz id not found");
            participant.getClientHandler().sendMessage(m);
        }
    }
//    public void removeParticipantFromQuiz(int quizId, Participant participant) {
//        //disconnect case
//        quiz.removeParticipant(participant);
//    }
    public void answerQuestion(int quizId, String username, int questionIndex, int optionIndex) {
        Quiz quiz = quizzes.get(quizId);
        quiz.answerQuestion(username, questionIndex, optionIndex);
    }

    private int generateRandomQuizId() {
        Random random = new Random();
        int randId = random.nextInt(1000, 9999);

        //TODO generate random quiz id
        while((quizzes.containsKey(randId))) {
            randId = random.nextInt(1000, 9999);
        }
        return randId;
    }




}
