package seismes;

import java.util.Arrays;
import java.util.Comparator;


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
}
