package timers;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import tools.Tools;
import uc3m.tac.algoritmos.AKS;

public class RTiming {
	int minDigits = 5;
	int maxDigits = 200;
	int iterations = 70;
	boolean keepExecution;
	long delay;

	public RTiming(int delay_in_seconds) {
		this.keepExecution = true;
		this.delay = (long) delay_in_seconds * 1000;
	}

	public void run() throws IOException {
		ArrayList<BigInteger> primes = new ArrayList<>();
		ArrayList<BigInteger> composites = new ArrayList<>();
		ArrayList<Long> primes_times = new ArrayList<>();
		ArrayList<Long> composites_times = new ArrayList<>();

		// GESTIONAR UNA POSIBLE SALIDA ANTES DE QUE TERMINE LA EJECUCION
		Timer timer = new Timer("Timer");
		timer.schedule(getTimerTask(this), this.delay);

		for (int digits = minDigits; digits < maxDigits; digits++) {
			System.out.print("\nEvaluando numeros de \t" + digits + "digitos");
			for (int i = 0; i < iterations; i++) {
				BigInteger n = Tools.getRandomBigIntegerPrime(digits);
				AKS a = new AKS(n);
				long start = System.currentTimeMillis();
				a.obtainR();
				long end = System.currentTimeMillis();
				primes.add(n);
				primes_times.add(end - start);

				n = Tools.getRandomBigInteger(digits);
				a = new AKS(n);
				System.out.print(".");
				start = System.currentTimeMillis();
				a.obtainR();
				end = System.currentTimeMillis();
				composites.add(n);
				composites_times.add(end - start);

				if (!this.keepExecution)
					break;
				if (i % 5 == 0)
					System.out.println("" + i + "/" + iterations + "\t" + (end - start) + "ms");
			}
			if (!this.keepExecution) {
				timer.cancel();
				break;
			}
		}
		Tools.exportCSV("./outputs/obtainR/primesStop.csv", primes, primes_times);
		Tools.exportCSV("./outputs/obtainR/compositesStop.csv", composites, composites_times);
		System.out.println("Finished RTiming");
		return;
	}

	private TimerTask getTimerTask(RTiming rt) {
		TimerTask timertask = new TimerTask() {
			public void run() {
				rt.keepExecution = false;
			}
		};
		return timertask;
	}

}
