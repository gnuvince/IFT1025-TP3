/**
 * 
 */
package seismes;

import java.io.Serializable;

/**
 * @author Eric Thivierge, Vincent Foley
 *
 */
public class Coord implements Serializable {
	private double
		longitude,
		latitude;
	
	public Coord(double latitude, double longitude) {
		this.longitude = longitude;
		this.latitude = latitude;
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
	
	public void print() {
		System.out.println(this.latitude + ", " + this.longitude);
	}
	
}
