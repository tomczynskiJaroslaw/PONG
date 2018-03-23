import java.util.ArrayList;
import java.util.HashMap;

/** modyfikator, premia, bonus */
public class Promocja {
	static enum promocje {
		brakPromocji,//0
		przyspieszenie,//1
		zamrozonaPaletka,//2
		zaklejonaPrzepasc,//3
		uwolnionaPaletka,//4
		lepkaPaletka,//5
		powiekszeniePaletki,//6
		pomniejszeniePaletki,//7
		duzaKula,//8
		odkryteKlocki,//9
	};
	
	
	int czasStartu;
	int czasTrwaniaPromocji = 2000;
	static HashMap<String, Promocja> mapaPromocji = new HashMap<String, Promocja>();
	static HashMap<Integer, promocje> mapaEnum = new HashMap<Integer, Promocja.promocje>();
	static{
		int i=0;
		for (promocje p: promocje.values()){
			mapaEnum.put(i, p);
			i++;
		}
	}
	
	
	public static void promocja(int promocja) {
		if (promocje.przyspieszenie.ordinal()==promocja){
			Anim2.DELAY=1;
		}else if(promocje.lepkaPaletka.ordinal()==promocja){
			Pilka.y -= 5;
		}else if(promocje.powiekszeniePaletki.ordinal()==promocja){
			if (Paletka.rozmiarX<400){
				int interwal = 20;
				Paletka.rozmiarX += interwal;
				Paletka.x -= (interwal / 2);
			}
		}else if(promocje.pomniejszeniePaletki.ordinal()==promocja){
			if (Paletka.rozmiarX>30){
				int interwal = 20;
				Paletka.rozmiarX -= interwal;
				Paletka.x += (interwal / 2);
			}
		}else if(promocje.duzaKula.ordinal()==promocja){
			if (Pilka.rozmiar < 80) {
				Pilka.rozmiar += 10;
			}
		}
		new Promocja(mapaEnum.get(promocja).name());
		
//		switch (promocja) {
//		case 1:
//			Anim2.DELAY=1;//!!!!!!!!!!!!!!!!!
//			new Promocja(promocje.przyspieszenie.name());
//			break;
//		case 2:
//			new Promocja(promocje.zamrozonaPaletka.name(),250);
//			break;
//		case 3:
//			new Promocja(promocje.zaklejonaPrzepasc.name());
//			break;
//		case 4:
//			new Promocja(promocje.uwolnionaPaletka.name());
//			break;
//		case 5:
//			new Promocja(promocje.lepkaPaletka.name());
//			Pilka.y -= 5;
//			break;
//		case 6:
//			if (Paletka.rozmiarX < 400) {
//				int interwal = 20;
//				Paletka.rozmiarX += interwal;
//				Paletka.x -= (interwal / 2);
//				new Promocja(promocje.powiekszeniePaletki.name());
//			}
//			break;
//		case 7:
//			if (Paletka.rozmiarX > 30) {
//				int interwal = 20;
//				Paletka.rozmiarX -= interwal;
//				Paletka.x += (interwal / 2);
//				new Promocja(promocje.pomniejszeniePaletki.name());
//			}
//			break;
//		case 8:
//			if (Pilka.rozmiar < 80) {
//				Pilka.rozmiar += 10;
//				new Promocja(promocje.duzaKula.name());
//				// System.out.println(Pilka.rozmiar+" Pilka rozmiar");
//			}
//			break;
//		default:
//			break;
//		}
		Dzwiek.odtwarzaj("neck.wav");
	}

	public Promocja(String nazwa) {
		this.czasStartu = Anim2.czas;//!!!!!!!!!!!!!
		mapaPromocji.put(nazwa, this);
	}
	public Promocja(String nazwa,int czasTrwaniaPromocji) {
		this.czasTrwaniaPromocji=czasTrwaniaPromocji;
		this.czasStartu = Anim2.czas;//!!!!!!!!!!!!!!
		mapaPromocji.put(nazwa, this);
	}
	
	/** Odswieza czas pozostaly do konca danego modyfikatora | KoniecMod moze wiazac sie z jakims dzialaniem*/
	public static void aktualizujAnim() {

		ArrayList<String> doUsunieciaPromocje = new ArrayList<String>();
		for (String s : mapaPromocji.keySet()) {
			Promocja p = mapaPromocji.get(s);
			if (p.czasStartu < Anim2.czas - p.czasTrwaniaPromocji) {//!!!!!!!!!!!!
				if (s.equals(promocje.uwolnionaPaletka.name())) {
					Paletka.y = 600;
				} else if (s.equals(promocje.duzaKula.name())) {
					Pilka.rozmiar -= 10;
				} else if (s.equals(promocje.przyspieszenie.name())) {
					Anim2.DELAY=Anim2.domyslneOpuznienie;//!!!!!!!!!!!!!!!
				}
				doUsunieciaPromocje.add(s);
			}
		}
		for (String s : doUsunieciaPromocje) {
			mapaPromocji.remove(s);
		}
	}
}
