package utils;

public class Cronometro {
	
	private static long startOperation;
	private static long finishOperation;
	private static long results;

	public static void start() {
		startOperation = System.currentTimeMillis();
		finishOperation = 0;
		results = 0;
	}

	public static void stop() {
		finishOperation = System.currentTimeMillis();
		results = finishOperation - startOperation;
	}

	public static long retornaTempo() {
		return results;
}

}
