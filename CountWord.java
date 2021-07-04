import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.Callable;

public class CountWord implements Callable<Integer> {

    private final File input;

    public CountWord(File input) {
        this.input = input;
    }

    public Integer call() throws Exception {

        BufferedReader bf = new BufferedReader(new FileReader(input));

        String line = null;

        int count = 0;

        int wordCounter = 0;

        while ((line = bf.readLine()) != null) {

            String[] words = line.split(" ");


            wordCounter += words.length;

            count++;

        }



        bf.close();

        return count;

    }



}
