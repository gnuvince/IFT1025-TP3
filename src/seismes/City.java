/**
 * 
 */
package seismes;

import java.io.Serializable;

/**
 * @author eric
 *
 */
public class City implements Serializable {
	private String
		name,
		province;
	private Coord
		coord;
	
	public City(String name, String province, Coord coord) {
		this.name = name;
		this.province = province;
		this.coord = coord;
	}
	
	public String getName() {
		
		return this.name;
	}	
	
	public String getProvince() {
		
		return this.province;
	}	
	
	public Coord getCoord() {
		
		return this.coord;
	}
	
	public void setName(String name) {
		this.name = name;
	}	
	
	public void setProvince(String province) {
		this.province = province;
	}	
	
	public void setCoord(Coord coord) {
		this.coord = coord;
	}
	
	public void print() {
		System.out.println(this.name);
		System.out.println(this.province);
		this.coord.print();
	}

}
