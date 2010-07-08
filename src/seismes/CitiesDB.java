package seismes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.Enumeration;

import au.com.bytecode.opencsv.CSVReader;

public class CitiesDB implements Serializable {
	private static final String
		DEFAULT_DB_FILENAME = "cities.pickle";
	
	private String
		db_filename;
	private HashMap<String, HashMap<String, City>>
		cityDB;
	
	public CitiesDB() {
		this(DEFAULT_DB_FILENAME);
	}
		
	public CitiesDB(String db_filename) {
		this.cityDB = new HashMap();
		this.db_filename = db_filename;
	}
	
	// importe un fichier de format a l'url:
	// http://www.world-gazetteer.com/wg.php?x=1&men=gcis&lng=en&des=wg&geo=-45&srt=npan&col=abcdefghinoq&msz=1500&pt=c&va=x
	public void importCities(String cities_filename) {
		double
			latitude = 0.0,
			longitude = 0.0;
		String[]
		    nextLine;
		String
			strLgt, strLat, cityName, provName;
		City
			aCity;

		try {
			CSVReader reader = new CSVReader(new FileReader(cities_filename), ',', '\"', 3);
		    
		    while ((nextLine = reader.readNext()) != null) {
		    	cityName = nextLine[1];
		    	provName = nextLine[8];
		    	strLgt = nextLine[7];
		    	strLat = nextLine[6];
		    	
		    	if (strLgt.length() > 0 && strLat.length() > 0) {
			    	latitude = Double.parseDouble((String) strLat.subSequence(0, strLat.length()-2));
			    	longitude = Double.parseDouble((String) strLgt.subSequence(0, strLgt.length()-2));
			    	aCity = new City(cityName, provName, new Coord(latitude, longitude));
			    	if (!this.cityDB.containsKey(provName))
			    		this.cityDB.put(provName, new HashMap<String, City>());
			    	this.cityDB.get(provName).put(cityName, aCity);
		    	}
		    }
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public void loadDB() {
		if (this.db_filename != null)
			this.loadDB(db_filename);
		else
			this.loadDB(DEFAULT_DB_FILENAME);
	}
	
	public void loadDB(String db_filename) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		
		try {
			fis = new FileInputStream(db_filename);
			in = new ObjectInputStream(fis);
			this.cityDB = (HashMap<String, HashMap<String, City>>) in.readObject();
		} catch(IOException ex) {
			ex.printStackTrace();
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
//			try {
//				in.close();
//			} catch(IOException ex) {
//				ex.printStackTrace();
//			}
		}
	}
	
	public void writeDB() {
		if (this.db_filename != null)
			this.writeDB(this.db_filename);
		else
			this.writeDB(DEFAULT_DB_FILENAME);
	}
	
	public void writeDB(String db_filename) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		
		try {
			fos = new FileOutputStream(this.db_filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(this.cityDB);
		} catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.close();
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public String toString() {
		final String
			header = "Ville       Province  latitude  longitude\n";
		String
			newLine,
			cityName,
			provName;
		StringBuilder
			sb = new StringBuilder(10000);
		Iterator
			iterProv = this.cityDB.keySet().iterator(),
			iterCity;
		City
			aCity;
		Coord
			cityCoord;
		
		sb.append(header);
		while (iterProv.hasNext()) {
			provName = (String) iterProv.next();
			iterCity = this.cityDB.get(provName).keySet().iterator();
			while (iterCity.hasNext()) {
				cityName = (String) iterCity.next();
				aCity = this.cityDB.get(provName).get(cityName);
				
				sb.append(aCity.getName() + " ");
				sb.append(aCity.getProvince() + " ");
				cityCoord = aCity.getCoord();
				sb.append(cityCoord.getLatitude() + ", " + cityCoord.getLongitude() + "\n");
			}
		}
		
		return sb.toString();
	}
}
