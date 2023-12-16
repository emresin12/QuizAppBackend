package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JoinQuizRequest {
    private int quizId;
    private String username;
}
