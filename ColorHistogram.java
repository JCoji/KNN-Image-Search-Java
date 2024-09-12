import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Math;
import java.io.FileWriter;

public class ColorHistogram{
    int d;
    int numColour;
    String filename;
    String[] colourList;
    int[] normalHisto;
    ColorImage cI;

    double compareResult = -1;

    public ColorHistogram (int d){
        this.d = d;
        numColour = (int)Math.pow(2, d * 3);

        normalHisto = new int[numColour];

    } 

    public ColorHistogram (String filename){
        this.filename = filename;
        readFile();
        normalHisto = new int[numColour];

    }

    public void readFile(){
        try {
            File input = new File(filename);
            Scanner reader = new Scanner(input);

            numColour = Integer.parseInt(reader.nextLine());  //Check first line number. (Size of array.)
            colourList = reader.nextLine().split(" ");  //Array of numColour size.

            reader.close();

            /*Printing array for test.
            for (int i = 0; i < colourList.length; i++){
                System.out.print(colourList[i] + " ");
            }*/
        }
        
        catch (FileNotFoundException e) {
        System.out.println("Cannot find file of name: " + filename);
        e.printStackTrace();
    }
    }

    public void setImage(ColorImage image){
        cI = image;

    }

    public String getFileName(){
        return filename;
    }

    public int[] getHistogram(){
        int total = 0;

        //Add all pixels from image to obtain total amount of pixels.
        for (int i = 0; i < colourList.length; i++){
            total = (total + Integer.parseInt(colourList[i]));
        }

        //Divide each number with the total number of pixels to get ratio.
        //Returning the numbers into normalHisto array.
        for (int j = 0; j < colourList.length; j++){
            normalHisto[j] = (Integer.parseInt(colourList[j]));
            //System.out.print(normalHisto[j] + " ");
        }

        return normalHisto;
        
    }

    public double getCompareResult(){
        return compareResult;
    }

    public void queryToHistogram(){
        //use ci's rgb -> color list (String)
        int pos = 0;
        int[] pixel;
        
        colourList = new String[numColour];
        
        for(int i = 0; i < colourList.length; i++){
            colourList[i] = "0";
        }

        for(int i = 0; i < cI.getheight(); i++){
            for(int j = 0; j < cI.getWidth(); j++){
                pixel = cI.getPixel(j, i);

                //Compute algorithm ++ that position in colorList
                pos = (pixel[0] << (2 * d)) + (pixel[1] << d) + pixel[2];

                //System.out.println("i:" + i + " j:" + j + " {" + pixel[0] +"," + pixel[1] + "," +pixel[2] + " | "+ pos + "} ");
                //System.out.println((pixel[0] << (2 * 3)) + " + " + (pixel[1] << 3) + " + " + pixel[2]);
                //System.out.println(colourList.length);
                colourList[pos] =  "" + (Integer.parseInt(colourList[pos]) + 1);
                
            }
        }


        getHistogram();
    }

    public double compare(ColorHistogram hist){
        //double[] histA = getHistogram();
        //double[] histB = hist.getHistogram();
        double compareTotal = 0;

        //for (int i = 0; i < histA.length; i++){
        //    compareTotal = compareTotal + Math.min(histA[i], histB[i]);
        //}

        compareResult = compareTotal;

        return compareTotal;

    }

    public void saveHistogram (String filename){
        //Creating .txt file.
        try{
            File histoFile = new File(filename + "_COPY.txt");

            if (histoFile.createNewFile()){
                System.out.println("FILE CREATED");
            }
            }
        catch (IOException e) {
            System.out.println("FILE ERROR");
        }

        //Writing normalhisto info on the .txt file.
        try{
            FileWriter writer = new FileWriter(filename + "_COPY.txt");
            writer.write(numColour + "\n");
            
            for (int i = 0; i < normalHisto.length; i++){
                writer.write(Integer.toString(normalHisto[i]) + " ");
            }

            writer.close();
        }
        catch (IOException e) {
            System.out.println("WRITING ERROR");
        }
        
    }

}
