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
    public void answerQuestion(Answer answer){
        int quizId = answer.getQuizId();
        String username = answer.getUsername();
        int questionIndex = answer.getQuestionIndex();
        int optionIndex = answer.getOptionIndex();

        quizManager.answerQuestion(quizId, username, questionIndex, optionIndex);

    }
    public void startQuiz(int quizId){

        quizManager.startQuiz(quizId);
    }
    public void endQuiz(int quizId){

        quizManager.endQuiz(quizId);
    }
    public void nextQuestion(int quizId){
        //TODO handle next question
        quizManager.nextQuestion(quizId);
    }
}
