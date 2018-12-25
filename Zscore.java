import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Zscore implements Callable {

    private ArrayList<Integer>words;

    private double sd,totalword,totalwordchar;

    public Zscore(ArrayList<Integer> words, double sd, double totalword, double totalwordchar) {
        this.words = words;
        this.sd = sd;
        this.totalword = totalword;
        this.totalwordchar = totalwordchar;
    }



    public Object call() throws Exception {

        double score=0.0;

        ArrayList<Double> zScore = new ArrayList<Double>();

        double mean = totalwordchar/totalword;

        for(int i=0; i<words.size();i++){
            score = (words.get(i) - mean)/sd;

            zScore.add(score);
        }


        return zScore;
    }
}
