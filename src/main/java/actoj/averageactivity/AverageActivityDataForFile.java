package actoj.averageactivity;

import actoj.DataForFile;
import actoj.core.Actogram;
import ij.IJ;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static actoj.util.Consts.CSV_LINE_SEPARATOR;
import static actoj.util.Consts.CSV_VALUE_SEPARATOR;

/**
 * Created by piotr.rolinski on 04/04/2020.
 */
public class AverageActivityDataForFile implements DataForFile {
    
    
    boolean prepareFileData = false;
    HashMap<Float, HashMap<String, Float>> averageActivityMap = new HashMap<>();
    List<String> namesList = new ArrayList<>();
    
    String period;
    String sigma;
    
    public AverageActivityDataForFile(float p, float s) {
        period = String.format("%d", (long) p);
        sigma = String.format("%d", (long) s);
        prepareFileData = true;
    }


    public boolean isPrepareFileData() {
        return prepareFileData;
    }

    public void setPrepareFileData(boolean prepareFileData) {
        this.prepareFileData = prepareFileData;
    }
    
    public void exportAverageActivity(Actogram actogram, int periodIdx, float[] time, float[] values) {
        namesList.add(actogram.name);
        for (int i = 0; i < periodIdx; i++) {
            Float timeObj = time[i];
            HashMap<String, Float> ys = averageActivityMap.get(timeObj);
            if (ys == null) {
                ys = new HashMap<>();
            }
            ys.put(actogram.name, values[i]);
            averageActivityMap.put(timeObj, ys);
            
        }
    }

    //HashMap<String, Float> ysForName = new HashMap<>();

    public void export2File () {
        if (isPrepareFileData()) {
            try {
                File peaksFile = new File(String.format("%s_%s_%s", period, sigma, "actogram_avg_activity.csv"));
                if (peaksFile.exists()) {
                    if (peaksFile.delete())
                        peaksFile.createNewFile();
                }
                FileWriter fw = new FileWriter(peaksFile, false);
                final BufferedWriter bw = new BufferedWriter(fw);
                StringBuilder aLine = new StringBuilder();
                aLine.append("X0");
                for (String name : namesList)
                    aLine.append(String.format("%s%s", CSV_VALUE_SEPARATOR, name));
                aLine.append(CSV_LINE_SEPARATOR);
                bw.write(aLine.toString());
                List<Float> x0s = new ArrayList<>(averageActivityMap.keySet());
                Collections.sort(x0s);
                for (Float x0 : x0s) {
                    aLine = new StringBuilder();
                    HashMap<String, Float> y0s = averageActivityMap.get(x0);
                    aLine.append(String.format("%f", x0));
                    Collections.sort(namesList);
                    for (String name : namesList) {
                        Float y0 = y0s.get(name);
                        if (y0 != null)
                            aLine.append(String.format("%s%f", CSV_VALUE_SEPARATOR, y0));
                        else
                            aLine.append(String.format("%s", CSV_VALUE_SEPARATOR));
                    }
                    aLine.append(CSV_LINE_SEPARATOR);
                    bw.write(aLine.toString());                    
                        
                }

                bw.flush();
                bw.close();

            } catch(Exception e) {
                IJ.error(e.getClass() + ": " + e.getMessage());
                e.printStackTrace();
            }



        }
    }

}
