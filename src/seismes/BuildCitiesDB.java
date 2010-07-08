package seismes;

public class BuildCitiesDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String
			cities_filename = "/Users/eric/Documents/dev/workspace/IFT1025-TP3/src/seismes/canCities.csv";
		CitiesDB
			cities = new CitiesDB("/Users/eric/Documents/dev/workspace/IFT1025-TP3/src/seismes/cities.pickle"),
			cities2 = new CitiesDB();
		
		cities.importCities(cities_filename);
//		System.out.println(cities);
		
		cities.writeDB();
		
		cities2.loadDB("/Users/eric/Documents/dev/workspace/IFT1025-TP3/src/seismes/cities.pickle");
		System.out.println(cities2);
	}
}
