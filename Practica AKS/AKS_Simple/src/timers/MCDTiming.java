package timers;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import tools.Tools;
import uc3m.tac.algoritmos.AKS;

public class MCDTiming {
	int minDigits = 3;
	int maxDigits = 200;
	int iterations = 50;
	boolean keepExecution;
	long delay;

	public MCDTiming(int delay_in_seconds) {
		this.keepExecution = true;
		this.delay = (long) delay_in_seconds * 1000;
	}

	public void run() throws IOException {
		ArrayList<BigInteger> primes = new ArrayList<>();
		ArrayList<BigInteger> primes_r = new ArrayList<>();
		ArrayList<Long> primes_times = new ArrayList<>();

		// GESTIONAR UNA POSIBLE SALIDA ANTES DE QUE TERMINE LA EJECUCION
		Timer timer = new Timer("Timer");
		timer.schedule(getTimerTask(this), this.delay);

		for (int digits = minDigits; digits < maxDigits; digits++) {
			System.out.print("\nEvaluando numeros de \t" + digits + "digitos");
			for (int i = 0; i < iterations; i++) {
				BigInteger n = Tools.getRandomBigIntegerPrime(digits);
				AKS a = new AKS(n);

				// PRIMES
				BigInteger r = a.obtainR();
				long start = System.currentTimeMillis();
				a.checkMCD(r);
				long end = System.currentTimeMillis();
				primes.add(n);
				primes_r.add(r);
				primes_times.add(end - start);
				if (!this.keepExecution)
					break;

				if (!this.keepExecution)
					break;
				if (i % 5 == 0)
					System.out.println("" + i + "/" + iterations + "\t");
			}
			if (!this.keepExecution) {
				timer.cancel();
				break;
			}
		}
		Tools.exportCSV("./outputs/checkMCD/primesStop.csv", primes, primes_r, primes_times);
		System.out.println("Finished MCDTiming");
		return;
	}

	private TimerTask getTimerTask(MCDTiming mcdt) {
		TimerTask timertask = new TimerTask() {
			public void run() {
				mcdt.keepExecution = false;
			}
		};
		return timertask;
	}
}
