import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

//import Promocja.promocje;


public class Spada {
	int x;
	int y;
	int promocja;
	static ArrayList<Spada> listaSpadajacych = new ArrayList<Spada>();
	public Spada(int x,int y,int promocja) {
		this.x=x;
		this.y=y;
		this.promocja=promocja;
		listaSpadajacych.add(this);
	}
	public static void rysujSpada(Graphics g,Plutno p) {
		for (int i=0;i<listaSpadajacych.size();i++){
			Spada s = listaSpadajacych.get(i);
//			g.drawRect(s.x, s.y, 10, 10);
			
			
			g.drawImage(Plutno.mapaSpadajacych.get(Promocja.mapaEnum.get(s.promocja)), s.x, s.y, p);
			
			
//			switch (s.promocja) {
//			case 1:
//				g.drawImage(Plutno.SpadaPrzyspieszenie, s.x, s.y, p);
//				break;
//			case 2:
//				g.drawImage(Plutno.SpadaZamrozeniePaletki, s.x, s.y, p);
//				break;
//			case 3:
//				g.drawImage(Plutno.SpadaZaklejonaPrzepasc, s.x, s.y, p);
//				break;
//			case 4:
//				g.drawImage(Plutno.SpadaUwolnionaPaletka, s.x, s.y, p);
//				break;
//			case 5:
//				g.drawImage(Plutno.SpadaLepkaPaletka, s.x, s.y, p);
//				break;
//			case 6:
//				g.drawImage(Plutno.SpadaPowiekszeniePaletki, s.x, s.y, p);
//				break;
//			case 7:
//				g.drawImage(Plutno.SpadaPomniejszeniePaletki, s.x, s.y, p);
//				break;
//			case 8:
//				g.drawImage(Plutno.SpadaDuzaKula, s.x, s.y, p);
//			default:
//				break;
//			}
		}

	}
	public static void ruch(){
		
		ArrayList<Spada> doUsunieciaSpadajace = new ArrayList<Spada>();
		for (int i=0;i<listaSpadajacych.size();i++){
			Spada s = listaSpadajacych.get(i);
			if (Anim2.czas%2==0){//!!!!!!!!!!!!!!!
				s.y++;
			}
			Rectangle paletka = new Rectangle(Paletka.x,Paletka.y,Paletka.rozmiarX,Paletka.rozmiarY);
			Rectangle spada = new Rectangle(s.x,s.y,40,20);
			
			if (spada.intersects(paletka)){
				Promocja.promocja(s.promocja);
				doUsunieciaSpadajace.add(s);
//				System.out.println("SPADAJACE DOTNELO PALETKI!!");
			}
			if (s.y>700){
				doUsunieciaSpadajace.add(s);
			}
		}
		for (Spada i: doUsunieciaSpadajace){
			listaSpadajacych.remove(i);
		}
//		System.out.println(l.size());
	}
}
