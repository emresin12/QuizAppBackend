package controller;
import Model.Message;
import service.ClientService;

public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    public void RouteMessage(Message message) {
        String action = message.getMessage();
        switch (action) {
            case "start.quiz":
                clientService.startQuiz(message);
                break;
            case "join.quiz":
                clientService.joinQuiz(message);
                break;
            case "answer.question":
                clientService.answerQuestion(message);
                break;
            case "create.quiz":
                //TODO handle create quiz
                clientService.createQuiz(message);
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
