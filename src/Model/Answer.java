package Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {

    private int quizId;
    private String username;
    private int questionIndex;
    private int optionIndex;

    public Answer(int optionIndex, int questionIndex, int quizId, String username) {
        this.optionIndex = optionIndex;
        this.questionIndex = questionIndex;
        this.quizId = quizId;
        this.username = username;
    }


}
