import java.awt.MouseInfo;
/**odbija pilke nie pozwalajac jej spas w przepasc [steruje nia gracz]*/
public class Paletka {
	/** domyslne ustawienie paletki w pionie */
	static final int domyslnyY = 600;
	static int x = 100;
	static int y = 600;
	static int rozmiarX = 200;// 50
	final static int rozmiarY = 20;
	static int predkosc = 5;//OPCJA w MENU sterownie gracza
	static boolean prawo = false;
	static boolean lewo = false;
	static boolean gora = false;
	static boolean dol = false;

	public static void ruch() {
		// STEROWANIE Paletka
		if (!Promocja.mapaPromocji.containsKey(Promocja.promocje.zamrozonaPaletka.name())) {
			// [])Drugi parametr w if ograniczenia na wyjscie paletki poza ekran
			if (lewo && x + (rozmiarX / 2) - Pilka.rozmiar > 0) {
				Paletka.x -= Paletka.predkosc;
			}
			// [])Drugi parametr w if ograniczenia na wyjscie paletki poza ekran
			if (prawo && x + (rozmiarX / 2) + Pilka.rozmiar < Okienko.plutno.getWidth()) {
				Paletka.x += Paletka.predkosc;
			}
			// pozycja X myszy wzgledem okienka
			int mysz = (int) (MouseInfo.getPointerInfo().getLocation().getX() - (Okienko.ramka.getLocation().getX()));
			// [])Drugi parametr w if ograniczenia na wyjscie paletki poza ekran
			if (Sterowanie.mysz && mysz > 0 && mysz < Okienko.plutno.getWidth()) {
				x = mysz - rozmiarX / 2;
			}
			if (Promocja.mapaPromocji.containsKey("uwolnionaPaletka")) {
				// [])Drugi parametr w if ograniczenia na wyjscie paletki poza ekran
				if (gora && y - Pilka.rozmiar > 0) {// Mozliwe ograniczenia na wysokosc na jaka mozna uniesc paletke
					Paletka.y -= Paletka.predkosc;
				}
				// [])Drugi parametr w if ograniczenia na wyjscie paletki poza ekran
				if (dol && y + rozmiarY < Okienko.getWysokosc()) {
					Paletka.y += Paletka.predkosc;
				}
				// [])Drugi parametr w if ograniczenia na wyjscie paletki poza ekran
				if (Sterowanie.mysz && mysz > 0 && mysz < Okienko.getWysokosc()) {
					y = (int) (MouseInfo.getPointerInfo().getLocation().getY() - (Okienko.ramka.getLocation().getY() + 30));
				}
			}
		}

		// odbicie pilki od paletki DO GORY
		if ((Pilka.y + Pilka.rozmiar == y) && Pilka.x + Pilka.rozmiar >= x && Pilka.x <= x + rozmiarX) {
			Pilka.kierunekY = -Pilka.kierunekY;//odbicie
			Pilka.y--; // podnosi pilke jednorazowo o pixel do gory by odbicie nastepowalo RAZ
			Dzwiek.odtwarzaj("Realistic.wav");
			
			//OPCJA1 styl odbicia "pol kole"
//			int wspolrzendneSrodkaPilki = Pilka.x + Pilka.rozmiar / 2;
			int i = (((Pilka.x + (Pilka.rozmiar / 2) - (Paletka.x + (Paletka.rozmiarX / 2))) / 1));
			
			double katR = ((Math.atan((double) Pilka.mocY / Pilka.mocX)));
			Pilka.mocY = 50; //wstepnie duza wartosc Zmniejszona w procesie "skracania ulamkow"
			int alfa = i;
			alfa = (int) (Math.abs(alfa) / 1.0);
			// ZIELONY - odbicie ZE zmiana kierunkuX
			if ((Pilka.kierunekX == 1 && i < 0) || (Pilka.kierunekX == -1 && i > 0)) {
				if (katR > 90 - 2 * alfa) {
					Pilka.mocX = ((int) Math.abs((Pilka.mocY / Math.tan(Math.toRadians((180 - Math.toDegrees(katR) - alfa))))));
					System.out.println("1");
					Pilka.kierunekX = -Pilka.kierunekX;
				} else if (katR > 90 - alfa) {
					Pilka.mocX = ((int) Math.abs((Pilka.mocY / Math.tan(Math.toRadians((Math.toDegrees(katR) + alfa - 90))))));
					System.out.println("2");
					Pilka.kierunekX = -Pilka.kierunekX;
				}
			}
			// RӯOWY - odbicie BEZ zmiany kierunkuX
			if ((Pilka.kierunekX == 1 && i < 0) || (Pilka.kierunekX == -1 && i > 0)) {
				Pilka.mocX = ((int) Math.abs((Pilka.mocY / Math.tan(Math.toRadians((Math.toDegrees(katR) + alfa))))));
				// utrzymuje kierunek!
				System.out.println("3");
			}
			if ((Pilka.kierunekX == -1 && i < 0) || (Pilka.kierunekX == 1 && i > 0)) {
				Pilka.mocX = ((int) Math.abs((Pilka.mocY / Math.tan(Math.toRadians((Math.toDegrees(katR) - alfa))))));
				// utrzymuje kierunek!
				System.out.println("4");
			}
			// SZARY - odbicie po ktorym pilka dalej spadala by w dul sztucznie osiaga skrajnie plaskie odbicie w gore
			if ((Pilka.kierunekX == -1 && i < 0) || (Pilka.kierunekX == 1 && i > 0)) {
				if (Math.toDegrees(katR) < alfa) {
					Pilka.mocX = 20;
					System.out.println("5");
				}
			}

			// "SKRACANIE ULAMKOW :)"
			int skalujDo = 2;
			System.out.println(Pilka.mocX + " " + Pilka.mocY);
			if (Pilka.mocX > Pilka.mocY) {

				Pilka.mocX = (int) (Pilka.mocX * skalujDo / (double) Pilka.mocY);
				Pilka.mocY = skalujDo;
			} else {

				Pilka.mocY = (int) (Pilka.mocY * skalujDo / (double) Pilka.mocX);
				Pilka.mocX = skalujDo;
			}
//			System.out.println(Pilka.mocX + " " + Pilka.mocY);
//			System.out.println(Math.toDegrees(katR) + " " + Pilka.mocX + " " + Pilka.mocY + " " + alfa + " " + (Math.toDegrees(katR) + alfa));
			
			// int tmpX = ((int)
			// Math.abs((Pilka.mocY/Math.tan(Math.toRadians((Math.toDegrees(katR)+alfa))))));
			// int tmpX2 =(tmpX>100)?(100):(tmpX);
			// Pilka.mocX = tmpX2;
			//
			// if ((Math.toDegrees(katR)+alfa)>90){
			// Pilka.mocX=(int)Math.abs((Pilka.mocY/Math.tan(Math.toRadians((Math.toDegrees(katR)+alfa-90)))));
			// // Pilka.kierunekX=-Pilka.kierunekX;
			// }
			// // if ((0>Math.abs(alfa)+Math.toDegrees(katR) ||
			// Math.abs(alfa)+Math.toDegrees(katR)>90 )&&
			// ((wspolrzendneSrodkaPilki<=Paletka.x+Paletka.rozmiarX/3/*!1/3!*/
			// && Pilka.kierunekX==1) ||
			// (wspolrzendneSrodkaPilki>=(int)Paletka.x+Paletka.rozmiarX*((double)2/3/*!2/3!*/)&&Pilka.kierunekX==-1))){
			// // Pilka.kierunekX=-Pilka.kierunekX;
			// // System.out.println("----");
			// // }else
			// if (Math.toDegrees(katR)<Math.abs(i/2) && ((i<0 &&
			// Pilka.kierunekX==-1) ||(i>0 && Pilka.kierunekX==1))){
			// Pilka.mocX=30;
			// System.out.println("0_O "+Math.toDegrees(katR)+" "+Math.abs(i/2));
			// }
			
			
			// //lewa
			// if (wspolrzendneSrodkaPilki<=Paletka.x+Paletka.rozmiarX/3 &&
			// Pilka.kierunekX==1){
			// Pilka.kierunekX=-Pilka.kierunekX;
			// }
			// //prawa
			// if
			// (wspolrzendneSrodkaPilki>=(int)Paletka.x+Paletka.rozmiarX*((double)2/3)&&Pilka.kierunekX==-1){
			// Pilka.kierunekX=-Pilka.kierunekX;
			// }
			if (Promocja.mapaPromocji.containsKey("lepkaPaletka")) {
				Pilka.pilkaStoi = true;
			}
		}
		// odbicie lewo prawo pionowymi krotkimi bokami paletki
		if ((Pilka.x + Pilka.rozmiar == x || x + rozmiarX == Pilka.x) && Pilka.y >= y && Pilka.y <= y + rozmiarY) {
			Pilka.kierunekX = -Pilka.kierunekX;
		}
	}
}
