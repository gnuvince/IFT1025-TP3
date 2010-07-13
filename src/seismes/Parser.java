/**
 * 
 */
package seismes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Parse les lignes du fichier donne
 * @author Eric Thivierge, Vincent Foley
 *
 */
public class Parser {
	private final String SEPARATOR = ",";
	private String filename;
	private Seisme[] seismes;
	
	public Parser(String filename) {
	    this.filename = filename;
	}
	
	
	/**
	 * parse() lit le fichier `filename` ligne par ligne et ajoute des objets
	 * Seisme au tableau `seismes`.
	 */
	public void parse() {
	    ArrayList<Seisme> al = new ArrayList<Seisme>();
	    try {
	        BufferedReader in = new BufferedReader(new FileReader(filename));
	        try {
	            // Skip first line
	            in.readLine();
	            while (in.ready()) {
	                String line = in.readLine();
	                Seisme seisme = parseLine(line);
	                if (seisme != null)
	                    al.add(seisme);
	            }
	        }
	        catch (IOException e) {
	            System.err.println(e);
	            al.clear(); 
	        }
	        finally {
	            in.close();
	        }
	    }
	    catch (IOException e) {
	        System.err.println(e);
	    }
	    
	    seismes = new Seisme[al.size()];
	    seismes = al.toArray(seismes);
	}
	
	
	/**
	 * Lit une ligne dans `filename` et retourne un objet Seisme ou null
	 * s'il y a des erreurs
	 * @param line un String représentant la ligne actuelle
	 * @return objet Seisme ou null s'il y a des erreurs
	 */
	public Seisme parseLine(String line) {
	    String[] components = line.split(SEPARATOR);
	    
	    try {
	        Date datetime = parseDatetime(components[0], components[1]);
	        double lat = Double.parseDouble(components[2]);
	        double lon = Double.parseDouble(components[3]);
	        double depth = Double.parseDouble(sanitize(components[4]));
	        double magnitude = Double.parseDouble(sanitize(components[5]));
	        String comment = components[6];
	        return new Seisme(lat, lon, magnitude, datetime, depth, comment);
	    }
	    catch (NumberFormatException e) { 
	        System.err.println(e);
	        return null;
	    }
	}
	
	
	/**
	 * Garde seulement les chiffres, le point décimal et le signe de
	 * négativité
	 * @param s String représentant un double avec des caractères suffixes non-numériques.
	 * @return String sans les caractères suffixes
	 */
	private String sanitize(String s) {
	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < s.length(); ++i) {
	        char c = s.charAt(i);
	        if (c >= '0' && c <= '9' || c == '.' || c == '-') 
	            sb.append(c);
	    }
	    return sb.toString();
	}
	
	   
	/**
	 * Utilise une date au format mm/dd/yy et une heure au format hh:mm:ss
	 * pour créer un objet date.
	 * @param dateString date au format mm/dd/yy
	 * @param timeString heure au format hh:mm:ss
	 * @return
	 */
    @SuppressWarnings("deprecation")
    private Date parseDatetime(String dateString, String timeString) {
        String[] dateParts = dateString.split("/");
        String[] timeParts = timeString.split(":");
        return new Date(
                Integer.parseInt(dateParts[2].trim())+100,
                Integer.parseInt(dateParts[0].trim()),
                Integer.parseInt(dateParts[1].trim()),
                Integer.parseInt(timeParts[0].trim()),
                Integer.parseInt(timeParts[1].trim()),
                Integer.parseInt(timeParts[2].trim()));
    }


    public Seisme[] getSeismes() {
        return seismes;
    }
}
