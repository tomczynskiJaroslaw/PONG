import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sterowanie {
	static boolean mysz = true;

	public static void ruch() {
		Okienko.ramka.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					Paletka.lewo = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					Paletka.prawo = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					Paletka.gora = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					Paletka.dol = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					Pilka.pilkaStoi = false;
				}
				if ((Anim2.aktywny==false)) {
					Gra.przegrana = false;
					Gra.wygrana = false;
					// Pilka.pilkaStoi=false;
					System.out.println(Anim2.thread.isAlive()+"<-------");
					Anim2.aktywny=true;
					
				}
				if (e.getKeyCode() == KeyEvent.VK_P) {
					Anim2.aktywny=false;//!!!!!!!!!!!!!!!!!
				}
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					Anim2.aktywny=false;//!!!!!!!!!!!!!!
					new Pomoc();
					System.out.println("Sterowania.Pomoc");
				}
				System.out.println("KLLAWISZ WCISBNNIETO");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					Paletka.lewo = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					Paletka.prawo = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					Paletka.gora = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					Paletka.dol = false;
				}
			}
		});
		Okienko.ramka.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				Pilka.x = e.getX();
				Pilka.y = e.getY();
			}

		});
	}
}
