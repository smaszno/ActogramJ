package actoj.averageactivity;

import actoj.core.Actogram;
import actoj.core.TimeInterval;

import java.awt.*;

/**
 * Created by piotr.rolinski on 11/10/2020.
 */
public class OnsetOffset {


    private Double xForTheWhole;
    private Double xPerDay;


    public Double getXForTheWhole() {
        return xForTheWhole;
    }

    public Double getXPerDay() {
        return xPerDay;
    }

    public OnsetOffset (Double xCoordinate, TimeInterval.Units unit) {

        xForTheWhole = xCoordinate;
        int howManyDays = (int) (xForTheWhole / unit.in24Hours);
        xPerDay = xForTheWhole - (howManyDays * unit.in24Hours);
    }
}
