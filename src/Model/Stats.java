package Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class Stats {
    private ArrayList<String> scores;

    public Stats(ArrayList<String> scores) {
        this.scores = scores;
    }
}
