package App;

import Model.Answer;
import Model.Participant;
import Model.Quiz;

import java.util.ArrayList;

public class QuizManager {
    private static QuizManager instance;

    private QuizManager() {
        quizzes = new ArrayList<>();
    }
    private ArrayList<Quiz> quizzes;

    public static QuizManager getInstance() {
        if (instance == null) {
            instance = new QuizManager();
        }
        return instance;
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }
    public void endQuiz(Quiz quiz) {
        quizzes.remove(quiz);
    }
    public void startQuiz(Quiz quiz) {
        quiz.startQuiz();
    }
    public void nextQuestion(Quiz quiz) {
        quiz.nextQuestion();
    }
    public void addParticipantToQuiz(Quiz quiz, Participant participant) {
        quiz.addParticipant(participant);
    }
    public void removeParticipantFromQuiz(Quiz quiz, Participant participant) {
        //disconnect case
        quiz.removeParticipant(participant);
    }
    public void answerQuestion(Quiz quiz, Participant participant, Answer answer) {
        quiz.answerQuestion(participant, answerIndex);
    }




}
