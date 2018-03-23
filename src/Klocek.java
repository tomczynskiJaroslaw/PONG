import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Klocek {
	int x;
	int y;
	static int rozmiarX=40 ;
	static int rozmiarY=20 ;
	/**modyfikator, premia jaka daje zbicie klocka*/
	int promocja=0;
	static ArrayList<Klocek> klocki = new ArrayList<Klocek>();
	public Klocek(int x,int y){
		this.x=x;
		this.y=y;
		klocki.add(this);
	}
	public Klocek(int x,int y,int promocja){
		this.x=x;
		this.y=y;
		this.promocja=promocja;
		klocki.add(this);
	}
	
	public static void dodajKlocki(){
		Klocek k = new Klocek(0, 300);
		k.promocja=8;
//		for (int i=0;i<20;i++){
//			for (int n=0;n<10;n++){
//				Klocek k =new Klocek(i*(rozmiarX+1),n*(rozmiarY+1));
//				k.promocja=(int) ((Math.random()*8)+1);
//				System.out.println(klocki.size());
//			}
//		}
//		for (int i=0;i<10;i++){
//			for (int n=12;n<15;n++){
//				Klocek k =new Klocek(i*(rozmiarX+1),n*(rozmiarY+1));
//				k.promocja=(int) ((Math.random()*8)+1);
//				
//				System.out.println(k.promocja);
//			}
//		}
	}
	public static void wczytajKlocki(String plik){
		
			FileInputStream fis;
			try {
				fis = new FileInputStream(new File(plik));
				
			 
				//Construct BufferedReader from InputStreamReader
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			 
				String line = null;
				while ((line = br.readLine()) != null) {
					String[] linia = line.split(" ");
					Klocek k = new Klocek(Integer.parseInt(linia[0]), Integer.parseInt(linia[1]),Integer.parseInt(linia[2]));
					System.out.println(k.promocja+"<--");
					if (k.promocja==-1){
						k.promocja=(int)(Math.random()*(double)Promocja.mapaEnum.size());
					}
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
	}
	public static void rysujKlocki(Graphics2D g2, Plutno plutno) {
		for (int i=0;i<klocki.size();i++){
//			g.drawRect(klocki.get(i).x, klocki.get(i).y, klocki.get(i).rozmiarX, klocki.get(i).rozmiarY);
			g2.drawImage(Plutno.klocek, klocki.get(i).x, klocki.get(i).y, plutno);
//			for (Promocja.promocje p: Promocja.promocje.values()){
//				System.out.println(p);
				if (Promocja.mapaPromocji.containsKey("odkryteKlocki")){
					if (Promocja.mapaEnum.containsKey(klocki.get(i).promocja)){
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
						BufferedImage SpadaZaklejonaPrzepasc2 = Plutno.mapaSpadajacych.get(Promocja.mapaEnum.get(klocki.get(i).promocja)).getSubimage(0, 10, 30, 10);
						g2.drawImage(SpadaZaklejonaPrzepasc2, klocki.get(i).x+5, klocki.get(i).y+5, plutno);
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
					}
				}
//			}
//			switch (klocki.get(i).promocja) {
//			case 3:
//				BufferedImage SpadaZaklejonaPrzepasc2 = Plutno.SpadaZaklejonaPrzepasc.getSubimage(0, 10, 30, 10);
//				
//				g.drawImage(SpadaZaklejonaPrzepasc2, klocki.get(i).x+5, klocki.get(i).y+5, plutno);
//				break;
//
//			default:
//				g.drawImage(Plutno.klocek, klocki.get(i).x, klocki.get(i).y, plutno);
//				break;
//			}
			
			
		}
	}
	public static void ruch() {
		for (int i=0;i<klocki.size();i++){
			Klocek klocek=klocki.get(i);
			if (!Pilka.pilkaStoi){
				//dó³
				if ((  Pilka.y == klocek.y+klocek.rozmiarY ) && goraDol(klocek)){
					Pilka.kierunekY=-Pilka.kierunekY;
					Pilka.y++;//!
					zbity(klocek, i);
				}
				//dóra
				if (Pilka.y+Pilka.rozmiar==klocek.y && goraDol(klocek)){
					Pilka.kierunekY=-Pilka.kierunekY;
					Pilka.y--;//!
					zbity(klocek, i);
				}
				//prawo
				if ((  klocek.x+klocek.rozmiarX==Pilka.x) && lewoPrawo(klocek)){
					Pilka.kierunekX=-Pilka.kierunekX;
					Pilka.x++;//!
					zbity(klocek,i);
				}
				//lewo
				if (Pilka.x+Pilka.rozmiar==klocek.x && lewoPrawo(klocek)){
					Pilka.kierunekX=-Pilka.kierunekX;
					Pilka.x--;//!
					zbity(klocek,i);
				}
			}
		}
	}
	public static void zbity(Klocek klocek,int i){
//		Promocja.promocja(klocek.promocja);
//		System.out.println(klocek.promocja);
		new Spada(klocek.x+5,klocek.y,klocek.promocja);
		Dzwiek.odtwarzaj("Blop.wav");
		if (klocki.contains(klocek)){
			klocki.remove(i);
		}
	}
	public static boolean lewoPrawo(Klocek klocek){
		return Pilka.y+Pilka.rozmiar>=klocek.y && Pilka.y<=klocek.y+klocek.rozmiarY ;
	}
	public static boolean goraDol(Klocek klocek){
		return Pilka.x+Pilka.rozmiar>=klocek.x && Pilka.x<=klocek.x+klocek.rozmiarX;
	}
}
