//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.Timer;
//import javax.swing.JPanel;
///**odswieza kolejne klatki animacji w calej grze*/
//public class Animacja extends JPanel {
//	/**zwiekrza sie o jeden w kazdej kolejnej klatce animacji zaczynajac od 0 (zlicza odswiezenia panelu)*/
//	static int czas=0; 
//	static Timer timer;
//	private static long s; //tmp szas wykonania jdnego fps-a
////	public Animacja() {
////		setPreferredSize(new Dimension(800, 100));
////	}
//
//	public static void animate() {
////		final int animationTime = 1000;
//		
//		int delay = 1; //(int)(1000 / (double)framesPerSecond);
//		final long start = System.currentTimeMillis();
//		timer = new Timer(delay, null);
//		timer.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				for (int i=0;i<1;i++){//WYSOCE NIE PROFESJONALNE!!!
////					s = System.nanoTime();
//					Pilka.ruch();
////					Sterowanie.ruch();
//					Paletka.ruch();
//					
//					Klocek.ruch();
//					Promocja.aktualizujAnim();
//					Spada.ruch();
//					czas++;
//					Gra.ruch();
//					Okienko.ramka.repaint();
////					final long s2 = System.currentTimeMillis();
////					System.out.println(s-System.nanoTime());
//					s=System.nanoTime();
//					
//				}
//			}
//		});
////		timer.start();
////		System.out.println("Animacja timer.start()");
//	}
//}