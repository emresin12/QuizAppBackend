package service;

import Model.Answer;
import Model.Participant;
import Model.Quiz;
import App.QuizManager;
import Model.Message;



public class ClientService {

    private final QuizManager quizManager;

    public ClientService() {
        this.quizManager = QuizManager.getInstance();
    }

    public void createQuiz(Quiz quiz){
        //TODO handle create quiz
        quizManager.addQuiz(quiz);

    }
    public void joinQuiz(Participant participant, Quiz quiz){
        quizManager.addParticipantToQuiz(quiz, participant);
    }
    public void answerQuestion(Participant participant, Quiz quiz, Answer answer){
        quizManager.answerQuestion(quiz, participant, answerIndex);

    }
    public void startQuiz(Message message){
        //TODO handle start quiz
    }
    public void endQuiz(Message message){
        //TODO handle end quiz
    }
    public void nextQuestion(Message message){
        //TODO handle next question
    }
}
