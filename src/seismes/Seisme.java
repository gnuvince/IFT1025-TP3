/**
 * 
 */
package seismes;

import java.util.Date;


/**
 * Classe représentant un séisme
 * @author Eric Thivierge, Vincent Foley
 *
 */
public class Seisme {
    private Coord coord;
    private double magnitude;
    private Date datetime;
    private double depth;
    private String comment;
    
    
    /**
     * Construit un nouveau Seisme
     * @param latitude la latitude du séisme
     * @param longitude la longitude du séisme
     * @param magnitude la magnitude du séisme à l'échelle de Richter
     * @param datetime la date du séisme
     * @param depth la profondeur du séisme
     * @param comment un String
     */
    public Seisme(
            double latitude, double longitude, double magnitude,
            Date datetime, double depth, String comment) {
        this.coord = new Coord(latitude, longitude);
        this.magnitude = magnitude;
        this.datetime = datetime;
        this.depth = depth;
        this.comment = comment;
    }
    
    public double distance(Coord point) {
        return coord.distance(point);
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
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();

    	sb.append(this.datetime + ", ");
    	sb.append(this.coord.getLatitude() + ", ");
    	sb.append(this.coord.getLongitude() + ", ");
    	sb.append(this.magnitude + ", ");
    	sb.append(this.depth + ", ");
    	sb.append(this.comment);
    	
    	return sb.toString();
    }

}
