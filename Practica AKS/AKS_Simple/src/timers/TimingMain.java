package timers;

import java.io.IOException;

public class TimingMain {
	public static void main(String[] args) throws IOException {
		//Tiempo en segundos
		int time = 8*60*60/5; // (8 horas) * (60 minutos/hora) * (60 segundos/minuto) / (5 metodos)
		
		PerfectPowTiming ppt = new PerfectPowTiming(time);
		ppt.run();
		
		RTiming rt = new RTiming(time);
		rt.run();
		
		MCDTiming mcdt = new MCDTiming(time);
		mcdt.run();
		
		TotientTiming tt = new TotientTiming(time);
		tt.run();
		
		SufficientConditionTiming sct = new SufficientConditionTiming(time);
		sct.run();
	}
}
