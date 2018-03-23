import java.awt.EventQueue;

import javax.swing.JFrame;




public class Main {
	public static void main(String[] args) {
		
		new Okienko();
		new Paletka(); //odbija pilke | steruje uzytkownik
		
		new Gra(); //ladowanie poziomow | wygrana | przegrana
		
		
		Klocek.wczytajKlocki("plik1");
		

//		Klocek.dodajKlocki();
		
		
		new Dzwiek("Realistic.wav");
		new Dzwiek("Blop.wav");
		new Dzwiek("neck.wav");
		new Dzwiek("plop.wav");
		new Dzwiek("End.wav");
		new Dzwiek("Martian.wav");
		new Dzwiek("Boxing.wav");
		
		Sterowanie.ruch();
//		Animacja.animate();!!!!!!!!!!!!!!!!
//		Thread m = new Anim2();
		Anim2.thread.start();
		
		
		
		
		
		
		
		
		for(Promocja.promocje p: Promocja.promocje.values()){
			System.out.println(p);
		}
	}
}
