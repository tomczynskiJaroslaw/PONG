import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Okienko {
	static JFrame ramka = new JFrame("ramka");
	static Plutno plutno = new Plutno();
	public Okienko(){
		plutno.obszarRysowaniaWczytaj();
		ramka.add(plutno);
		ramka.pack();
		ramka.setBackground(Color.black);
		ramka.setResizable(false);
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.setLocationRelativeTo(null);
		ramka.setVisible(true); 
	}
	public static int getSzerokosc(){
		return plutno.getWidth();
	}
	public static int getWysokosc() {
		return plutno.getHeight();
	}
}
