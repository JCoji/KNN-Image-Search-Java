import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class SimilaritySearch{

    static int k = 5; 
    
    public static void main(String[] args) {
        ColorImage[] queryImage = new ColorImage[16];
        ColorHistogram[] queryHistogram = new ColorHistogram[16];

        ColorHistogram[] datasetHistograms;
        String queryPhotoName = args[0];
        String datasetFilePath = "toPrint";

        

        //Setup for query image
        /* 
        queryImage = new ColorImage(queryPhotoName);
        queryHistogram = new ColorHistogram(3);
        queryHistogram.setImage(queryImage);
        queryHistogram.queryToHistogram();

        File dataSetFiles = new File(datasetFilePath);

        //File filter, only accepting histogram .txt files
        File[] files = dataSetFiles.listFiles(new FilenameFilter() {
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(".txt");
            }   
        });

        datasetHistograms = new ColorHistogram[files.length];

        //Conv all .txt files to histograms, stores in array
        for (int i = 0; i < files.length; i++) {

            if (files[i].isFile()) {
                datasetHistograms[i] = new ColorHistogram(datasetFilePath + "/"+ files[i].getName());
            }
        }

        findKNN(queryHistogram, datasetHistograms);
        */

        //Print out histograms for all query images
        File dataSetFiles = new File(datasetFilePath);
        File[] files = dataSetFiles.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".ppm");
                }   
        });

        datasetHistograms = new ColorHistogram[files.length];

        //Conv all .txt files to histograms, stores in array
        for (int i = 0; i < files.length; i++) {
            System.out.println(i);

            if (files[i].isFile()) {
                queryImage[i] = new ColorImage(datasetFilePath + "/"+ files[i].getName());
                queryHistogram[i] = new ColorHistogram(3);
                queryHistogram[i].setImage(queryImage[i]);
                queryHistogram[i].queryToHistogram();
                queryHistogram[i].saveHistogram(datasetFilePath + "/"+ files[i].getName());
            }
        }

        
    }

    public static void findKNN(ColorHistogram query, ColorHistogram[] data){

        ArrayList<ColorHistogram> knn = new ArrayList<ColorHistogram>();


        for (int i = 0; i < data.length; i++) {
            data[i].compare(query);
            
            knn.add(data[i]);

            if(knn.size() > k){
                knn.sort(new HistogramComparator());
                knn.remove(knn.size() - 1);
            }

        }

        System.out.println("The " + k + " most similar images to the query image are: ");

        for(int i = 0; i < knn.size(); i ++){
            System.out.println((i + 1) + ". "+ knn.get(i).getFileName() + " - " + knn.get(i).getCompareResult());
        }
    }
}
