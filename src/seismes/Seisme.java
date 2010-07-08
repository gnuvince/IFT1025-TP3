/**
 * 
 */
package seismes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author Eric Thivierge, Vincent Foley
 *
 */
public class Seisme {
	private Parser psr = new Parser();
	private CitiesDB cities = new CitiesDB();
	
	public Seisme(String file) {
		
	}
	
	public void importSeismes(String seisme_filename) {
//Date,Time(UT),Lat,Long,Depth,Mag,Region and Comment
		String[]
		       nextLine;
		String
		       latitude,
		       longitude;
		try {
			CSVReader reader = new CSVReader(new FileReader(seisme_filename), ',', '\"', 3);
		    
		    while ((nextLine = reader.readNext()) != null) {
		    	latitude = nextLine[2];
		    	longitude = nextLine[3];
		    	
////		    	if (strLgt.length() > 0 && strLat.length() > 0) {
////			    	latitude = Double.parseDouble((String) strLat.subSequence(0, strLat.length()-2));
////			    	longitude = Double.parseDouble((String) strLgt.subSequence(0, strLgt.length()-2));
////			    	aCity = new City(cityName, provName, new Coord(latitude, longitude));
////			    	if (!this.cityDB.containsKey(provName))
////			    		this.cityDB.put(provName, new HashMap<String, City>());
////			    	this.cityDB.get(provName).put(cityName, aCity);
//		    	}
//		    }
//		} catch (FileNotFoundException e) {
//				e.printStackTrace();
//		} catch (IOException e) {
//				e.printStackTrace();
		    }
	    } catch (Exception e) {}
	}
		

}
