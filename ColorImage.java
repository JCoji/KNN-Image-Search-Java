import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ColorImage {

    String fileName;
    int width;
    int height;
    int depth;
    int D = 3;

    int[][] r;
    int[][] g;
    int[][] b;

    ColorImage(String fileName){
        this.fileName = fileName;
        readFile();
        reduceColor(D);
     
       
    }

    public int getWidth(){
        return width;
    }

    public int getheight(){
        return height;
    }

    public int getDepth() {
        return depth;
    }

    public int getD(){

        return D;
    }

    public void readFile(){
        try {
            File input = new File(fileName); 
            Scanner myReader = new Scanner(input);

 
            myReader.nextLine();
            myReader.nextLine();

            String[] data = myReader.nextLine().split(" ");

            width = Integer.parseInt(data[0]);
            height = Integer.parseInt(data[1]);
            depth = Integer.parseInt(myReader.nextLine());

            r = new int[width][height];
            g = new int[width][height];
            b = new int[width][height];

            int i = 0;
            int j = 0;



            while (myReader.hasNextLine()) {
                data = myReader.nextLine().split(" ");

                for(int k = 0; k < data.length; k = k + 3){
                    int red = Integer.parseInt(data[k]);
                    int green = Integer.parseInt(data[k + 1]);
                    int blue = Integer.parseInt(data[k + 2]);
                
                    r[i][j] = red;
                    g[i][j] = green;
                    b[i][j] = blue;

                    if(i == width - 1){
                        i = 0;

                        if(j == height){
                            break;
                        }else{
                            j++;
                        }
   
                    }else{
                        i++;
                    }
                    
                    
                }
            }

            

            myReader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("cannot find file of name: " + fileName);
            e.printStackTrace();
        }
    }
    
    public int[] getPixel(int i, int j){
        int[] pixel = new int[3];

        pixel[0] = r[i][j];
        pixel[1] = g[i][j];
        pixel[2] = b[i][j];

        return pixel;
    }

    public void reduceColor(int d){

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){

                //d should be 3
                r[j][i] = r[j][i] >> (8 - d);
                g[j][i] = g[j][i] >> (8 - d);
                b[j][i] = b[j][i] >> (8 - d);
            }
        }
        
    }
}


