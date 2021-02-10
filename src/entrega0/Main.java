package entrega0;

public class Main {
	
	public static boolean primalityTest(long number) {
		boolean isPrime = true;
		for (long i = 2; i < number; i++) {
			if(number%i == 0) {
				isPrime = false;
			}
		}
		return isPrime;
	}
	
	public static void usePrimalityTest(long number) {
		long start1 = System.nanoTime();
	    boolean result = primalityTest(number);
	    long end1 = System.nanoTime();
		System.out.println("The number: " + number + (result?" is prime":" is not prime,\t") + "Elapsed Time in nano seconds: "+ (end1-start1));
	}

	public static void main(String[] args) {
		usePrimalityTest(454540055L);
		usePrimalityTest(40);
		usePrimalityTest(40);
		usePrimalityTest(40);
	}
}
