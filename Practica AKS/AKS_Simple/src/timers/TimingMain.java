package timers;

import java.awt.AWTException;
import java.io.IOException;

public class TimingMain {
	public static void main(String[] args) throws IOException {
		PerfectPowTiming ppt = new PerfectPowTiming(8);
		ppt.run();
	}
}
