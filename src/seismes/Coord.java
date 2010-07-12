package seismes;

/**
 * Classe qui représente un point sur la terre via sa latitude et longitude
 * @author Eric Thivierge, Vincent Foley
 *
 */
public class Coord {
	private final double R = 6367.45;
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
        double t1, t2, t3, t4;
        double phi1 = (this.latitude / 90) * Math.PI;
        double phi2 = (coord.getLatitude() / 90) * Math.PI;
        double theta1 = ((this.longitude + 180) / 360) * 2 * Math.PI;
        double theta2 = ((coord.longitude + 180) / 360) * 2 * Math.PI;
        
        t1 = Math.pow(Math.sin((phi1 - phi2) / 2), 2);
        t2 = Math.cos(phi1);
        t3 = Math.cos(phi2);
        t4 = Math.pow(Math.sin((theta1 - theta2) / 2), 2);
        
		return 2 * R * Math.asin(Math.sqrt(t1 + t2 * t3 * t4));
	}
	
	@Override
	public String toString() {
		return latitude + ", " + longitude;
	}
}
