package actoj.periodogram;

import actoj.DataForFile;
import actoj.core.Actogram;
import ij.IJ;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static actoj.util.Consts.CSV_LINE_SEPARATOR;
import static actoj.util.Consts.CSV_VALUE_SEPARATOR;

/**
 * Created by piotr.rolinski on 04/04/2020.
 */
public class PeriodogramDataForFile implements DataForFile {
    
    boolean prepareFileData = false;
    int maxPeaks = 0;
    StringBuilder dataBuffer;
    String actogramGroupName = null;

    public boolean isPrepareFileData() {
        return prepareFileData;
    }

    public void setPrepareFileData(boolean prepareFileData) {
        this.prepareFileData = prepareFileData;
    }

    public int getMaxPeaks() {
        return maxPeaks;
    }

    public void setMaxPeaks(int maxPeaks) {
        this.maxPeaks = maxPeaks;
    }

    private StringBuilder getDataBuffer() {
        if (dataBuffer == null)
            dataBuffer = new StringBuilder();
        return dataBuffer;
    }

    private String convertToString() {
        return (dataBuffer != null ? dataBuffer.toString() : null);
    }
    
    public String getActogramGroupName() {
        return actogramGroupName;
    }

    public void setActogramGroupName(String actogramGroupName) {
        if (this.actogramGroupName == null)
            this.actogramGroupName = actogramGroupName;
    }


    public void exportPeriodogramPeaks(Actogram actogram, int fromPeriodIdx, float factor, int[] peaks, float[] values, float[] pValues) {


        ArrayList<Integer> peaksObjectified = new ArrayList<>(peaks.length);
        for (int peak : peaks)
            peaksObjectified.add(peak);
        List<Integer> peaksAbove = peaksObjectified.stream().filter(p -> values[p] >= pValues[p]).collect(Collectors.toList());
        StringBuilder dataBuffer = getDataBuffer();
        dataBuffer.append(String.format("%s%s", actogram.name, CSV_VALUE_SEPARATOR));
        if (!peaksAbove.isEmpty()) {
            Float pValue = null;
            for (Integer peak: peaksAbove) {
                if (pValue == null) {
                    pValue = pValues[peak];
                    dataBuffer.append(String.format("%f%s", pValue, CSV_VALUE_SEPARATOR));
                } else {
                    dataBuffer.append(',');
                }
                float period = (fromPeriodIdx + peak) * factor;
                dataBuffer.append(String.format("%f%s%f", period, CSV_VALUE_SEPARATOR, values[peak]));
            }
        }	else {
            dataBuffer.append(String.format("%d%s%d",0, CSV_VALUE_SEPARATOR, 0));
        }
        setMaxPeaks(Math.max(getMaxPeaks(), peaksAbove.size() > 0 ? peaksAbove.size() : 1));
        dataBuffer.append(CSV_LINE_SEPARATOR);

    }

    public void export2File () {
        if (isPrepareFileData()) {
            try {
                File peaksFile = new File(String.format("%s_%s", getActogramGroupName(), "actogram_peaks.csv"));
                if (peaksFile.exists()) {
                    if (peaksFile.delete())
                        peaksFile.createNewFile();
                }
                FileWriter fw = new FileWriter(peaksFile, false);
                final BufferedWriter bw = new BufferedWriter(fw);
                StringBuilder titleLine = new StringBuilder();
                titleLine.append(String.format("Tube%sPN Significance", CSV_VALUE_SEPARATOR));
                for (int i = 0; i < getMaxPeaks(); i++)
                    titleLine.append(String.format("%sTau %d [min]%sPN%d", CSV_VALUE_SEPARATOR, (i+1), CSV_VALUE_SEPARATOR, (i+1)));
                titleLine.append(CSV_LINE_SEPARATOR);
                bw.write(titleLine.toString());
                bw.write(convertToString());
                bw.flush();
                bw.close();

            } catch(Exception e) {
                IJ.error(e.getClass() + ": " + e.getMessage());
                e.printStackTrace();
            }



        }
    }
    

}
