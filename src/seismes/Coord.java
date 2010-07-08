/**
 * 
 */
package seismes;

/**
 * @author Eric Thivierge, Vincent Foley
 *
 */
public class Coord {
    private double latitude;
    private double longitude;
		
	public Coord(double latitude, double longitude) {
	    this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}	
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}	
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double distance(Coord coord) {
		return 0.0;
	}
	
	@Override
	public String toString() {
		return latitude + ", " + longitude;
	}
	
}
