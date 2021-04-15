package timers;

import tools.Tools;
import uc3m.tac.algoritmos.AKS;
import java.awt.AWTException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PerfectPowTiming {
	int minDigits = 5;
	int maxDigits = 60;
	int iterations = 70;
	boolean keepExecution;
	long delay;

	public PerfectPowTiming(int delay_in_seconds) {
		this.keepExecution = true;
		this.delay = (long)delay_in_seconds * 1000;
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
				a.isPower();
				long end = System.currentTimeMillis();
				primes.add(n);
				primes_times.add(end - start);

				n = Tools.getRandomBigInteger(digits);
				a = new AKS(n);
				System.out.print(".");
				start = System.currentTimeMillis();
				a.isPower();
				end = System.currentTimeMillis();
				composites.add(n);
				composites_times.add(end - start);

				if (!this.keepExecution)
					break;
				if (i % 5 == 0)
					System.out.println("" + i + "/" + iterations + "\t" + (end - start) + "ms");
			}
			if (!this.keepExecution)
				break;
		}
		Tools.exportCSV("./outputs/isPower/primesStop.csv", primes, primes_times);
		Tools.exportCSV("./outputs/isPower/compositesStop.csv", composites, composites_times);
		System.out.println("Finished PerfectPowOutputs");
		return;
	}

//	static boolean checkStopExecution(Timer timer) {
//		boolean stopExecution = false;
//		
//		delay = 500L;
//		timer.schedule(getTimerTaskEnter(), delay);
//		System.out.println("Continue?");
//		Scanner myObj = new Scanner(System.in);
//		String continue_control = myObj.nextLine();
//		if (!continue_control.equals("yes")) {
//			stopExecution = true;
//			// Mato al hilo del timer
//			timer.cancel();
//			System.out.println("Stopping...");
//		}
//		return stopExecution;
//	}

	private TimerTask getTimerTask(PerfectPowTiming ppt) {
		TimerTask timertask = new TimerTask() {
			public void run() {
				ppt.keepExecution = false;
			}
		};
		return timertask;
	}
//
//	static TimerTask getTimerTaskEnter() {
//		TimerTask timertask = new TimerTask() {
//			public void run() {
//				try {
//					Robot robot = new Robot();
//					robot.keyPress(KeyEvent.VK_ENTER);
//					robot.keyRelease(KeyEvent.VK_ENTER);
//				} catch (AWTException e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		return timertask;
//	}
}
