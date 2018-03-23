
import javax.swing.JPanel;

public class Anim2 extends JPanel implements Runnable {
	public static int domyslneOpuznienie=2;
	static boolean aktywny=true;
	static int czas = 0;
	static int DELAY = domyslneOpuznienie;
	static Thread thread= new Thread(new Anim2());
	
//	public Anim2() {
//		thread 
//	}
	
	private void cycle() {
		for (int i = 0; i < 2; i++) {
			Pilka.ruch();
			// Sterowanie.ruch();
			Paletka.ruch();

			Klocek.ruch();
			Promocja.aktualizujAnim();
			Spada.ruch();
			czas++;
			Gra.ruch();
		}
	}

	@Override
	public void run() {

		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (true) {
			if (aktywny){
				cycle();
			}
			Okienko.ramka.repaint();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;
			// if (timeDiff>20 || timeDiff<0){
			// System.out.println("---------"+timeDiff);
			// }
			if (sleep < 0) {
				sleep = 2;
			}

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("Interrupted: " + e.getMessage());
			}

			beforeTime = System.currentTimeMillis();
		}
	}
}