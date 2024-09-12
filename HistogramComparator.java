import java.util.Comparator;

public class HistogramComparator implements Comparator<ColorHistogram> {
    public int compare(ColorHistogram h1, ColorHistogram h2){
        if (h1.getCompareResult() == h2.getCompareResult()){
            return 0;
        }else if(h1.getCompareResult() > h2.getCompareResult()){
            return -1;
        }else{
            return 1;
        }
    }
}
