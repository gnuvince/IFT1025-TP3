package seismes;

import java.util.ArrayList;
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
    	Seisme[] all;
        ArrayList<Seisme> accepted = new ArrayList<Seisme>();
		
		p.parse();
		all = p.getSeismes();
		for (Seisme s: all) {
			if (isAccepted(afterDate, latitude, longitude, distance, magnitude, s)) {
				accepted.add(s);
			}
		}
    	return accepted.toArray(new Seisme[1]);
    }
}
