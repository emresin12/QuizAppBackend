package Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {

    private int questionIndex;
    private int optionIndex;

    public Answer(int optionIndex, int questionIndex) {
        this.optionIndex = optionIndex;
        this.questionIndex = questionIndex;
    }

}
