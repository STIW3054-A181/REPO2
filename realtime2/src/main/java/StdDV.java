import java.util.ArrayList;
import java.util.concurrent.Callable;

public class StdDV implements Callable {
    private double totalword,totalwordchar;

    private ArrayList<Integer>words;

    public StdDV(double totalword, double totalwordchar, ArrayList<Integer> words) {
        this.totalword = totalword;
        this.totalwordchar = totalwordchar;
        this.words = words;
    }

    public Object call() throws Exception {

        double sdv = 0.0;

        double mean = totalwordchar/totalword;

        for( int i = 0; i<words.size();i++){
            sdv += Math.pow(words.get(i) - mean,2);
        }
        return Math.sqrt((sdv/(words.size()-1)));
    }
}
