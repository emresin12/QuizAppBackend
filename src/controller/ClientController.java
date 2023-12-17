package controller;
import App.ClientHandler;
import Model.*;
import service.ClientService;

public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    public void RouteMessage(Message message, ClientHandler clientHandler) {
        String action = message.getMessage();
        switch (action) {
            case "start.quiz":
                Integer quizId = (Integer)message.getPayload();
                clientService.startQuiz(quizId);

                break;
            case "join.quiz":
                JoinQuizRequest joinQuizRequest = (JoinQuizRequest)message.getPayload();
                Participant participant = new Participant(joinQuizRequest.getUsername(), clientHandler);
                clientService.joinQuiz(participant, joinQuizRequest.getQuizId());

                break;
            case "answer.question":
                Answer answer = (Answer)message.getPayload();
                clientService.answerQuestion(answer);
                break;
            case "create.quiz":
                Participant admin = new Participant("admin", clientHandler);
                Quiz quiz = (Quiz)message.getPayload();
                quiz.setAdmin(admin);
                clientService.createQuiz(quiz);
                break;
            case "end.quiz":
                Integer quizIdEnd = (Integer)message.getPayload();
                clientService.endQuiz(quizIdEnd);
                break;
            case "next.question":
                Integer quizIdNext = (Integer)message.getPayload();
                clientService.nextQuestion(quizIdNext);
                break;

            default:
                // handle default which is non recognized command
                break;
        }
    }
}
