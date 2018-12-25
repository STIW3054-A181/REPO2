import javafx.scene.chart.XYChart;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.peer.SystemTrayPeer;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;


public class ReadPdf2 extends JFrame{


    public static int[] ax = new int [12];
    public static int[] ay = new int [12];
    public static ArrayList<Double> sc = new ArrayList<Double>();
    public static int[] result = new int[100];
    public static ArrayList<Integer> nword = new ArrayList<Integer>();
    public static ArrayList<Integer>w2 = new ArrayList<Integer>();
    public static ArrayList<Integer>numOfCharInWord= new ArrayList<Integer>();
    private static DecimalFormat df2 = new DecimalFormat(".####");
    List<Number> listed = new ArrayList<>();
    private static final String ROW_KEY = " ";


/*---------------------------------------Start of Zscore Code-----------------------------------------------------------*/
    public ReadPdf2() {
        initUI();
    }
    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Z-Score Graph",
                "Number of character in words",
                "Z-score",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Z-Score Graph",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;

    }

    private XYDataset createDataset() {

        XYSeries series = new XYSeries("Plot Line");





        for(int iii = 0;iii < ax.length;iii++){
            series.add(result[iii], sc.get(iii));
        }



        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }
/*-----------------------------------------------End of ZScore Chart code-----------------------------------------------*/

/*-----------------------------------------------Start of BoxPlot code--------------------------------------------------*/



    private void displaybocPlot() {
        JFrame f = new JFrame("BoxPlot");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefaultBoxAndWhiskerCategoryDataset data = new DefaultBoxAndWhiskerCategoryDataset();
        data.add(getInputData(), ROW_KEY, " ");
        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(
                "Boxplot for Q1, Q2, & Q3", ROW_KEY, "Number of characters in word", data, false);
        f.add(new ChartPanel(chart) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(320, 480);
            }
        });
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private List<Number> getInputData() {


        for(int i =0; i < ax.length; i++){
            for(int j = 0; j < ay[i]; j++){

                listed.add(result[i]);
            }
        }

        return listed;
    }



/*-----------------------------------------------End of BoxPlot code----------------------------------------------------*/


    public static void main(String args[]) throws IOException, ExecutionException, InterruptedException {

/*-----------------------------------------This code is to get the PDF File---------------------------------------------*/
        String path = "C:\\Users\\JARD\\Desktop\\realtime PDF";

        String files = null;
        File filew = new File(path);
        File[] listOfFiless = filew.listFiles();

        ArrayList<String>wr=new ArrayList<String>();

        System.out.println("System Read PDF file............\n");

        for (int i = 0; i < listOfFiless.length; i++) {
            if (listOfFiless[i].isFile()) {

                files = listOfFiless[i].getPath();
                File file2 = new File(files);
                PDDocument doc = PDDocument.load(file2);
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String text = pdfStripper.getText(doc);
                System.out.println(listOfFiless[i].getName());
                System.out.println(text);

                wr.add(text);
                doc.close();
            }
        }



        System.out.println("Total number of PDF file = "+wr.size());

        //to get every word in array.............................................

        String [] words = null;
        ArrayList<String>mm = new ArrayList<String>();



        for(int r = 0; r<wr.size();r++) {

            words = wr.get(r).split(" ");

            for(int h =0; h<words.length;h++){
                mm.add(words[h]);
            }

        }

        System.out.println("Total number of word = "+mm.size());

        //to get number of char for total word using callable.....................


        ExecutorService es = Executors.newFixedThreadPool(3);

        List<Future<String>> list = new ArrayList<Future<String>>();

        Future<String>future = es.submit(new CountChar((mm)));

        list.add(future);

        System.out.println("Total number of Character = "+future.get());

        es.shutdown();

        //to get number of chatacter in each word........................................................................



        for(int i=0;i<mm.size();i++)
        {
            String s = mm.get(i);
            nword.add(s.length());
        }

        System.out.println("\n");




        int counter = 0, count = 0;
        for (int i = 0; i < mm.size(); i++) {
            boolean isDistinct = false;
            for (int j = 0; j < i; j++) {
                if (nword.get(i) == nword.get(j)) {
                    isDistinct = true;
                    break;
                }
            }
            if (!isDistinct) {
                result[counter++] = nword.get(i);


            }
        }

        int totalchar = 0;
        for (int i = 0; i < counter; i++) {
            count = 0;
            for (int j = 0; j < mm.size(); j++) {
                if (result[i] == nword.get(j)) {
                    count++;
                    if(j<12){
                        ay[j] = count;

                    }

                }

            }



            System.out.println("number of word with "+result[i]+" character is " + " = " + count);
            numOfCharInWord.add(count);


            totalchar = totalchar + count;

        }

        //to get standard deviation......................................................

        ExecutorService es1 = Executors.newFixedThreadPool(3);

        List<Future<Integer>> wordList = new ArrayList<Future<Integer>>();

        Future<Integer>future1 = es1.submit(new StdDV(result.length,totalchar,numOfCharInWord));

        wordList.add(future1);

        String sd = ""+df2.format(future1.get());

        double sdConvert = Double.parseDouble(sd);


        System.out.format("\nStandard Deviation = %.4f ",future1.get());

        es1.shutdown();


        //to get zScore..................................................................

        ExecutorService es2 = Executors.newFixedThreadPool(3);

       List<Future<Integer>> charlist = new ArrayList<Future<Integer>>();

       Future<Integer>future2 = es2.submit(new Zscore(numOfCharInWord,sdConvert,result.length,totalchar));

       charlist.add(future2);

       String z = ""+future2.get();

       String [] t = null;

       t = z.split(",");



       for(int l = 0;l<t.length;l++){

           if(l==0) {
               sc.add(Double.parseDouble(t[l].replace("[", "")));
           }

           else if(l==(t.length - 1)){
               String j = t[l].replace("]","");
               sc.add(Double.parseDouble(j.replace(" ", "")));
           }

           else{
               sc.add(Double.parseDouble(t[l].replace(" ", "")));
           }
       }


       ArrayList<Double>zScores = new ArrayList<Double>();
      System.out.println("\n");

       for(int u = 0;u<sc.size();u++){
           System.out.println("Z-score for "+result[u]+" character word = "+sc.get(u));
       }



       es2.shutdown();

       //...........................................................................................

        SwingUtilities.invokeLater(() -> {
            ReadPdf2 ex = new ReadPdf2();
            ex.setVisible(true);
        });

        EventQueue.invokeLater(new ReadPdf2()::displaybocPlot);


    }
}
