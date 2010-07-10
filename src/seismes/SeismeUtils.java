package seismes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;


public class SeismeUtils {
    public static void sortByDate(Seisme[] arr) {
        Arrays.sort(arr, new Comparator<Seisme>() {
           public int compare(Seisme s1, Seisme s2) {
               return s1.getDatetime().compareTo(s2.getDatetime());
           }
        });
    }
    
    public static void sortByDistance(Seisme[] arr) {
        Arrays.sort(arr, new Comparator<Seisme>() {
           public int compare(Seisme s1, Seisme s2) {
               return (int)((s1.distance() - s2.distance()) * 10);
           }
        });
    }
    
    
    public static void sortByMagnitude(Seisme[] arr) {
        Arrays.sort(arr, new Comparator<Seisme>() {
           public int compare(Seisme s1, Seisme s2) {
               return (int)((s1.getMagnitude() - s2.getMagnitude()) * 10);
           }
        });
    }
    
    public static boolean isAccepted(Date afterDate, double latitude,
    							     double longitude, double distance,
    								 double magnitude, Seisme seisme) {
    	boolean retval = true;
    	
    	if (afterDate != null)
    		retval = retval && (seisme.getDatetime().after(afterDate));
    	if (distance >= 0)
    		retval = retval && (new Coord(latitude, longitude).distance(seisme.getCoord()) >= distance);
    	if (magnitude != 0)
    		retval = retval && (seisme.getMagnitude() >= magnitude);
    	
    	return retval;
    }
    
    public static Seisme[] filterSeismes(Date afterDate, double latitude,
		                               double longitude, double distance,
			                           double magnitude, String filename) {
    	Parser p = new Parser(filename);
    	Seisme[] raw, filtered;
    	int n = 0;
		
		p.parse();
		raw = p.getSeismes();
		filtered = new Seisme[raw.length];
		for (Seisme s: raw) {
			if (isAccepted(afterDate, latitude, longitude, distance, magnitude, s)) {
				filtered[n] = s;
				n++;
			}
		}
    	return filtered;
    }
    
    public static String arrayToString(Seisme[] arr) {
    	StringBuilder sb = new StringBuilder();
    	
    	for (Seisme s : arr) {
    		sb.append(s.toString() + "\n");
    	}
    	
    	return sb.toString();
    }
}
