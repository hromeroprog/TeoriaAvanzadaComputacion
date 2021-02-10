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
		System.out.println("The number: " + number + (primalityTest(number)?" is prime":" is not prime"));
	}

	public static void main(String[] args) {
		usePrimalityTest(37);
		usePrimalityTest(40);
	}
}
