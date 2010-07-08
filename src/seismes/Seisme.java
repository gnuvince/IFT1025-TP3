/**
 * 
 */
package seismes;

import java.util.Date;


/**
 * @author Eric Thivierge, Vincent Foley
 *
 */
public class Seisme {
    private Coord coord;
    private double magnitude;
    private Date datetime;
    private double depth;
    private String comment;
    
    
    public Seisme(
            double latitude, double longitude, double magnitude,
            Date datetime, double depth, String comment) {
        this.coord = new Coord(latitude, longitude);
        this.magnitude = magnitude;
        this.datetime = datetime;
        this.depth = depth;
        this.comment = comment;
    }
    
    public double distance() {
        return 0.0;
    }
            
    
    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }
    
    public double getMagnitude() {
        return magnitude;
    }
    
    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }
    
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

}
