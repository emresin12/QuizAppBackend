package Model;

import lombok.Data;

import java.util.ArrayList;
@Data
public class CreateQuizRequest {
    private ArrayList<Question> questions;
    private String quizName;
}