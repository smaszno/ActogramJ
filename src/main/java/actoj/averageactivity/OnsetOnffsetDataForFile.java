package actoj.averageactivity;

import actoj.DataForFile;
import actoj.core.Actogram;
import ij.IJ;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static actoj.util.Consts.CSV_LINE_SEPARATOR;
import static actoj.util.Consts.CSV_VALUE_SEPARATOR;

/**
 * Created by piotr.rolinski on 11/10/2020.
 */
public class OnsetOnffsetDataForFile implements DataForFile {


    boolean prepareFileData = false;
    HashMap<String, List<OnsetOffset>> actogramNameToOnsetOffsetListMap = new HashMap<>();
    int maxListSize = -1;
    List<String> namesList = new ArrayList<>();



    public boolean isPrepareFileData() {
        return prepareFileData;
    }

    public void setPrepareFileData(boolean prepareFileData) {
        this.prepareFileData = prepareFileData;
    }

    public void exportPointsList(Actogram actogram) {
        if (actogram.getOnsetOffsetPointsList().size() > 0) {
            prepareFileData = true;
            maxListSize = Math.max(maxListSize, actogram.getOnsetOffsetPointsList().size());
            namesList.add(actogram.name);
            List<OnsetOffset> onsetOffsetList = actogram.getOnsetOffsetPointsList();
            onsetOffsetList.sort(Comparator.comparing(OnsetOffset::getXForTheWhole));
            actogramNameToOnsetOffsetListMap.put(actogram.name, onsetOffsetList);
        }
    }

    public void export2File () {
        if (isPrepareFileData()) {
            try {
                File onsetOffsetFile = new File(String.format("%s", "onset_offset.csv"));
                if (onsetOffsetFile.exists()) {
                    if (onsetOffsetFile.delete())
                        onsetOffsetFile.createNewFile();
                }
                FileWriter fw = new FileWriter(onsetOffsetFile, false);
                final BufferedWriter bw = new BufferedWriter(fw);
                StringBuilder aLine = new StringBuilder();

                Collections.sort(namesList);
                for (String name : namesList) {
                    aLine.append(String.format("%s%s%s", name, CSV_VALUE_SEPARATOR,CSV_VALUE_SEPARATOR));
                }
                aLine.append(CSV_LINE_SEPARATOR);
                bw.write(aLine.toString());


                for (int i = 0; i < maxListSize; i++) {
                    aLine = new StringBuilder();
                    for (String name : namesList) {
                        List<OnsetOffset> onsetOffsetList = actogramNameToOnsetOffsetListMap.get(name);
                        if (onsetOffsetList.size() > i) {
                            OnsetOffset oO = onsetOffsetList.get(i);
                            aLine.append(String.format("%f%s%f%s", oO.getXForTheWhole(), CSV_VALUE_SEPARATOR, oO.getXPerDay(), CSV_VALUE_SEPARATOR));
                        }
                        else {
                            aLine.append(String.format("%s%s", CSV_VALUE_SEPARATOR, CSV_VALUE_SEPARATOR));
                        }
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
