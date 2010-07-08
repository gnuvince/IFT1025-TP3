package seismes;

import java.util.HashMap;

public class TestParseDouble {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, Integer> hm = new HashMap();
		
		hm.put("Allo", (Integer) 3);
		System.out.println(hm.get("Allo"));
		System.out.println(hm.containsKey("Allo"));
	}

}
