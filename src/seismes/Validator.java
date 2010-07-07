package seismes;

import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Interface pour la validation d'une chaîne de caractères.
 * @author vince
 *
 */
public interface Validator {
    /**
     * Vérifie une chaîne de caractères et retourne si elle est valide.
     * @param input la chaîne à vérifier
     * @return true si la chaîne est valide, false sinon
     */
    boolean isValid(String input);
    
    /**
     * Donne la raison pour laquelle la chaîne est invalide
     * @return un String contenant l'erreur
     */
    String getErrorMessage();
}


/**
 * Vérifie qu'une valeur est numérique et se trouve dans un intervalle défini.
 * @author vince
 *
 */
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


/**
 * Vérifie qu'une valeur est numérique et non-négative.
 * @author vince
 *
 */
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


/**
 * Vérifie qu'une valeur de date correspond à un format prédéfini.
 * @author vince
 *
 */
class DateValidator implements Validator {
    private static final String dateFormat = "yyyy/MM/dd";
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