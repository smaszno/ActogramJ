package actoj.periodogram;

/**
 * Created by piotr.rolinski on 04/04/2020.
 */
public class PeriodogramDataForFile {
    
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

    public StringBuilder getDataBuffer() {
        if (dataBuffer == null)
            dataBuffer = new StringBuilder();
        return dataBuffer;
    }

    public String convertToString() {
        return (dataBuffer != null ? dataBuffer.toString() : null);
    }
    
    public String getActogramGroupName() {
        return actogramGroupName;
    }

    public void setActogramGroupName(String actogramGroupName) {
        if (this.actogramGroupName == null)
            this.actogramGroupName = actogramGroupName;
    }
}
