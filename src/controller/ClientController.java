package controller;
import App.ClientHandler;
import Model.JoinQuizRequest;
import Model.Message;
import Model.Participant;
import Model.Quiz;
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
                clientService.answerQuestion(message);
                break;
            case "create.quiz":
                Participant admin = new Participant("admin", clientHandler);
                Quiz quiz = (Quiz)message.getPayload();
                quiz.setAdmin(admin);
                clientService.createQuiz(quiz);
                break;
            case "end.quiz":
                clientService.endQuiz(message);
                break;
            case "next.question":
                clientService.nextQuestion(message);
                break;

            default:
                // handle default which is non recognized command
                break;
        }
    }
}
