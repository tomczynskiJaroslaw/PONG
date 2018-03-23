import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class Plutno extends JPanel{
	static BufferedImage klocek;
	static Image tlo;
	static Image tloUwolnionaPaletka;
	static Image tloZaklejonaPrzepasc;
//	static Image paletka;
	
	static BufferedImage paletka;
	static BufferedImage pilka;
	static BufferedImage paletkaLepka;
	static BufferedImage paletkaZamrozona;
	static BufferedImage tloPrzegrana;
	static BufferedImage tloWygrana;
	
	//Spadajace promocje do zlapania
	static HashMap<Promocja.promocje, BufferedImage> mapaSpadajacych = new HashMap<Promocja.promocje, BufferedImage>();
	static BufferedImage SpadaZamrozeniePaletki;
	static BufferedImage SpadaZaklejonaPrzepasc;
	static BufferedImage SpadaUwolnionaPaletka;
	static BufferedImage SpadaLepkaPaletka;
	static BufferedImage SpadaDuzaKula;
	static BufferedImage SpadaPrzyspieszenie;
	static BufferedImage SpadaPowiekszeniePaletki;
	static BufferedImage SpadaPomniejszeniePaletki;
	
	
	public void obszarRysowaniaWczytaj(){
		try {
			klocek = ImageIO.read(new File("zasoby/klocek.gif"));
			tlo = Toolkit.getDefaultToolkit().getImage("zasoby/tlo.GIF");
			tloUwolnionaPaletka = Toolkit.getDefaultToolkit().getImage("zasoby/tloUwolnionaPaletka.GIF");
			tloZaklejonaPrzepasc = Toolkit.getDefaultToolkit().getImage("zasoby/tloZaklejonaPrzepasc.GIF");
//			paletka = Toolkit.getDefaultToolkit().getImage("zasoby/paletka.GIF");
			pilka = ImageIO.read(new File("zasoby/pilka2.gif"));
			
			paletka = ImageIO.read(new File("zasoby/paletka.gif"));
			paletkaLepka = ImageIO.read(new File("zasoby/paletkaLepka.gif"));
			paletkaZamrozona = ImageIO.read(new File("zasoby/PromocjaZamrozonaPaletka.gif"));
			tloPrzegrana = ImageIO.read(new File("zasoby/tloPrzegrales.png"));
			tloWygrana = ImageIO.read(new File("zasoby/tloWygrales.png"));
			
			//Spadajace promocje do zlapania
			for (Promocja.promocje p: Promocja.promocje.values()){
//				System.out.println(p);
				mapaSpadajacych.put(p, ImageIO.read(new File("zasoby/"+p+".gif")));
			}
			SpadaZamrozeniePaletki= ImageIO.read(new File("zasoby/zamrozonaPaletka.gif"));
			SpadaZaklejonaPrzepasc = ImageIO.read(new File("zasoby/zaklejonaPrzepasc.gif"));
			SpadaUwolnionaPaletka = ImageIO.read(new File("zasoby/uwolnionaPaletka.gif"));
			SpadaLepkaPaletka= ImageIO.read(new File("zasoby/lepkaPaletka.gif"));
			SpadaDuzaKula =ImageIO.read(new File("zasoby/duzaKula.gif"));
			
			SpadaPrzyspieszenie = ImageIO.read(new File("zasoby/przyspieszenie.gif"));
			SpadaPomniejszeniePaletki = ImageIO.read(new File("zasoby/pomniejszeniePaletki.gif"));
			SpadaPowiekszeniePaletki = ImageIO.read(new File("zasoby/powiekszeniePaletki.gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dimension d = new Dimension(500,700);
		setMaximumSize(d);
		setPreferredSize(new Dimension(d));
		setMaximumSize(d);
	}
	public void paintComponent(Graphics g) {
//		long s = System.nanoTime();
//		g.setColor(Color.white);
		int h = getHeight();
		int x = getWidth();
		Graphics2D g2 = (Graphics2D) g;
		//|TLO| SPODNIA WARSTWA
		g.drawImage(Plutno.tlo, 0, 0, this);
		if (Promocja.mapaPromocji.containsKey("uwolnionaPaletka")){
			g.drawImage(Plutno.tloUwolnionaPaletka, 0, 0, this);
		}
		//promocje aktywne SRODKOWA WARSTWA
		if (!Promocja.mapaPromocji.containsKey("lepkaPaletka")){
			g.drawImage(paletka, Paletka.x, Paletka.y, Paletka.rozmiarX, Paletka.rozmiarY,this);
		}else{//|PALETKA|
			g.drawImage(paletkaLepka, Paletka.x, Paletka.y, Paletka.rozmiarX, Paletka.rozmiarY,this);
		}
		
		if (Promocja.mapaPromocji.containsKey(Promocja.promocje.zamrozonaPaletka.name())){
			BufferedImage paletkaZamrozonaSkadrowana = paletkaZamrozona.getSubimage(0, 0, Paletka.rozmiarX, Paletka.rozmiarY);
			g.drawImage(paletkaZamrozonaSkadrowana, Paletka.x, Paletka.y, this);
//			System.out.println("zamrozeniePaletki");
		}
		if (Promocja.mapaPromocji.containsKey("zaklejonaPrzepasc")){
			g.drawImage(tloZaklejonaPrzepasc, 0, 680, this);//dolny pasek ekranu
		}
		//|PILKA|
		g.drawImage(pilka,Pilka.x, Pilka.y, Pilka.rozmiar, Pilka.rozmiar,this);
		//|KLOCKI|
		Klocek.rysujKlocki(g2, this);
		
		
		//spadajace promocje WIERZCHNIA WARSTWA Z PRZEZROCZYSTOSCIA
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
		Spada.rysujSpada(g,this);
		
		//menu NADRZEDNA WARSTWA
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		g.drawString(Gra.liczbaZyc+"", 10, 20);
		if (Gra.przegrana){
			g.drawImage(tloPrzegrana, 0, 0, this);
		}
		if (Gra.wygrana){
			g.drawImage(tloWygrana, 0, 0, this);
		}
		
		//TMP TEST
//		g.drawImage(tloPrzegrana, 0, 0, this);
//		g.drawImage(paletkaZamrozonaSkadrowana, 0, 0, this);
//		g.setColor(Color.white);
//		for (int i=Pilka.tmpLista.size()-1;i>Pilka.tmpLista.size()-300 && i>0;i--){
//			g.drawRect(Pilka.tmpLista.get(i)[0], Pilka.tmpLista.get(i)[1], 3, 3);
//		}
		
//		System.out.println(s-System.nanoTime());
	}
}
