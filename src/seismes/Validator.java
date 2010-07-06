package seismes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public interface Validator {
    boolean isValid(String input);
    String getErrorMessage();
}

class RangeValidator implements Validator {
    private double low;
    private double high;
    
    public RangeValidator(double low, double high) {
        this.low = low;
        this.high = high;
    }
    
    public boolean isValid(String input) {
        double x;
        try {
            x = Double.parseDouble(input);
            return (x >= low) && (x <= high); 
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public String getErrorMessage() {
        return "Cette valeur doit se trouver dans l'intervalle [" + low + 
            ", " + high + "]";
    }
}


class PositiveDoubleValidator implements Validator {
    public boolean isValid(String input) {
        double x;
        try {
            x = Double.parseDouble(input);
            return x >= 0;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public String getErrorMessage() {
        return "Cette valeur doit être un réel non-négatif";
    }
}


class DateValidator implements Validator {
    private static final String dateFormat = "yyyy/mm/dd";
    public boolean isValid(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            sdf.parse(input);
            return true;
        }
        catch (ParseException e) {
            return false;
        }
    }
    
    public String getErrorMessage() {
        return "le format de date doit être: " + dateFormat;
    }
}