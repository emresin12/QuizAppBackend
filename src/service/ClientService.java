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
        quizManager.createQuiz(quiz);
    }
    public void joinQuiz(Participant participant, int quizId){
        quizManager.addParticipantToQuiz(quizId, participant);
    }
    public void answerQuestion(Participant participant, int quizId, Answer answer){
        quizManager.answerQuestion(quiz, participant, answer);

    }
    public void startQuiz(int quizId){
        //TODO handle start quiz
        quizManager.startQuiz(quizId);
    }
    public void endQuiz(int quizId){
        //TODO handle end quiz
        quizManager.endQuiz(quiz);
    }
    public void nextQuestion(int quizId){
        //TODO handle next question
        quizManager.nextQuestion(quiz);
    }
}
