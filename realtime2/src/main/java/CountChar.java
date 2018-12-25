import java.util.ArrayList;
import java.util.concurrent.Callable;

public class CountChar implements Callable<String> {

    private ArrayList<String>words;

    public CountChar(ArrayList<String> words) {
        this.words = words;
    }

    public String call() throws Exception {

        int totalCha = 0;


        for(int i =0;i<words.size();i++){
           totalCha += words.get(i).length();
        }
        return ""+totalCha;
    }
}
