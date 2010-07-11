package seismes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;


public class SeismeUtils {
    public static void sortByDate(Seisme[] arr) {
        Arrays.sort(arr, new Comparator<Seisme>() {
           public int compare(Seisme s1, Seisme s2) {
        	   if (s1 != null && s2 != null)
        		   return s1.getDatetime().compareTo(s2.getDatetime());
        	   else
        		   return 0;
           }
        });
    }
    
    public static void sortByDistance(Seisme[] arr) {
        Arrays.sort(arr, new Comparator<Seisme>() {
           public int compare(Seisme s1, Seisme s2) {
        	   if (s1 != null && s2 != null)
        		   return (int)((s1.distance() - s2.distance()) * 10);
        	   else
        		   return 0;
           }
        });
    }
    
    
    public static void sortByMagnitude(Seisme[] arr) {
        Arrays.sort(arr, new Comparator<Seisme>() {
           public int compare(Seisme s1, Seisme s2) {
        	   if (s1 != null && s2 != null)
        		   return (int)((s1.getMagnitude() - s2.getMagnitude()) * 10);
        	   else
        		   return 0;
           }
        });
    }
    
    public static boolean isAccepted(Date afterDate, double latitude,
    							     double longitude, double distance,
    								 double magnitude, Seisme seisme) {
    	
    	return (seisme.getDatetime().after(afterDate)) &&
    		    (new Coord(latitude, longitude).distance(seisme.getCoord()) <= distance) &&
    		    (seisme.getMagnitude() >= magnitude);
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
    
    public static String collapseToString(Seisme[] arr) {
    	StringBuilder sb = new StringBuilder();
    	
    	for (Seisme s : arr) {
    		if (s != null)
    			sb.append(s.toString() + "\n");
    	}
    	return sb.toString();
    }
}
