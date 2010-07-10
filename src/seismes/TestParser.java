package seismes;

public class TestParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Parser p = new Parser("/Users/eric/Documents/dev/workspace/IFT1025-TP3/src/seismes/seismes.csv");
		Seisme[] S;
		
		p.parse();
		S = p.getSeismes();

		System.out.println("Les seismes:");
		for (Seisme s: S) {
//	        System.out.println(s.getDatetime());
	        System.out.println(s);
	    }
	}

}
