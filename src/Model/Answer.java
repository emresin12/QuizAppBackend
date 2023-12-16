package Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    private String answer;
    private int questionIndex;

    public Answer(String answer, int questionIndex, int participantIndex) {
        this.answer = answer;
        this.questionIndex = questionIndex;
    }

}
