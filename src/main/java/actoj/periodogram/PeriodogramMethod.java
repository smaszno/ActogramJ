package actoj.periodogram;

/**
 * Created by piotr.rolinski on 04/04/2020.
 */
public enum PeriodogramMethod {
    
    LOMB_SCARGLE( "Lomb-Scargle"), FOURIER("Fourier"), CHI_SQUARE("Chi-Square");
    
    String title;
    
    PeriodogramMethod (String title) {
        this.title = title;
    }
    
    public String title() {
        return title;
    }
    
    
}
