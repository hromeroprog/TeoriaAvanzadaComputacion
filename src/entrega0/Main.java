package entrega0;

public class Main {
	
	public boolean primalityTest(long number) {
		boolean isPrime = true;
		for (int i = 2; i < number; i++) {
			if(number%i == 0) {
				isPrime = false;
			}
		}
		return isPrime;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(5/3);
	}

}
