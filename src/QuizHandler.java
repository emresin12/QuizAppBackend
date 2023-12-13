public class QuizHandler {


    public void addParticipantToQuiz(Participant participant, Quiz quiz) {
        quiz.getParticipants().add(participant);
    }
}
